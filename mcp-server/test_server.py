import json

import server


def test_read_only_sql_allows_select():
    assert server._is_read_only_sql("select * from students")


def test_read_only_sql_blocks_delete():
    assert not server._is_read_only_sql("delete from students")


def test_generate_tutoring_plan_returns_json():
    result = json.loads(server.generate_tutoring_plan_impl("Alice", "Software Engineering", 58))
    assert result["ok"] is True
    assert result["student_name"] == "Alice"
    assert "Targeted Tutoring Plan" in result["plan_markdown"]


def test_audit_missing_file_returns_error():
    result = json.loads(server.audit_code_impl("missing/File.java"))
    assert result["ok"] is False
    assert "File not found" in result["error"]
