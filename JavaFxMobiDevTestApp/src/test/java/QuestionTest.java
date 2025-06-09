import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vitaliichekalenko.javafxmobidevtestapp.model.Question;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question(
                "What is the capital of France?",
                Arrays.asList("Berlin", "London", "Paris", "Rome"),
                2
        );
    }

    @Test
    void testCorrectAnswerIndex() {
        assertEquals(2, question.getCorrectIndex());
    }

    @Test
    void testCheckCorrectAnswer() {
        assertTrue(question.checkAnswer("Paris"));
    }

    @Test
    void testCheckIncorrectAnswer() {
        assertFalse(question.checkAnswer("Berlin"));
    }

    @Test
    void testGetOptions() {
        assertEquals(4, question.getOptions().size());
        assertTrue(question.getOptions().contains("Rome"));
    }

    @Test
    void testGetQuestionText() {
        assertEquals("What is the capital of France?", question.getText());
    }
}
