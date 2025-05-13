package guru.springframework.springaiintro.promptengineering;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Chain of thought - adding a series of intermediate reasoning steps to the prompt.
 * See <a href="https://arxiv.org/abs/2201.11903">Chain-of-Thought Prompting Elicits Reasoning in Large Language Models</a>
 */
@SpringBootTest
class ChainOfThoughtTests extends BaseTest {
    @Test
    void traditionalPrompt() {
        var prompt = """
                Q: Roger has 5 tennis balls. He buys 2 more cans of tennis balls, each containing 3 balls.
                How many tennis balls does Roger have now?
                """;

        var promptTemplate = new PromptTemplate(prompt);

        //models previously would answer 27
        System.out.println(
                chatModel.call(promptTemplate.create()).getResult().getOutput().getText()
        );
    }

    @Test
    void chainOfThroughPrompt() {
        var chainOfThoughtPrompt = """
                Q: Roger has 5 tennis balls. He buys 2 more cans of tennis balls, each containing 3 balls.
                How many tennis balls does Roger have now?
                
                A: Roger started with 5 balls. 2 cans of 3 balls each is 6 balls. 5 + 6 = 11. So Roger has 11 tennis balls.
                
                Q: The cafeteria had 23 apples originally. They used 20 apples to make lunch and bought 6 more. How many
                apples does the cafeteria have now?
                """;

        var promptTemplate = new PromptTemplate(chainOfThoughtPrompt);

        System.out.println(
                chatModel.call(promptTemplate.create()).getResult().getOutput().getText()
        );
    }

    @Test
    void traditionalPrompt2() {
        var prompt = """
                Q: Roger has 5 tennis balls. He buys 2 more cans of tennis balls, each containing 3 balls.
                How many tennis balls does Roger have now? Answer in 1 word.
                """;

        var promptTemplate = new PromptTemplate(prompt);

        //models previously would answer 27
        System.out.println(
                chatModel.call(promptTemplate.create()).getResult().getOutput().getText()
        );
    }
}
