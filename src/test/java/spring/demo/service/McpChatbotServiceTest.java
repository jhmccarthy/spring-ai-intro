package spring.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.demo.model.ChatRequest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class McpChatbotServiceTest {
    @Autowired
    private McpChatbotService service;

    @Test
    void chat() {
        var question = new ChatRequest(null, "What is the current weather in Chicago according to Weather Underground?");

        var result = service.chatMcp(question);
        assertNotNull(result);
        log.info("Response: {}", result.answer());
    }
}
