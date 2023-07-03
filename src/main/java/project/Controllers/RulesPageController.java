package project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import project.OpenNewWindow;

public class RulesPageController {

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        backButton.setOnAction(actionEvent -> {
            OpenNewWindow.openNewWindow(backButton, "firstGamePage");
        });
    }

}
