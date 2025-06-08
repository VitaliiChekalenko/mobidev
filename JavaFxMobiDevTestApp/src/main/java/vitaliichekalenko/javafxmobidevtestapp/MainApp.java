package vitaliichekalenko.javafxmobidevtestapp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vitaliichekalenko.javafxmobidevtestapp.view.LoginView;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        LoginView loginView = new LoginView(primaryStage);
        primaryStage.setTitle("MobiDevTestApp");
        primaryStage.setScene(new Scene(loginView.getView(), 400, 300));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}