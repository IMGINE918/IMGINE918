# Architecture

## Current Shape

The repository currently has three main technical slices:

1. Java domain and service code in [src/main/java/com/student](C:/Users/郑寒萍/GitHubProjects/Home/IMGINE918-profile/src/main/java/com/student)
2. React UI experiments in [frontend](C:/Users/郑寒萍/GitHubProjects/Home/IMGINE918-profile/frontend)
3. MCP integration in [mcp-server](C:/Users/郑寒萍/GitHubProjects/Home/IMGINE918-profile/mcp-server)

## Intended Relationship

- Java code contains business and domain logic
- frontend is the future user-facing application shell
- MCP server exposes project context and simple tools to AI clients such as Cursor or Claude

## Current Limitation

The repository is not yet a fully connected full-stack system.

Current status:

- MCP server is the clearest runnable part
- Java code is meaningful but not yet packaged as a standard buildable backend service
- frontend is still closer to a UI prototype than a complete app

## Recommended Evolution Path

1. keep MCP stable as the first reliable entry point
2. turn Java code into a standard Maven service
3. turn frontend into a startable app shell
4. connect frontend to backend
5. keep AI and MCP as an enhancement layer, not as the only entry point
