package spring.basichat.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spring.basicchat.model.ChatRequest;
import spring.basicchat.service.ChatbotService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Slf4j
class ChatbotServiceTest {
    @Autowired
    private ChatbotService service;

    @Test
    void chat() {
        var question = new ChatRequest(null, "There are 3 killers in a room. Someone enters the room and kills one of them. How many killers are left in the room?");

        var result = service.chat(question);
        assertNotNull(result);
        log.info("Response: {}", result.answer());
    }

    @Test
    void getCapital() {
        var question = new ChatRequest(null, "New Zealand");

        var result = service.getCapital(question);
        assertNotNull(result);
        log.info("Response: {}", result.answer());
    }

    @Test
    void getCapitalWithInfo() {
        var question = new ChatRequest(null, "New Zealand");

        var result = service.getCapitalWithInfo(question);
        assertNotNull(result);
        log.info("Response: {}", result.answer());
    }
}
