# Frontend

This directory contains the React frontend shell for the student-management practice project.

## What it does now

- starts as a Vite React app
- renders dashboard cards and student rows
- falls back to mock data when the backend is unavailable
- switches to backend data when `/api/*` endpoints are available

## Commands

```powershell
npm.cmd install
npm.cmd run dev
```

## Full local flow

Start the backend first:

```powershell
..\mvnw.cmd spring-boot:run
```

Then start the frontend:

```powershell
npm.cmd run dev
```

The Vite dev server proxies `/api/*` requests to `http://localhost:8080`.
