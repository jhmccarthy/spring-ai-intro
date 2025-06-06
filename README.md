# Spring AI

This project contains examples of using [Spring AI][spring-ai] to work with Large Language Models (LLM).

The Spring AI project aims to streamline the development of applications that incorporate artificial intelligence
functionality without unnecessary complexity.

Spring AI provides abstractions that serve as the foundation for developing AI applications. These abstractions have
multiple implementations, enabling easy component swapping with minimal code changes.

This project uses these Spring components.

- Spring AI, 1.0.0
- Spring Boot, 3.5.0

## Sample projects

- [basic-chat](./basic-chatbot/README.md) - demonstrates using Spring AI to build a basic chatbot using multiple LLMs.
- [rag-chat](./rag-chatbot/README.md) - demonstrates using Spring AI to build a chatbot that supports Retrieval
  Augmented Generation (RAG).
- [tools-chat](./tools-chatbot/README.md) - demonstrates using Spring AI to use tools to extend the functionality of the
  chatbot.
- [mcp-chat](./mcp-chatbot/README.md) - demonstrates using a Model Context Protocol (MCP) to use external tools and
  resources in a structured way.

## LLM Models

The following LLMs and embedding models have been tested.

| Vendor | LLM Model | Embedding Model |
|-------------------|----------------------------------------------|--------------------------|
| [OpenAI] | [gpt-4o-mini] | [text-embedding-3-small] |
| [Anthropic] | [claude-3-7-sonnet-latest][anthropic-models] | |
| [Ollama] | [ollama3.2] | [nomic-embed-text] |

*NOTE:* Spring AI doesn't have support for the Anthropic embedding model. You can use either the OpenAI or Ollama's
embedding model.

[anthropic]: https://console.anthropic.com/dashboard
[anthropic-models]: https://docs.anthropic.com/en/docs/about-claude/models/all-models
[gpt-4o-mini]: https://platform.openai.com/docs/models/gpt-4o-mini
[nomic-embed-text]: https://ollama.com/library/nomic-embed-text
[ollama]: https://ollama.com/download
[ollama3.2]: https://ollama.com/library/llama3.2
[openai]: https://platform.openai.com/signup
[spring-ai]: https://docs.spring.io/spring-ai/reference/index.html
[text-embedding-3-small]: https://platform.openai.com/docs/models/text-embedding-3-small
