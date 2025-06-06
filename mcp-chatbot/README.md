# Spring AI

This project demonstrates using a Model Context Protocol (MCP) to use external tools and resources in a structured way.

## LLM Models

The following LLM is used in this project.

| Vendor | LLM Model |
|-----------|----------------------------------------------|
| Anthropic | [claude-3-7-sonnet-latest][anthropic-models] |

### Anthropic

You will need to create an API key on the Anthropic portal. Create an account at
the [Anthropic API dashboard][anthropic] and generate the api key on the [Get API Keys][anthropic-api-keys] page. The
Spring AI project defines a configuration property named `spring.ai.anthropic.api-key` that you should set to the value
of the `API Key` obtained from `anthropic.com`.

Exporting an environment variable is one way to set that configuration property:

```
export ANTHROPIC_API_KEY=<INSERT KEY HERE>
```

## Model Context Protocol (MCP)

The [Model Context Protocol][mcp] (MCP) is a standardized
protocol that enables AI models to interact with external tools and resources in a structured way. It supports multiple
transport mechanisms to provide flexibility across different environments.

`Spring AI MCP` extends the MCP Java SDK with Spring Boot integration, providing both client and server starters.

### MCP Client

The MCP Client is a key part in the MCP architecture, responsible for establishing and managing connections with
MCP servers. It implements the client-side of the protocol.

#### Brave Search MCP Server

This project uses the [Brave Search][brave-search-mcp] MCP server, providing both web and local search capabilities.

You will need to create an API key on the Brave Search API [account page][brave-search-api]. The Spring AI project
defines a configuration property named `spring.ai.mcp.client.stdio.connections.brave-search.env.BRAVE_API_KEY` that you
should set to the value of the `API Key` obtained from `Brave Search API`.

Exporting an environment variable is one way to set that configuration property:

```
export BRAVE_API_KEY=<INSERT KEY HERE>
```

The Brave Search MCP server needs the `npx` (package runner for `npm`) to be installed. The simplest way to install it
is to install `Node.js` from the [Node.js download page][node-js-download].

### MCP Server

The MCP Server is a foundational component in the MCP architecture that provides tools, resources, and capabilities to
clients. It implements the server side of the protocol.

## Testing the Chatbot

You can test the API by using [Postman]. Postman is the single platform for designing, building, and scaling APIs.

Once Postman is installed, follow these steps to use the API.

1. Export the environment variables needed by the application to access the LLMs and API Ninjas.
   ```bash
   export ANTHROPIC_API_KEY=<Anthropic Key>
   export API_NINJAS_KEY=<API Ninjas Key>
   export BRAVE_API_KEY=<Brave Search API Key>
   export OPENAI_API_KEY=<OPEN API Key>
   ```
1. Start the application by running `mvn` from a command prompt.
1. In Postman, create a POST request with a URL of `http://localhost:8080/chat`. The body of the request should contain
   the question to ask the chatbot.

To test the MCP client implementation, use this query:

```json
{
  "chatId": null,
  "question": "What's the current weather in Wellington, New Zealand according to Weather Underground?"
}
```

To test the MCP server implementation, use this query:

```json
{
  "chatId": null,
  "question": "What's the current weather at latitude/longitude 41.879895599412045, -87.62928376877025?"
}
```

[anthropic]: https://console.anthropic.com/dashboard
[anthropic-api-keys]: https://console.anthropic.com/settings/keys
[anthropic-models]: https://docs.anthropic.com/en/docs/about-claude/models/all-models
[brave-search-api]: https://brave.com/search/api/
[brave-search-mcp]: https://github.com/modelcontextprotocol/servers/tree/main/src/brave-search
[mcp]: https://modelcontextprotocol.org/docs/concepts/architecture
[node-js-download]: https://nodejs.org/en/download/
[postman]: https://www.postman.com/
