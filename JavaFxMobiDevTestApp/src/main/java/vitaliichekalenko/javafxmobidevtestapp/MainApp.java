package vitaliichekalenko.javafxmobidevtestapp;

import javafx.application.Application;
import javafx.stage.Stage;
import vitaliichekalenko.javafxmobidevtestapp.controller.QuizController;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        new QuizController(primaryStage).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
