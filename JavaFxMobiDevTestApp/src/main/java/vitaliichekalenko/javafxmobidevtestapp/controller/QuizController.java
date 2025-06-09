package vitaliichekalenko.javafxmobidevtestapp.controller;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vitaliichekalenko.javafxmobidevtestapp.model.Question;
import vitaliichekalenko.javafxmobidevtestapp.view.QuestionView;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

public class QuizController {
    private final List<Question> questions = Arrays.asList(
            new Question("What is the capital of France?", Arrays.asList("Berlin", "London", "Paris", "Rome"), 2),
            new Question("2 + 2 = ?", Arrays.asList("3", "4", "5", "6"), 1),
            new Question("Android is built with?", Arrays.asList("Swift", "Kotlin", "JavaScript", "Ruby"), 1)
    );

    private final List<String> warnings = Arrays.asList(
            "Choose an answer.",
            "Hey, pick something!",
            "Still waiting... 😏",
            "Not funny 😐",
            "Ha-ha, very funny... now choose!",
            "🤯 OK, I'm just a button. But seriously – click something!"
    );

    private int current = 0;
    private int correct = 0;
    private final Stage stage;

    public QuizController(Stage stage) {
        this.stage = stage;
    }

    public void start() {
        current = 0;
        correct = 0;
        nextQuestion();
    }

    private void nextQuestion() {
        if (current >= questions.size()) {
            showResultDialog();
            return;
        }

        Question question = questions.get(current);
        QuestionView view = new QuestionView(question);
        Button nextButton = createNextButton(view, question);

        VBox root = buildQuestionLayout(view, nextButton);
        stage.setScene(new Scene(root, 400, 300));
        stage.setTitle("MobiDev Test App Quiz");
        stage.show();
    }

    private Button createNextButton(QuestionView view, Question question) {
        Button btn = new Button("Next");
        final int[] noAnswerAttempts = {0};

        btn.setOnAction(e -> {
            int sel = view.getSelectedIndex();
            if (sel < 0) {
                String msg = warnings.get(noAnswerAttempts[0] % warnings.size());
                noAnswerAttempts[0]++;
                new Alert(Alert.AlertType.WARNING, msg).showAndWait();
                return;
            }
            if (sel == question.getCorrectIndex()) {
                correct++;
            }
            current++;
            nextQuestion();
        });

        return btn;
    }

    private VBox buildQuestionLayout(QuestionView view, Button btn) {
        VBox root = new VBox(15, view, btn);
        root.setPadding(new javafx.geometry.Insets(20));
        return root;
    }


    private void showResultDialog() {
        boolean perfectScore = correct == questions.size();

        Alert alert = createResultAlert(perfectScore);
        configureResultButtons(alert, perfectScore);

        alert.showAndWait().ifPresent(response -> handleResultResponse(response, perfectScore));
    }

    private Alert createResultAlert(boolean perfectScore) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quiz Finished");

        String header = perfectScore
                ? "🎉 All answers are correct! Get your reward!"
                : "Your score: " + correct + " out of " + questions.size();

        alert.setHeaderText(header);
        alert.setContentText("Would you like to try again?");
        return alert;
    }

    private void configureResultButtons(Alert alert, boolean perfectScore) {
        ButtonType repeatBtn = new ButtonType("Try Again");
        ButtonType exitBtn = new ButtonType("Exit");

        if (perfectScore) {
            ButtonType prizeBtn = new ButtonType("Get Prize (PDF)");
            alert.getButtonTypes().setAll(repeatBtn, prizeBtn, exitBtn);
        } else {
            alert.getButtonTypes().setAll(repeatBtn, exitBtn);
        }
    }

    private void handleResultResponse(ButtonType response, boolean perfectScore) {
        String responseText = response.getText();

        if (responseText.equals("Try Again")) {
            start();
        } else if (perfectScore && responseText.equals("Get Prize (PDF)")) {
            showRewardWindow();
        } else {
            stage.close();
        }
    }


    private void showRewardWindow() {
        try {
            ImageView gifView = loadGifView("/congrats.gif");
            File rewardPdf = extractPdfFromResources("/reward.pdf");

            Button openPdfBtn = createOpenPdfButton(rewardPdf);
            VBox content = new VBox(15, gifView, openPdfBtn);
            content.setStyle("-fx-background-color: white; -fx-alignment: center;");
            content.setPadding(new Insets(20));

            Stage rewardStage = new Stage();
            rewardStage.setTitle("🎁 Your Reward!");
            rewardStage.setScene(new Scene(content, 400, 350));
            rewardStage.show();
        } catch (Exception e) {
            showError("Error showing reward: " + e.getMessage());
        }
    }

    private ImageView loadGifView(String path) {
        Image gif = new Image(getClass().getResource(path).toExternalForm());
        ImageView imageView = new ImageView(gif);
        imageView.setFitWidth(200);
        imageView.setPreserveRatio(true);
        return imageView;
    }

    private File extractPdfFromResources(String resourcePath) throws IOException {
        InputStream in = getClass().getResourceAsStream(resourcePath);
        if (in == null) {
            showError("Prize PDF not found!\nAsk dev!");
            throw new FileNotFoundException(resourcePath);
        }

        File tempFile = File.createTempFile("reward", ".pdf");
        tempFile.deleteOnExit();
        Files.copy(in, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return tempFile;
    }

    private Button createOpenPdfButton(File pdfFile) {
        Button btn = new Button("Open Reward PDF");
        btn.setOnAction(e -> {
            try {
                Desktop.getDesktop().open(pdfFile);
                stage.close();
            } catch (Exception ex) {
                showError("Failed to open PDF: " + ex.getMessage());
            }
        });
        return btn;
    }

    private void showError(String message) {
        new Alert(Alert.AlertType.ERROR, message).showAndWait();
    }
}
