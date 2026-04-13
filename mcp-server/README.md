# MCP Server

This directory contains the Python MCP server for the repository.

## What it does

- exposes database status as a resource
- runs read-only SQL queries
- performs simple Java-file audits
- generates tutoring plans

## Local setup

```powershell
powershell .\deploy.ps1
.\venv\Scripts\python.exe .\server.py
```

## Tests

```powershell
.\venv\Scripts\pytest.exe .\test_server.py
```

## Requirements

- Python 3.10+
- access to a MySQL instance if you want SQL queries to work

## Notes

- configure values in the repository root `.env`
- do not commit real secrets
- tool results use JSON-shaped text payloads for more stable AI consumption
