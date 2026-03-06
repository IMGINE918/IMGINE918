# Student Management MCP Server Deployment Script
# Author: Gemini CLI Assistant

$ServerDir = "C:\Users\郑寒萍\JavaProject_StudentManagement\mcp-server"
Set-Location $ServerDir

Write-Host "--- 🚀 开始部署 Student Management MCP Server ---" -ForegroundColor Cyan

# 1. 创建 Python 虚拟环境
if (-not (Test-Path "venv")) {
    Write-Host "正在创建虚拟环境..." -ForegroundColor Green
    python -m venv venv
}

# 2. 安装依赖
Write-Host "正在安装依赖 (mcp, sqlalchemy, pymysql, python-dotenv)..." -ForegroundColor Green
.\venv\Scripts\pip install mcp sqlalchemy pymysql python-dotenv --quiet

# 3. 创建 .env 配置文件模板
if (-not (Test-Path ".env")) {
    Write-Host "正在生成 .env 模板..." -ForegroundColor Green
    "DB_URL=mysql+pymysql://root:您的密码@localhost:3306/student_management" | Out-File -FilePath ".env" -Encoding utf8
    Write-Host "⚠️ 请在 .env 文件中修改您的数据库密码！" -ForegroundColor Yellow
}

# 4. 生成 Claude Desktop 配置参考
$ConfigPath = "$env:APPDATA\Claude\claude_desktop_config.json"
Write-Host "--- 💡 配置参考 ---" -ForegroundColor Cyan
Write-Host "请将以下内容手动添加到 Claude Desktop 的配置文件中 ($ConfigPath):" -ForegroundColor White
Write-Host @"
{
  "mcpServers": {
    "student-management": {
      "command": "$($ServerDir.Replace('\','\\'))\\venv\\Scripts\\python.exe",
      "args": [
        "$($ServerDir.Replace('\','\\'))\\server.py"
      ]
    }
  }
}
"@ -ForegroundColor Gray

Write-Host "--- ✅ 部署脚本运行完成 ---" -ForegroundColor Cyan
