package guru.springframework.springaiintro.service;

import guru.springframework.springaiintro.model.ChatRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class RagChatbotServiceTest {
    @Autowired
    private RagChatbotService service;

    @Test
    void ragChat() {
        var question = new ChatRequest(null, "What is revenue from the movie Sherlock Holmes A Game of Shadows?");

        var result = service.ragChat(question);
        assertNotNull(result);
        log.info("Response: {}", result.answer());
    }
}
