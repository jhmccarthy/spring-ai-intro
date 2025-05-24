package spring.demo.prompts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class BaseTest {
    @Autowired
    @Qualifier("chatClientWithPersonality")
    protected ChatClient chatClient;

    protected String chat(String prompt) {
        var promptTemplate = new PromptTemplate(prompt);
        var promptToSend = promptTemplate.create();

        var response = chatClient
                .prompt(promptToSend)
                .call()
                .content();

        log.info("Chat response: {}", response);

        return response;
    }

    protected String chat(Prompt prompt) {
        var response = chatClient
                .prompt(prompt)
                .call()
                .content();

        log.info("Chat response: {}", response);

        return response;
    }
}
