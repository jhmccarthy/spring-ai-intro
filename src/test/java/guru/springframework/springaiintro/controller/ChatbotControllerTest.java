package guru.springframework.springaiintro.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ChatbotControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void chat() throws Exception {
        var question = "{ \"question\": \"A question for\"}";

        mvc.perform(post("/request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(question))
                .andExpect(status().isOk());
    }

    @Test
    void ragChat() throws Exception {
        var question = "{ \"question\": \"A question for\"}";

        mvc.perform(post("/rag-request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(question))
                .andExpect(status().isOk());
    }

    @Test
    void getCountry() throws Exception {
        var question = "{ \"question\": \"A question for\"}";

        mvc.perform(post("/country")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(question))
                .andExpect(status().isOk());
    }
}
