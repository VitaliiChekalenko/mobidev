package vitaliichekalenko.javafxmobidevtestapp.model;

import java.util.List;

public class Question {
    private final String text;
    private final List<String> options;
    private final int correctIndex;

    public Question(String text, List<String> options, int correctIndex) {
        this.text = text;
        this.options = options;
        this.correctIndex = correctIndex;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public boolean checkAnswer(String answer) {
        return options.get(correctIndex).equals(answer);
    }
}
