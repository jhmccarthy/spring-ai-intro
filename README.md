# Introduction to Spring AI

This repository contains source code examples used to support my on-line courses about the Spring Framework.

## LLM Models

The following LLM and embedding models have been tested.

| Vendor    | Model                                        | Embedding                |
|-----------|----------------------------------------------|--------------------------|
| OpenAI    | [gpt-4o-mini]                                | [text-embedding-3-small] |
| Anthropic | [claude-3-7-sonnet-latest][anthropic-models] |                          |
| Ollama    | [ollama3.2]                                  | [nomic-embed-text]       |

*NOTE:* Spring AI doesn't have support an Anthropic embedding model. You can use either the OpenAI or Ollama's embedding
model.

In the `application.yml` file, update the `spring.ai.model.embedding` parameter to choose which embedding model to use.
Note, that you need to rebuild the vector store file when switching between the different embedding models.

### OpenAI

### Anthropic

### Ollama

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

## All Spring Framework Guru Courses

### Spring Framework 6

* [Spring Framework 6 - Beginner to Guru](https://www.udemy.com/course/spring-framework-6-beginner-to-guru/?referralCode=2BD0B7B7B6B511D699A9)
* [Hibernate and Spring Data JPA: Beginner to Guru](https://www.udemy.com/course/hibernate-and-spring-data-jpa-beginner-to-guru/?referralCode=251C4C865302C7B1BB8F)
* [API First Engineering with Spring Boot](https://www.udemy.com/course/api-first-engineering-with-spring-boot/?referralCode=C6DAEE7338215A2CF276)
* [Introduction to Kafka with Spring Boot](https://www.udemy.com/course/introduction-to-kafka-with-spring-boot/?referralCode=15118530CA63AD1AF16D)
* [Spring Security: Beginner to Guru](https://www.udemy.com/course/spring-security-core-beginner-to-guru/?referralCode=306F288EB78688C0F3BC)

### Spring Framework 5

* [Spring Framework 5: Beginner to Guru](https://www.udemy.com/testing-spring-boot-beginner-to-guru/?couponCode=GITHUB_REPO) -
  Get the most modern and comprehensive course available for the Spring Framework! Join over 17,200 over Guru's in an
  Slack community exclusive to this course! More than 5,700 students have given this 53 hour course a 5 star review!
* [Spring Boot Microservices with Spring Cloud Beginner to Guru](https://www.udemy.com/course/spring-boot-microservices-with-spring-cloud-beginner-to-guru/?referralCode=6142D427AE53031FEF38) -
  Master Microservice Architectures Using Spring Boot 2 and Cloud Based Deployments with Spring Cloud and Docker
* [Reactive Programming with Spring Framework 5](https://www.udemy.com/reactive-programming-with-spring-framework-5/?couponCode=GITHUB_REPO_SF5B2G) -
  Keep your skills razor sharp and take a deep dive into Reactive Programming!
* [Testing Spring Boot: Beginner to Guru](https://www.udemy.com/testing-spring-boot-beginner-to-guru/?couponCode=GITHUB_REPO_SF5B2G) - **
  Best Selling Course** Become an expert in testing Java and Spring Applications with JUnit 5, Mockito and much more!

### SQL

* [SQL Beginner to Guru: MySQL Edition](https://www.udemy.com/sql-beginner-to-guru-mysql-edition/?couponCode=GITHUB_REPO_SF5B2G) -
  SQL is a fundamental must have skill, which employers are looking for. Learn to master SQL on MySQL, the worlds most
  popular database!

### DevOps

* [Apache Maven: Beginner to Guru](https://www.udemy.com/apache-maven-beginner-to-guru/?couponCode=GITHUB_REPO_SF5B2G) -
  **Best Selling Course** Take the mystery out of Apache Maven. Learn how to use Maven to build your Java and Spring
  Boot projects!
* [OpenAPI: Beginner to Guru](https://www.udemy.com/course/openapi-beginner-to-guru/?referralCode=0E7F511C749013CA6AAD) -
  Master OpenAPI (formerly Swagger) to Create Specifications for Your APIs
* [OpenAPI: Specification With Redocly](https://www.udemy.com/course/openapi-specification-redocly-api-documentation/?referralCode=863C443928D61D9A3831)
* [Docker for Java Developers](https://www.udemy.com/docker-for-java-developers/?couponCode=GITHUB_REPO_SF5B2G) - Best
  Selling Course on Udemy! Learn how you can supercharge your development by leveraging Docker. Collaborate with other
  students in a Slack community exclusive to the course!
* [Spring Framework DevOps on AWS](https://www.udemy.com/spring-core-devops-on-aws/?couponCode=GITHUB_REPO_SF5B2G) -
  Learn how to build and deploy Spring applications on Amazon AWS!
* [Ready for Production with Spring Boot Actuator](https://www.udemy.com/ready-for-production-with-spring-boot-actuator/?couponCode=GITHUB_REPO_SF5B2G) -
  Learn how to leverage Spring Boot Actuator to monitor your applications running in production.

### Web Development with Spring Framework

* [Mastering Thymeleaf with Spring Boot](https://www.udemy.com/mastering-thymeleaf-with-spring/?couponCode=GITHUB_REPO_SF5B2G) -
  Once you learn Thymeleaf, you'll never want to go back to using JSPs for web development!

## Connect with Spring Framework Guru

* Spring Framework Guru [Blog](https://springframework.guru/)
* Subscribe to Spring Framework Guru on [YouTube](https://www.youtube.com/channel/UCrXb8NaMPQCQkT8yMP_hSkw)
* Like Spring Framework Guru on [Facebook](https://www.facebook.com/springframeworkguru/)
* Follow Spring Framework Guru on [Twitter](https://twitter.com/spring_guru)
* Connect with John Thompson on [LinkedIn](http://www.linkedin.com/in/springguru)

[anthropic-models]: https://docs.anthropic.com/en/docs/about-claude/models/all-models

[gpt-4o-mini]: https://platform.openai.com/docs/models/gpt-4o-mini

[text-embedding-3-small]: https://platform.openai.com/docs/models/text-embedding-3-small

[ollama-cli-reference]: https://github.com/ollama/ollama?tab=readme-ov-file#cli-reference

[ollama3.2]: https://ollama.com/library/llama3.2

[nomic-embed-text]: https://ollama.com/library/nomic-embed-text
