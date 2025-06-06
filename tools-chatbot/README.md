# Spring AI

This project demonstrates using Spring AI to use tools to extend the functionality of the chatbot.

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

## Testing the Chatbot

You can test the API by using [Postman]. Postman is the single platform for designing, building, and scaling APIs.

Once Postman is installed, follow these steps to use the API.

1. Export the environment variables needed by the application to access the LLMs and API Ninjas.
   ```bash
   export ANTHROPIC_API_KEY=<Anthropic Key>
   export API_NINJAS_KEY=<API Ninjas Key>
   ```
1. Start the application by running `mvn` from a command prompt.
1. In Postman, create a POST request with a URL of `http://localhost:8080/chat`. The body of the request should contain
   the question to ask the chatbot.

To test the information retrieval tool calling, use this query:

```json
{
  "chatId": null,
  "question": "What is current population of New Zealand?"
}
```

To test the information action calling, use this request:

```json
{
  "chatId": null,
  "question": "Set an alarm 2 hours from now."
}
```

[anthropic]: https://console.anthropic.com/dashboard
[anthropic-api-keys]: https://console.anthropic.com/settings/keys
[anthropic-models]: https://docs.anthropic.com/en/docs/about-claude/models/all-models
[api-ninjas]: https://api-ninjas.com/
[api-ninjas-country]: https://api-ninjas.com/api/country
[api-ninjas-register]: https://api-ninjas.com/register
[postman]: https://www.postman.com/
