FROM python:3.11-slim

WORKDIR /app

ENV PYTHONDONTWRITEBYTECODE=1
ENV PYTHONUNBUFFERED=1

COPY mcp-server/requirements.txt /tmp/requirements.txt
RUN pip install --no-cache-dir -r /tmp/requirements.txt

COPY mcp-server /app/mcp-server
COPY .env.example /app/.env.example

WORKDIR /app/mcp-server

CMD ["python", "server.py"]
