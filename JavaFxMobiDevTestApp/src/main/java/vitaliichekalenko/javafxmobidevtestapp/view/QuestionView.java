package vitaliichekalenko.javafxmobidevtestapp.view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import vitaliichekalenko.javafxmobidevtestapp.model.Question;

import java.util.List;

public class QuestionView extends VBox {
    private final ToggleGroup group;

    public QuestionView(Question question) {
        super(10);
        setPadding(new Insets(20));
        Label q = new Label(question.getText());
        getChildren().add(q);

        group = new ToggleGroup();
        List<String> opts = question.getOptions();
        for (String opt : opts) {
            RadioButton rb = new RadioButton(opt);
            rb.setToggleGroup(group);
            getChildren().add(rb);
        }
    }

    public int getSelectedIndex() {
        for (int i = 0; i < group.getToggles().size(); i++) {
            if (group.getToggles().get(i).isSelected()) return i;
        }
        return -1;
    }
}
