package spring.demo.prompts;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class MakingTheModelThinkTests extends BaseTest {
    private static final String STORY = """
            In a charming village, siblings Jack and Jill set out on
            a quest to fetch water from a hilltop well.
            As they climbed, singing joyfully, misfortune
            struck—Jack tripped on a stone and tumbled
            down the hill, with Jill following suit.
            Though slightly battered, the pair returned home to
            comforting embraces. Despite the mishap,
            their adventurous spirits remained undimmed, and they
            continued exploring with delight.
            """;
    private static final String PROMPT = """
            Perform the following actions:
            1 - Summarize the following text delimited by triple
            backticks with 1 sentence.
            2 - Translate the summary into German.
            3 - List each name in the German summary.
            4 - Output a json object that contains the following
            keys: german_summary, num_names.
            Separate your answers with line breaks.
            Text:
            ```{text}```
            """;
    private static final String PROMPT_2_INCORRECT = """
            Determine if the student's solution is correct or not.
            
            Question:
            I'm building a solar power installation and I need
             help working out the financials.
            - Land costs $100 / square foot
            - I can buy solar panels for $250 / square foot
            - I negotiated a contract for maintenance that will cost
            me a flat $100k per year, and an additional $10 / square foot
            
            What is the total cost for the first year of operations
            as a function of the number of square feet.
            
            Student's Solution:
            Let x be the size of the installation in square feet.
            Costs:
            1. Land cost: 100x
            2. Solar panel cost: 250x
            3. Maintenance cost: 100,000 + 100x
            Total cost: 100x + 250x + 100,000 + 100x = 450x + 100,000
            """;
    private static final String PROMPT_3_CORRECT = """
            Your task is to determine if the student's solution is correct or not.
            To solve the problem do the following:
            - First, work out your own solution to the problem including the final total.
            - Then compare your solution to the student's solution and evaluate if the student's solution is correct or not.
            
            Don't decide if the student's solution is correct until you have done the problem yourself.
            
            Use the following format:
            Question:
            ```question here```
            
            Student's solution:
            ```student's solution here```
            
            Actual solution:
            ```steps to work out the solution and your solution here```
            
            Is the student's solution the same as actual solution just calculated:
            ```yes or no```
            
            Student grade:
            ```correct or incorrect```
            
            Question:
            ```
            I'm building a solar power installation and I need help working out the financials.
            - Land costs $100 / square foot
            - I can buy solar panels for $250 / square foot
            - I negotiated a contract for maintenance that will cost me a flat $100k per year, and an additional $10 / square foot
            
            What is the total cost for the first year of operations as a function of the number of square feet.
            ```
            
            Student's solution:
            ```
            Let x be the size of the installation in square feet.
            Costs:
            1. Land cost: 100x
            2. Solar panel cost: 250x
            3. Maintenance cost: 100,000 + 100x
            Total cost: 100x + 250x + 100,000 + 100x = 450x + 100,000
            ```
            
            Actual solution:
            ```actual solution here```
            
            """;
    private static final String PROMPT_4 = """
            You are an expert at solving reasoning problems. A cup is an object with an open top and close on the sides and bottom. The open top does not prevent objects from passing through it.
            
            Assume the laws of physics on Earth. A small marble is put into a normal cup and the cup is placed upside down on a table,
             causing the open side of the cup to be in contact with the table. Gravity will cause the ball to fall to the table.
            Someone then picks the cup up without changing its orientation and puts it inside the microwave. Where is the ball now. Determine the position of the ball in each step. Explain
            why the ball is positioned where it is.
            """;

    @Test
    void testSteps() {
        var promptTemplate = new PromptTemplate(PROMPT);
        var prompt = promptTemplate.create(
                Map.of("text", STORY)
        );

        chat(prompt);
    }

    @Test
    void incorrectPrompt() {
        var promptTemplate = new PromptTemplate(PROMPT_2_INCORRECT);

        chat(promptTemplate.create());
    }

    @Test
    void correctPrompt() {
        var promptTemplate = new PromptTemplate(PROMPT_3_CORRECT);

        chat(promptTemplate.create());
    }

    @Test
    void theBallPrompt() {
        var promptTemplate = new PromptTemplate(PROMPT_4);

        chat(promptTemplate.create());
    }
}
