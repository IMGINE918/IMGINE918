#!/usr/bin/env python3
"""
Student Management MCP Server.

This server exposes a small set of tools for AI clients such as Cursor or
Claude Desktop. It is intentionally lightweight and rule-based.
"""

from __future__ import annotations

import json
import os
from pathlib import Path
from typing import Any

from dotenv import load_dotenv
from mcp.server import Server
from mcp.types import TextContent, Tool
from sqlalchemy import create_engine, text
from sqlalchemy.exc import SQLAlchemyError

load_dotenv(Path(__file__).resolve().parent.parent / ".env")

DB_URL = os.getenv(
    "DB_URL",
    "mysql+pymysql://student:student123@localhost:3306/student_management",
)

PROJECT_ROOT = Path(__file__).resolve().parent.parent
SRC_ROOT = PROJECT_ROOT / "src" / "main" / "java" / "com" / "student"

server = Server("student-management-mcp")

try:
    engine = create_engine(DB_URL)
except Exception as exc:  # pragma: no cover - defensive startup handling
    print(f"Failed to create database engine: {exc}")
    engine = None


def _text(value: str) -> list[TextContent]:
    return [TextContent(type="text", text=value)]


def _json_response(ok: bool, **payload: Any) -> str:
    return json.dumps({"ok": ok, **payload}, ensure_ascii=False, indent=2, default=str)


def _is_read_only_sql(sql: str) -> bool:
    normalized = sql.strip().lower()
    allowed = ("select", "show", "describe", "desc", "explain")
    blocked = ("insert", "update", "delete", "drop", "alter", "truncate", "create")
    return normalized.startswith(allowed) and not any(token in normalized for token in blocked)


def execute_sql_impl(sql: str) -> str:
    if not engine:
        return _json_response(ok=False, error="Database engine is not available. Check DB_URL configuration.")
    if not _is_read_only_sql(sql):
        return _json_response(ok=False, error="Only read-only SQL statements are allowed.")

    try:
        with engine.connect() as conn:
            result = conn.execute(text(sql))
            rows = [dict(zip(result.keys(), row)) for row in result]
            return _json_response(ok=True, rows=rows, row_count=len(rows))
    except SQLAlchemyError as exc:
        return _json_response(ok=False, error=f"SQL execution error: {exc}")


def audit_code_impl(file_path: str) -> str:
    full_path = (SRC_ROOT / file_path).resolve()
    if not full_path.exists():
        return _json_response(ok=False, error=f"File not found: {file_path}")
    if SRC_ROOT not in full_path.parents and full_path != SRC_ROOT:
        return _json_response(ok=False, error="Only files inside src/main/java/com/student are allowed.")

    content = full_path.read_text(encoding="utf-8")
    issues: list[str] = []

    if "System.out.println" in content:
        issues.append("- Prefer structured logging over System.out.println in service code.")
    if "TODO" in content:
        issues.append("- Remove or resolve TODO markers before treating this as production-ready.")
    if "Connection" in content and ".close()" not in content and "try (" not in content:
        issues.append("- Database resources may not be closed safely.")
    if "password" in content.lower():
        issues.append("- Review whether sensitive values are hard-coded.")
    if "catch (Exception" in content:
        issues.append("- Avoid overly broad exception handling when a narrower type is possible.")

    if not issues:
        return _json_response(ok=True, findings=[], summary="No obvious rule-based issues were found.")

    return _json_response(ok=True, findings=issues, summary="Rule-based issues found.")


def generate_tutoring_plan_impl(student_name: str, major: str, score: float) -> str:
    plan = f"""# Targeted Tutoring Plan

Student: {student_name}
Major: {major}
Current Score: {score}

Goal:
- Raise understanding of core weak points within 7 days
- Build a repeatable review plan before the next assessment

7-Day Plan:
- Day 1: Review the weakest core concepts in {major}
- Day 2: Rework missed question types and summarize mistakes
- Day 3: Complete a guided practice set with explanations
- Day 4: Run a short AI-assisted mock oral review
- Day 5: Complete a timed written practice
- Day 6: Fix remaining weak points and review notes
- Day 7: Do one final mock assessment and reflection

Advisor Notes:
- Keep feedback concrete and encouraging
- Focus on the smallest number of high-impact weak points first
"""
    return _json_response(
        ok=True,
        student_name=student_name,
        major=major,
        score=score,
        plan_markdown=plan,
    )


@server.list_resources()
async def list_resources() -> list:
    return [
        {
            "uri": "database://schema",
            "name": "Database Status",
            "description": "Connection status and current database name.",
            "mimeType": "application/json",
        }
    ]


@server.read_resource()
async def read_resource(uri: str) -> str:
    if uri != "database://schema":
        return _json_response(ok=False, error="Resource not found.")
    if not engine:
        return _json_response(ok=False, status="disconnected")
    return _json_response(ok=True, status="connected", database=engine.url.database)


@server.list_tools()
async def list_tools() -> list[Tool]:
    return [
        Tool(
            name="execute_query",
            description="Run a read-only SQL query and return JSON results.",
            inputSchema={
                "type": "object",
                "properties": {
                    "sql": {"type": "string", "description": "A read-only SQL statement."}
                },
                "required": ["sql"],
            },
        ),
        Tool(
            name="audit_java_code",
            description="Run a lightweight rule-based audit on a local Java file.",
            inputSchema={
                "type": "object",
                "properties": {
                    "file_path": {
                        "type": "string",
                        "description": "Relative path under src/main/java/com/student",
                    }
                },
                "required": ["file_path"],
            },
        ),
        Tool(
            name="generate_tutoring_plan",
            description="Generate a simple tutoring plan for a student.",
            inputSchema={
                "type": "object",
                "properties": {
                    "student_name": {"type": "string"},
                    "major": {"type": "string"},
                    "score": {"type": "number"},
                },
                "required": ["student_name", "major", "score"],
            },
        ),
    ]


@server.call_tool()
async def call_tool(name: str, arguments: dict) -> list[TextContent]:
    if name == "execute_query":
        return _text(execute_sql_impl(arguments.get("sql", "")))
    if name == "audit_java_code":
        return _text(audit_code_impl(arguments.get("file_path", "")))
    if name == "generate_tutoring_plan":
        return _text(
            generate_tutoring_plan_impl(
                arguments.get("student_name", "Unknown"),
                arguments.get("major", "Unknown"),
                float(arguments.get("score", 0)),
            )
        )
    return _text(_json_response(ok=False, error=f"Unknown tool: {name}"))


async def main() -> None:
    from mcp.server.stdio import stdio_server

    async with stdio_server() as (read_stream, write_stream):
        await server.run(read_stream, write_stream, server.create_initialization_options())


if __name__ == "__main__":
    import asyncio

    asyncio.run(main())
