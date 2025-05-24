package spring.demo.prompts;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.UUID;

@SpringBootTest
@Slf4j
class ZeroAndFewShotTests extends BaseTest {
    private static final String REVIEW = """
            I get it. Everyone is buying these now after years of not caring about Stanley tumblers because of social media. The problem with viral crap like this is we get caught up in fitting in and jumping on the band wagon that we fail to see what's wrong with a product before buying it.
            THIS TUMBLER IS NOT LEAK PROOF. It's not even a little resistant to leaking. Even if you have the top fully closed and the straw taken out, the liquid inside will leak out like crazy if you tip it over even slightly. To me, if I'm going to carry around 30-40oz of hot or cold liquids then the tumbler MUST prevent said liquids from coming out. I understand it's not a water bottle, but that's a lame technicality that Stanley shouldn't cling to. At a MINIMUM the tumbler should be leak proof if I take out the straw and close the top. Furthermore, the sip top closing mechanism seems very flimsy and can be easily bended out of place, so beware of turning it too hard or especially dropping your tumbler.
            I am highly disappointed for being sucked into thinking this was a reliable tumbler that would replace others I have. Granted they are not as nice looking, but they do the job of holding AND containing the water I take with me all day to and from work in NYC.
            I do NOT recommend this tumbler and I would suggest that Stanley fix these important issues instead of focusing on more colors and patterns.""";
    private static final String PROMPT = """
            Identify a list of emotions that the writer of the following reviews is expressing, and provide a brief summary of each review.
            
            Review: ```{review}```
            """;
    /**
     * Few shot - send the model a few examples to help it understand the context of the prompt.
     * <p>
     * Example from <a href="https://arxiv.org/abs/2005.14165">Language Models are Few-Shot Learners</a> paper.
     */
    private static final String WHATPU_PROMPT = """
            A "whatpu" is a small, furry animal native to Tanzania. An example of a sentence that uses the word whatpu is:
            
            We were traveling in Africa and we saw these very cute whatpus.
            
            To do a "farduddle" means to jump up and down really fast. An example of a sentence that uses the word farduddle is:
            
            """;
    private static final String VACATION_PROMPT = """
            John likes white sand beaches and warm weather.
            
            What are 5 locations John should consider for vacation?
            """;
    private static final String MATH_PROMPT = """
            2+2 = twotwo
            3+3 = threethree
            4+5 = fourfive
            
            What is 5+7?
            """;

    /**
     * Zero shot - send the model a single prompt with no hints or examples. Leverages the model's training to generate a response.
     */
    @Test
    void zeroShotPrompt() {
        // java for loop 3 times
        for (int i = 0; i < 3; i++) {
            // java UUID randomUUID is an API cache buster
            var promptTemplate = new PromptTemplate(PROMPT);
            var prompt = promptTemplate.create(
                    Map.of(
                            "review",
                            UUID.randomUUID() + "\n" + REVIEW
                    )
            );

            log.info("#################################\n");
            chat(prompt);
        }
    }

    @Test
    void zeroShotPromptWithChatOptions() {
        var chatOptions = ChatOptions.builder()
                .temperature(0.9) // Creativity level
                .build();

        // java for loop 3 times
        for (int i = 0; i < 3; i++) {
            // java UUID randomUUID is an API cache buster
            var promptTemplate = new PromptTemplate(PROMPT);
            var prompt = promptTemplate.create(
                    Map.of(
                            "review",
                            UUID.randomUUID() + "\n" + REVIEW
                    ),
                    chatOptions
            );

            log.info("#################################\n");
            chat(prompt);
        }
    }

    @Test
    void whatPuPromptFewShot() {
        var promptTemplate = new PromptTemplate(WHATPU_PROMPT);

        chat(promptTemplate.create());
    }

    @Test
    void vacationFewShot() {
        var promptTemplate = new PromptTemplate(VACATION_PROMPT);

        chat(promptTemplate.create());
    }

    @Test
    void mathPromptFewShot() {
        var promptTemplate = new PromptTemplate(MATH_PROMPT);

        chat(promptTemplate.create());
    }

    @Test
    void aiHallucination() {
        var prompt = new Prompt(
                "Write sales copy for the new 'professional grade' " +
                        "Denali Advanced Toothbrush by GMC."
        );

        chat(prompt);
    }
}
