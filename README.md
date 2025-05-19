# Introduction to Spring AI

This repository contains source code examples used to support my on-line courses about the Spring Framework.

## LLM Models

The following LLM and embedding models have been tested.

| Vendor    | LLM Model                                    | Embedding Model          |
|-----------|----------------------------------------------|--------------------------|
| OpenAI    | [gpt-4o-mini]                                | [text-embedding-3-small] |
| Anthropic | [claude-3-7-sonnet-latest][anthropic-models] |                          |
| Ollama    | [ollama3.2]                                  | [nomic-embed-text]       |

*NOTE:* Spring AI doesn't have support for the Anthropic embedding model. You can use either the OpenAI or Ollama's
embedding model.

In the `application.yml` file, update the `spring.ai.model.embedding` parameter to choose which embedding model to use.
*Note* that you need to rebuild the vector store file when switching between the different embedding models.

### OpenAI

You will need to create an API with OpenAI to access ChatGPT models. Create an account at [OpenAI signup page][openai]
and generate the token on the [API Keys page][openai-api-keys]. The Spring AI project defines a configuration property
named `spring.ai.openai.api-key` that you should set to the value of the `API Key` obtained from openai.com. Exporting
an environment variable is one way to set that configuration property:

```
export OPENAI_API_KEY=<INSERT KEY HERE>
```

### Anthropic

You will need to create an API key on the Anthropic portal. Create an account at
the [Anthropic API dashboard][anthropic] and generate the api key on the [Get API Keys][anthropic-api-keys] page. The
Spring AI project defines a configuration property named `spring.ai.anthropic.api-key` that you should set to the value
of the `API Key` obtained from `anthropic.com`. Exporting an environment variable is one way to set that configuration
property:

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

| Description                             | Command                |
|-----------------------------------------|------------------------|
| Pull a model                            | `ollama pull llama3.2` |
| Remove a model                          | `ollama rm llama3.2`   |
| Show model information                  | `ollama show llama3.2` |
| List models on your computer            | `ollama list`          |
| List which models are currently loaded  | `ollama ps`            |
| Stop a model which is currently running | `ollama stop llama3.2` |

## Spring AI Functions

This project uses the [API Ninjas][api-ninjas] [Country API][api-ninjas-country] to define a callback function used by
the chatbot.

You will need to create an API key on the API Ninjas [register page][api-ninjas-register]. The Spring AI project defines
a configuration property named `ai-app.api-ninjas-key` that you should set to the value of the `API Key` obtained from
`api-ninjas.com`. Exporting an environment variable is one way to set that configuration property:

```
export API_NINJAS_KEY=<INSERT KEY HERE>
```

[anthropic]: https://console.anthropic.com/dashboard

[anthropic-api-keys]: https://console.anthropic.com/settings/keys

[anthropic-models]: https://docs.anthropic.com/en/docs/about-claude/models/all-models

[openai]: https://platform.openai.com/signup

[openai-api-keys]: https://platform.openai.com/account/api-keys

[gpt-4o-mini]: https://platform.openai.com/docs/models/gpt-4o-mini

[text-embedding-3-small]: https://platform.openai.com/docs/models/text-embedding-3-small

[ollama]: https://ollama.com/download

[ollama-cli-reference]: https://github.com/ollama/ollama?tab=readme-ov-file#cli-reference

[ollama3.2]: https://ollama.com/library/llama3.2

[nomic-embed-text]: https://ollama.com/library/nomic-embed-text

[api-ninjas]: https://api-ninjas.com/

[api-ninjas-register]: https://api-ninjas.com/register

[api-ninjas-country]: https://api-ninjas.com/api/country
