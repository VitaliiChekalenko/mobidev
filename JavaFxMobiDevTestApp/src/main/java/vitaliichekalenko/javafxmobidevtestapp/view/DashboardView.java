package vitaliichekalenko.javafxmobidevtestapp.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vitaliichekalenko.javafxmobidevtestapp.service.UserManager;

public class DashboardView {
    private VBox view;

    public DashboardView(Stage stage) {
        view = new VBox(10);
        view.setPadding(new Insets(20));

        Label welcome = new Label("Welcome, " + UserManager.getInstance().getUser().getName());
        Label xp = new Label("XP: " + UserManager.getInstance().getUser().getXp());

        Button startLesson = new Button("Start Lesson");
        startLesson.setOnAction(e -> {
            LessonView lessonView = new LessonView(stage);
            stage.setScene(new Scene(lessonView.getView(), 500, 400));
        });

        view.getChildren().addAll(welcome, xp, startLesson);
    }

    public VBox getView() {
        return view;
    }
}
