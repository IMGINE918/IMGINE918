# Setup

## Local MCP Server

```powershell
cd mcp-server
powershell .\deploy.ps1
.\venv\Scripts\python.exe .\server.py
```

## Docker Compose

```powershell
Copy-Item .env.example .env
docker compose up --build
```

This starts:

- MySQL on `3306`
- Redis on `6379`
- the MCP server container

## Environment Variables

Use [.env.example](C:/Users/郑寒萍/GitHubProjects/Home/IMGINE918-profile/.env.example) as the template.

Important rules:

- keep real secrets only in local `.env`
- never commit `.env`
- keep Docker and local script settings aligned

## Java Backend Build

This repository now includes a repo-local Maven launcher.

```powershell
.\mvnw.cmd test
.\mvnw.cmd spring-boot:run
```

It uses:

- the Maven distribution stored in `.tools`
- the local JDK under `C:\Users\郑寒萍\.jdks\openjdk-26` when `JAVA_HOME` is not set
