# Student Management System: AI-Native Practice Repo

This repository is a mixed practice workspace around:

- Java student-management domain modeling
- AI-assisted development workflow
- MCP server integration for Cursor / Claude
- Dockerized local services

It is best understood as an `AI-native engineering practice repo`, not a fully finished production application.

## Docs

- [docs/PROJECT_SCOPE.md](C:/Users/郑寒萍/GitHubProjects/Home/IMGINE918-profile/docs/PROJECT_SCOPE.md)
- [docs/SETUP.md](C:/Users/郑寒萍/GitHubProjects/Home/IMGINE918-profile/docs/SETUP.md)
- [docs/ARCHITECTURE.md](C:/Users/郑寒萍/GitHubProjects/Home/IMGINE918-profile/docs/ARCHITECTURE.md)

## What Is Runnable Today

The most complete runnable part is:

- [mcp-server](C:/Users/郑寒萍/GitHubProjects/Home/IMGINE918-profile/mcp-server)

It provides a lightweight MCP server that can:

- expose database connection status as a resource
- run read-only SQL queries
- audit local Java files with simple rule-based checks
- generate tutoring plans for at-risk students

Infrastructure available in this repo:

- MySQL
- Redis
- MCP server container

## What Is In Progress

These parts are still practice code or showcase material rather than a complete deployable product:

- [src](C:/Users/郑寒萍/GitHubProjects/Home/IMGINE918-profile/src)
- [frontend](C:/Users/郑寒萍/GitHubProjects/Home/IMGINE918-profile/frontend)

These parts are learning and practice material:

- [StudentSystem](C:/Users/郑寒萍/GitHubProjects/Home/IMGINE918-profile/StudentSystem)
- [JavaLearningDay1](C:/Users/郑寒萍/GitHubProjects/Home/IMGINE918-profile/JavaLearningDay1)
- [JavaOOP_Lesson01](C:/Users/郑寒萍/GitHubProjects/Home/IMGINE918-profile/JavaOOP_Lesson01)
- [ruanjian2ban](C:/Users/郑寒萍/GitHubProjects/Home/IMGINE918-profile/ruanjian2ban)

## Repository Structure

```text
.
|-- src/                  Java domain code and service logic
|-- frontend/             React UI experiments
|-- mcp-server/           Python MCP server
|-- StudentSystem/        Java basic student system practice
|-- JavaLearningDay1/     Java beginner exercises
|-- JavaOOP_Lesson01/     Java OOP exercises
|-- docker-compose.yml    Local infrastructure for practice
`-- Dockerfile            MCP server image definition
```

## Quick Start

### Option 1: Run the MCP server locally

```powershell
cd mcp-server
powershell .\deploy.ps1
.\venv\Scripts\python.exe .\server.py
```

### Option 2: Run local services with Docker

```powershell
Copy-Item .env.example .env
docker compose up --build
```

This starts:

- MySQL on `3306`
- Redis on `6379`
- MCP server on stdio inside the container

## Environment Setup

Use [.env.example](C:/Users/郑寒萍/GitHubProjects/Home/IMGINE918-profile/.env.example) as the template.

Important:

- never commit a real `.env`
- keep API keys and database passwords local only

## Cursor / Claude Learning Angle

If you are reading this repo to learn AI-native development, start here:

1. [docs/PROJECT_SCOPE.md](C:/Users/郑寒萍/GitHubProjects/Home/IMGINE918-profile/docs/PROJECT_SCOPE.md)
2. [mcp-server/server.py](C:/Users/郑寒萍/GitHubProjects/Home/IMGINE918-profile/mcp-server/server.py)
3. [mcp-server/deploy.ps1](C:/Users/郑寒萍/GitHubProjects/Home/IMGINE918-profile/mcp-server/deploy.ps1)
4. [src/main/java/com/student/service](C:/Users/郑寒萍/GitHubProjects/Home/IMGINE918-profile/src/main/java/com/student/service)

What this repo demonstrates:

- humans define architecture and guardrails
- AI helps implement and inspect
- MCP gives AI structured access to local project context

## Current Status

This repo intentionally keeps learning materials and practice code together.

That makes it useful for study, but it also means:

- some directories are exercises
- some are prototypes
- only part of the repo is production-like

## Suggested Next Improvements

- keep learning directories stable and separate from product work
- add real tests for `mcp-server`
- add a proper frontend app shell
- turn Java backend code into a standalone buildable service
