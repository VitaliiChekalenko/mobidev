package vitaliichekalenko.javafxmobidevtestapp.service;


import vitaliichekalenko.javafxmobidevtestapp.model.Question;

import java.util.Arrays;

public class LessonManager {
    private static final LessonManager instance = new LessonManager();

    private LessonManager() {}

    public static LessonManager getInstance() {
        return instance;
    }

    public Question getSampleQuestion() {
        return new Question(
                "What is the Spanish word for 'apple'?",
                Arrays.asList("manzana", "pera", "uva", "naranja"),
                0
        );
    }
}
