package vitaliichekalenko.javafxmobidevtestapp.view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vitaliichekalenko.javafxmobidevtestapp.service.UserManager;

public class LoginView {
    private VBox view;

    public LoginView(Stage primaryStage) {
        view = new VBox(10);
        view.setPadding(new Insets(20));

        Label label = new Label("Enter your name:");
        TextField nameField = new TextField();
        Button loginButton = new Button("Login");

        loginButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                UserManager.getInstance().login(name);
                DashboardView dashboard = new DashboardView(primaryStage);
                primaryStage.setScene(new Scene(dashboard.getView(), 500, 400));
            }
        });

        view.getChildren().addAll(label, nameField, loginButton);
    }

    public VBox getView() {
        return view;
    }
}
