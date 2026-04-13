# Student Management MCP Server Deployment Script

$ServerDir = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $ServerDir

Write-Host "--- Starting MCP server setup ---" -ForegroundColor Cyan

if (-not (Test-Path "venv")) {
    Write-Host "Creating Python virtual environment..." -ForegroundColor Green
    python -m venv venv
}

Write-Host "Installing Python dependencies..." -ForegroundColor Green
.\venv\Scripts\pip install -r .\requirements.txt

$RootEnv = Join-Path (Split-Path -Parent $ServerDir) ".env"
$ExampleEnv = Join-Path (Split-Path -Parent $ServerDir) ".env.example"

if (-not (Test-Path $RootEnv) -and (Test-Path $ExampleEnv)) {
    Copy-Item $ExampleEnv $RootEnv
    Write-Host "Created .env from .env.example. Please edit it before production use." -ForegroundColor Yellow
}

$PythonExe = Join-Path $ServerDir "venv\Scripts\python.exe"
$ServerFile = Join-Path $ServerDir "server.py"
$ConfigPath = "$env:APPDATA\Claude\claude_desktop_config.json"

Write-Host "--- Claude Desktop MCP config example ---" -ForegroundColor Cyan
Write-Host "Add the following entry to: $ConfigPath" -ForegroundColor White
Write-Host @"
{
  "mcpServers": {
    "student-management": {
      "command": "$($PythonExe.Replace('\','\\'))",
      "args": [
        "$($ServerFile.Replace('\','\\'))"
      ]
    }
  }
}
"@ -ForegroundColor Gray

Write-Host "--- Setup complete ---" -ForegroundColor Cyan
