package vitaliichekalenko.javafxmobidevtestapp.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vitaliichekalenko.javafxmobidevtestapp.model.Question;
import vitaliichekalenko.javafxmobidevtestapp.service.LessonManager;
import vitaliichekalenko.javafxmobidevtestapp.service.UserManager;

public class LessonView {
    private VBox view;
    private Question currentQuestion;
    private Label feedback;

    public LessonView(Stage stage) {
        view = new VBox(10);
        view.setPadding(new Insets(20));

        currentQuestion = LessonManager.getInstance().getSampleQuestion();

        Label questionLabel = new Label(currentQuestion.getQuestion());
        ToggleGroup group = new ToggleGroup();
        VBox optionsBox = new VBox(5);

        for (String option : currentQuestion.getOptions()) {
            RadioButton rb = new RadioButton(option);
            rb.setToggleGroup(group);
            optionsBox.getChildren().add(rb);
        }

        Button submit = new Button("Submit");
        feedback = new Label();

        submit.setOnAction(e -> {
            RadioButton selected = (RadioButton) group.getSelectedToggle();
            if (selected != null) {
                boolean correct = currentQuestion.checkAnswer(selected.getText());
                feedback.setText(correct ? "Correct!" : "Wrong!");
                if (correct) UserManager.getInstance().getUser().addXp(10);
            }
        });

        Button back = new Button("Back");
        back.setOnAction(e -> {
            DashboardView dashboard = new DashboardView(stage);
            stage.setScene(new Scene(dashboard.getView(), 500, 400));
        });

        view.getChildren().addAll(questionLabel, optionsBox, submit, feedback, back);
    }

    public VBox getView() {
        return view;
    }
}
