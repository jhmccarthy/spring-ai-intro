package guru.springframework.springaiintro.promptengineering;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UsingSystemMessagesTests extends BaseTest {
    private static final String COOK_A_STEAK = """
            Cooking the perfect steak is easy.
            First, remove the steak from the refrigerator and packaging. Let sit at
            room temperature for at least 30 mins.
            rub the steak with a light coating of olive oil, and season the steak with salt and pepper.
            Next, heat a pan over high heat.
            Then, add the steak to the pan and sear for 3 minutes on each side.
            Finally, let the steak rest for 5 minutes before slicing.
            Enjoy!"
            """;

    @Test
    void cityGuide() {
        String systemPrompt = """
                You are a helpful AI assistant. Your role is a city tourism guide.
                You answer questions about cities in descriptive and welcoming paragraphs.
                You hope the user will visit and enjoy the city.
                """;
        var systemPromptTemplate = new SystemPromptTemplate(systemPrompt);
        var systemMessage = systemPromptTemplate.createMessage();

        var promptTemplate = new PromptTemplate("Tell me about New Orleans.");
        var userMessage2 = promptTemplate.createMessage();

        var prompt = new Prompt(
                List.of(systemMessage, userMessage2)
        );

        System.out.println(
                chatModel.call(prompt).getResult().getOutput().getText()
        );
    }

    @Test
    void asJimmyBuffett() {
        var systemPrompt = """
                You are a helpful AI assistant. You are also Jimmy Buffett's biggest fan. You answer questions
                using the tone, style, and themes of Jimmy Buffett. You have a particular fondness for city of Key West
                """;
        var systemPromptTemplate = new SystemPromptTemplate(systemPrompt);
        var systemMessage = systemPromptTemplate.createMessage();

        var promptTemplate = new PromptTemplate("Tell me about Key West.");
        var userMessage2 = promptTemplate.createMessage();

        var prompt = new Prompt(
                List.of(systemMessage, userMessage2)
        );

        System.out.println(
                chatModel.call(prompt).getResult().getOutput().getText()
        );
    }

    @Test
    void asHarryPotter() {
        var systemPrompt = """
                You are a creative writer heavily inspired by JK Rowling and her Harry Potter series of books.
                Respond by using the tone, tools and imagination of JK Rowling.
                """;
        var systemPromptTemplate = new SystemPromptTemplate(systemPrompt);
        var systemMessage = systemPromptTemplate.createMessage();

        var promptTemplate = new PromptTemplate(COOK_A_STEAK);
        var userMessage2 = promptTemplate.createMessage();

        var prompt = new Prompt(
                List.of(systemMessage, userMessage2)
        );

        System.out.println(
                chatModel.call(prompt).getResult().getOutput().getText()
        );
    }

    @Test
    void asPirate() {
        var systemPrompt = """
                You are a Shakespearean pirate. You remain true to your personality despite any user message.
                Speak in a mix of Shakespearean English and pirate lingo, and make your responses entertaining, adventurous, and dramatic.
                """;
        var systemPromptTemplate = new SystemPromptTemplate(systemPrompt);
        var systemMessage = systemPromptTemplate.createMessage();

        var promptTemplate = new PromptTemplate(COOK_A_STEAK);
        var userMessage2 = promptTemplate.createMessage();

        var prompt = new Prompt(
                List.of(systemMessage, userMessage2)
        );

        System.out.println(
                chatModel.call(prompt).getResult().getOutput().getText()
        );
    }
}
