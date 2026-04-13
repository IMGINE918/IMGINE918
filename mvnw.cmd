@echo off
setlocal

set "REPO_DIR=%~dp0"
set "MAVEN_HOME=%REPO_DIR%.tools\apache-maven-3.9.9"

if not exist "%MAVEN_HOME%\bin\mvn.cmd" (
  echo Maven distribution not found under .tools\apache-maven-3.9.9
  exit /b 1
)

if "%JAVA_HOME%"=="" (
  if exist "%USERPROFILE%\.jdks\openjdk-26\bin\java.exe" (
    set "JAVA_HOME=%USERPROFILE%\.jdks\openjdk-26"
  )
)

call "%MAVEN_HOME%\bin\mvn.cmd" %*
