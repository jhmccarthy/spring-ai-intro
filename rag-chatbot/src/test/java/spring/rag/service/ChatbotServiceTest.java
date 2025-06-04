package spring.rag.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.rag.model.ChatRequest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class ChatbotServiceTest {
    @Autowired
    private ChatbotService service;

    @Test
    void chat() {
        var question = new ChatRequest(null, "What is revenue from the movie Sherlock Holmes A Game of Shadows?");

        var result = service.ragChat(question);
        assertNotNull(result);
        log.info("Response: {}", result.answer());
    }
}
