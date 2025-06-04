# Spring AI

This project contains examples of using [Spring AI][spring-ai] to work with Large Language Models (LLM).

The Spring AI project aims to streamline the development of applications that incorporate artificial intelligence
functionality without unnecessary complexity.

Spring AI provides abstractions that serve as the foundation for developing AI applications. These abstractions have
multiple implementations, enabling easy component swapping with minimal code changes.

This project uses these Spring components.

- Spring AI, 1.0.0
- Spring Boot, 3.5.0

## LLM Models

The following LLMs and embedding models have been tested.

| Vendor | LLM Model | Embedding Model |
|-----------|----------------------------------------------|--------------------------|
| OpenAI | [gpt-4o-mini] | [text-embedding-3-small] |
| Anthropic | [claude-3-7-sonnet-latest][anthropic-models] | |
| Ollama | [ollama3.2] | [nomic-embed-text] |

*NOTE:* Spring AI doesn't have support for the Anthropic embedding model. You can use either the OpenAI or Ollama's
embedding model.

In the `application.yml` file, update the `spring.ai.model.embedding` parameter to choose which embedding model to use.
*Note* that you need to rebuild the vector store file when switching between the different embedding models.

### OpenAI

You will need to create an API with OpenAI to access ChatGPT models. Create an account at [OpenAI signup page][openai]
and generate the token on the [API Keys page][openai-api-keys]. The Spring AI project defines a configuration property
named `spring.ai.openai.api-key` that you should set to the value of the `API Key` obtained from openai.com.

Exporting an environment variable is one way to set that configuration property:

```
export OPENAI_API_KEY=<INSERT KEY HERE>
```

### Anthropic

You will need to create an API key on the Anthropic portal. Create an account at
the [Anthropic API dashboard][anthropic] and generate the api key on the [Get API Keys][anthropic-api-keys] page. The
Spring AI project defines a configuration property named `spring.ai.anthropic.api-key` that you should set to the value
of the `API Key` obtained from `anthropic.com`.

Exporting an environment variable is one way to set that configuration property:

```
export ANTHROPIC_API_KEY=<INSERT KEY HERE>
```

### Ollama

Ollama runs as a native Windows application, including NVIDIA and AMD Radeon GPU support. Download the application from
the [Ollama Download page][ollama].

After installing Ollama for Windows, Ollama will run in the background and the `ollama` command line is available in
`cmd`, PowerShell or your favorite terminal application.

By default, the Ollama API will be served on http://localhost:11434.

#### CLI Reference

This is an abbreviated list of the [Ollama CLI commands][ollama-cli-reference].

| Description | Command |
|-----------------------------------------|------------------------|
| Pull a model | `ollama pull llama3.2` |
| Remove a model | `ollama rm llama3.2` |
| Show model information | `ollama show llama3.2` |
| List models on your computer | `ollama list` |
| List which models are currently loaded | `ollama ps` |
| Stop a model which is currently running | `ollama stop llama3.2` |

## Tool Calling

Tool calling (also known as function calling) is a common pattern in AI applications allowing a model to interact with a
set of APIs, or tools, augmenting its capabilities.

Tools are mainly used for:

- **Information Retrieval**. Tools in this category can be used to retrieve information from external sources, such as a
  database, a web service, a file system, or a web search engine. The goal is to augment the knowledge of the model,
  allowing it to answer questions that it would not be able to answer otherwise. As such, they can be used in Retrieval
  Augmented Generation (RAG) scenarios.
- **Taking Action**. Tools in this category can be used to take action in a software system, such as sending an email,
  creating a new record in a database, submitting a form, or triggering a workflow. The goal is to automate tasks that
  would otherwise require human intervention or explicit programming.

### Methods as Tools

Spring AI provides built-in support for specifying tools (i.e. `ToolCallback`(s)) from methods in two ways:

- declaratively, using the `@Tool` annotation
- programmatically, using the low-level `MethodToolCallback` implementation.

This project uses the declarative approach to allow the model to determine the current date and time in the user’s time
zone and set an alarm via a logging message.

### Functions as Tools

Spring AI provides built-in support for specifying tools from functions, either programmatically using the low-level
`FunctionToolCallback` implementation or dynamically as `@Bean`(s) resolved at runtime.

This project uses the [API Ninjas][api-ninjas] [Country API][api-ninjas-country] to define a callback function used by
the chatbot.

You will need to create an API key on the API Ninjas [register page][api-ninjas-register]. The Spring AI project defines
a configuration property named `ai-app.api-ninjas-key` that you should set to the value of the `API Key` obtained from
`api-ninjas.com`.

Exporting an environment variable is one way to set that configuration property:

```
export API_NINJAS_KEY=<INSERT KEY HERE>
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

```json
{
  "chatId": null,
  "question": "There are 3 killers in a room. Someone enters the room and kills one of them. How many killers are left in the room?"
}
```

[anthropic]: https://console.anthropic.com/dashboard
[anthropic-api-keys]: https://console.anthropic.com/settings/keys
[anthropic-models]: https://docs.anthropic.com/en/docs/about-claude/models/all-models
[api-ninjas]: https://api-ninjas.com/
[api-ninjas-country]: https://api-ninjas.com/api/country
[api-ninjas-register]: https://api-ninjas.com/register
[brave-search-api]: https://brave.com/search/api/
[brave-search-mcp]: https://github.com/modelcontextprotocol/servers/tree/main/src/brave-search
[gpt-4o-mini]: https://platform.openai.com/docs/models/gpt-4o-mini
[mcp]: https://modelcontextprotocol.org/docs/concepts/architecture
[node-js-download]: https://nodejs.org/en/download/
[nomic-embed-text]: https://ollama.com/library/nomic-embed-text
[ollama]: https://ollama.com/download
[ollama-cli-reference]: https://github.com/ollama/ollama?tab=readme-ov-file#cli-reference
[ollama3.2]: https://ollama.com/library/llama3.2
[openai]: https://platform.openai.com/signup
[openai-api-keys]: https://platform.openai.com/account/api-keys
[postman]: https://www.postman.com/
[spring-ai]: https://docs.spring.io/spring-ai/reference/index.html
[text-embedding-3-small]: https://platform.openai.com/docs/models/text-embedding-3-small
