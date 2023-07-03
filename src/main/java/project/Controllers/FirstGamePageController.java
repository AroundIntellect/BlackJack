package project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import project.OpenNewWindow;

public class FirstGamePageController {

    @FXML
    private Button outTheGameButton;

    @FXML
    private Button rulesButton;

    @FXML
    private Button startGameButton;

    @FXML
    void initialize() {

        outTheGameButton.setOnAction(actionEvent -> {
            OpenNewWindow.openNewWindow(outTheGameButton,"welcomePage");
        });

        rulesButton.setOnAction(actionEvent -> {
            OpenNewWindow.openNewWindow(rulesButton, "rulesPage");
        });

        startGameButton.setOnAction(actionEvent -> {
            OpenNewWindow.openNewWindow(startGameButton,"inGamePage");
        });
    }

}
