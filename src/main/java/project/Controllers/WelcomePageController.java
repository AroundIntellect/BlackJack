package project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import project.OpenNewWindow;
import project.Start;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WelcomePageController {

    @FXML
    private Label failLabel;

    @FXML
    private Button logInButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    void initialize() {

        loginField.setOnMouseClicked(mouseEvent -> {
            loginField.setStyle("-fx-background-color: #fafafa; -fx-background-radius: 10; -fx-border-color: silver; -fx-border-radius: 10;");
        });

        passwordField.setOnMouseClicked(mouseEvent -> {
            passwordField.setStyle("-fx-background-color: #fafafa; -fx-background-radius: 10; -fx-border-color: silver; -fx-border-radius: 10;");
        });

        signUpButton.setOnAction(actionEvent -> {
            OpenNewWindow.openNewWindow(signUpButton,"signUP");
        });

        logInButton.setOnAction(actionEvent -> {
            String login = loginField.getText().trim();
            String password = passwordField.getText().trim();

            if (!login.equals("") && !password.equals("")) {
                if (logInUser(login, password) == AccountValidationResult.ACCOUNT_EXISTS) {
                    Start.setLogin(login);
                    OpenNewWindow.openNewWindow(logInButton, "firstGamePage");
                } else if (logInUser(login, password) == AccountValidationResult.INVALID_PASSWORD) {
                    failLabel.setText("Пароль неверный");
                    passwordField.setStyle("-fx-background-color: #fafafa; -fx-background-radius: 10; -fx-border-color: red; -fx-border-radius: 10;");
                } else {
                    failLabel.setText("Аккаунт с таким логином не найден");
                    loginField.setStyle("-fx-background-color: #fafafa; -fx-background-radius: 10; -fx-border-color: red; -fx-border-radius: 10;");
                }
            } else {
                failLabel.setText("");
                if (login.equals("")) {
                    loginField.setStyle("-fx-background-color: #fafafa; -fx-background-radius: 10; -fx-border-color: red; -fx-border-radius: 10;");
                }
                if (password.equals("")) {
                    passwordField.setStyle("-fx-background-color: #fafafa; -fx-background-radius: 10; -fx-border-color: red; -fx-border-radius: 10;");
                }
            }


        });
    }

    public enum AccountValidationResult {
        ACCOUNT_EXISTS,
        INVALID_PASSWORD,
        ACCOUNT_NOT_FOUND
    }

    public static AccountValidationResult logInUser(String login, String password) {
        try {
            String relativePath = "src/main/java/project/Authentication/Accounts.txt";
            File file = new File(relativePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] accountData = line.split(";");
                if (accountData.length >= 1 && accountData[0].equals(login)) {
                    if (accountData[1].equals(password)) {
                        bufferedReader.close();
                        return AccountValidationResult.ACCOUNT_EXISTS;
                    } else {
                        bufferedReader.close();
                        return AccountValidationResult.INVALID_PASSWORD;
                    }
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Произошла ошибка при чтении файла: " + e.getMessage());
        }

        return AccountValidationResult.ACCOUNT_NOT_FOUND;
    }

}
