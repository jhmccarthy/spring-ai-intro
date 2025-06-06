# Spring AI

This project demonstrates using Spring AI to build a chatbot that supports Retrieval Augmented Generation (RAG).

RAG is a technique useful to overcome the limitations of large language models that struggle with long-form content,
factual accuracy, and context-awareness.

In the `application.yml` file, the `spring.ai.model.chat` configuration property allows you to define which LLM you want
to test.

## LLM Models

The following LLM and embedding model are used in this project.

| Vendor | LLM Model | Embedding Model |
|--------|---------------|--------------------------|
| OpenAI | [gpt-4o-mini] | [text-embedding-3-small] |

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

## Vector Database

A [vector database][vector-db] is a specialized type of database that plays an essential role in AI applications.

In vector databases, queries differ from traditional relational databases. Instead of exact matches, they perform
similarity searches. When given a vector as a query, a vector database returns vectors that are “similar” to the query
vector.

This project uses the [SimpleVectorStore][simple-vector-store] that creates a file-base vector store. When the
application
starts, it creates a `vectorStore.json` file from a list of the top 500 grossing movies that are found in the
`src/main/resources/movies500.cvs` file.

When the application starts, Spring AI uses an embedding model from OpenAI to load and query the data.

Given a reference to a source file that represents a JSON file with data we want to load into the vector
database, we use Spring AI’s `JsonReader` to load specific fields in the JSON, which splits them up into small pieces
and then passes those small pieces to the vector store implementation. The `VectorStore` implementation computes the
embeddings and stores the JSON and the embedding in the vector database

Later, when a user question is passed into the AI model, a similarity search is done to retrieve similar documents,
which are then "'stuffed'" into the prompt as context for the user’s question.

## Testing the Chatbot

You can test the API by using [Postman]. Postman is the single platform for designing, building, and scaling APIs.

Once Postman is installed, follow these steps to use the API.

1. Export the environment variables needed by the application to access the LLMs and API Ninjas.
   ```bash
   export OPENAI_API_KEY=<OPEN API Key>
   ```
1. Start the application by running `mvn` from a command prompt.
1. In Postman, create a POST request with a URL of `http://localhost:8080/chat`. The body of the request should contain
   the question to ask the chatbot.

```json
{
  "chatId": null,
  "question": "What is revenue from the movie Les Miserables?"
}
```

[gpt-4o-mini]: https://platform.openai.com/docs/models/gpt-4o-mini
[openai]: https://platform.openai.com/signup
[openai-api-keys]: https://platform.openai.com/account/api-keys
[postman]: https://www.postman.com/
[simple-vector-store]: https://github.com/spring-projects/spring-ai/blob/main/spring-ai-vector-store/src/main/java/org/springframework/ai/vectorstore/SimpleVectorStore.java
[text-embedding-3-small]: https://platform.openai.com/docs/models/text-embedding-3-small
[vector-db]: https://docs.spring.io/spring-ai/reference/api/vectordbs.html
