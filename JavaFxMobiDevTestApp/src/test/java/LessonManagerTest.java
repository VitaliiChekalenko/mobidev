import org.junit.jupiter.api.Test;
import vitaliichekalenko.javafxmobidevtestapp.model.Question;
import vitaliichekalenko.javafxmobidevtestapp.service.LessonManager;

import static org.junit.jupiter.api.Assertions.*;

public class LessonManagerTest {

    @Test
    void testSingletonInstance() {
        LessonManager instance1 = LessonManager.getInstance();
        LessonManager instance2 = LessonManager.getInstance();
        assertSame(instance1, instance2, "LessonManager should return the same instance");
    }

    @Test
    void testSampleQuestionIsValid() {
        Question question = LessonManager.getInstance().getSampleQuestion();
        assertNotNull(question.getText());
        assertEquals(4, question.getOptions().size());
        assertTrue(question.getOptions().contains("manzana"));
        assertTrue(question.checkAnswer("manzana"));
    }
}
