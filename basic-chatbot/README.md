# Spring AI - Basic Chatbot

This project demonstrates using Spring AI to build a basic chatbot using multiple LLMs.

In the `application.yml` file, the `spring.ai.model.chat` configuration property allows you to define which LLM you want
to test.

## LLM Models

The following LLMs have been tested.

| Vendor | LLM Model |
|-----------|----------------------------------------------|
| OpenAI | [gpt-4o-mini] |
| Anthropic | [claude-3-7-sonnet-latest][anthropic-models] |
| Ollama | [ollama3.2] |

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

## Testing the Chatbot

You can test the API by using [Postman]. Postman is the single platform for designing, building, and scaling APIs.

Once Postman is installed, follow these steps to use the API.

1. Export the environment variables needed by the application to access the LLMs and API Ninjas.
   ```bash
   export ANTHROPIC_API_KEY=<Anthropic Key>
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
[gpt-4o-mini]: https://platform.openai.com/docs/models/gpt-4o-mini
[ollama]: https://ollama.com/download
[ollama-cli-reference]: https://github.com/ollama/ollama?tab=readme-ov-file#cli-reference
[ollama3.2]: https://ollama.com/library/llama3.2
[openai]: https://platform.openai.com/signup
[openai-api-keys]: https://platform.openai.com/account/api-keys
[postman]: https://www.postman.com/
