package project.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import project.OpenNewWindow;
import project.Start;

import java.io.*;

public class SignUpController {

    @FXML
    private Button backToLogInButton;

    @FXML
    private Label failLabel;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField recordPasswordField;

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
        recordPasswordField.setOnMouseClicked(mouseEvent -> {
            recordPasswordField.setStyle("-fx-background-color: #fafafa; -fx-background-radius: 10; -fx-border-color: silver; -fx-border-radius: 10;");
        });

        backToLogInButton.setOnAction(actionEvent -> {
            OpenNewWindow.openNewWindow(backToLogInButton,"welcomePage");
        });

        signUpButton.setOnAction(actionEvent -> {

            String login = loginField.getText().trim();
            String password = passwordField.getText().trim();

            if (loginField.getText().equals("") || passwordField.getText().equals("") || recordPasswordField.getText().equals("")) {
                if (loginField.getText().equals("")) {
                    loginField.setStyle("-fx-background-color: #fafafa; -fx-background-radius: 10; -fx-border-color: red; -fx-border-radius: 10;");
                }
                if (passwordField.getText().equals("")) {
                    passwordField.setStyle("-fx-background-color: #fafafa; -fx-background-radius: 10; -fx-border-color: red; -fx-border-radius: 10;");
                }
                if (recordPasswordField.getText().equals("")) {
                    recordPasswordField.setStyle("-fx-background-color: #fafafa; -fx-background-radius: 10; -fx-border-color: red; -fx-border-radius: 10;");
                }
                return;
            }


            if (!passwordField.getText().equals(recordPasswordField.getText())) {
                recordPasswordField.setStyle("-fx-background-color: #fafafa; -fx-background-radius: 10; -fx-border-color: red; -fx-border-radius: 10;");
                failLabel.setText("Пароли не совпадают");
                return;
            } else {
                failLabel.setText("");
            }

            if (signUpUser(login, password)) {
                Start.setLogin(login);
                OpenNewWindow.openNewWindow(signUpButton, "firstGamePage");
            }
        });
    }

    public boolean signUpUser(String login, String password) {
        String relativePath = "src/main/java/project/Authentication/Accounts.txt";
        String data = login + ";" + password + ";" + "1000;";

        if (loginField.getText().trim().length() < 4) {
            failLabel.setText("Логин должен быть длиннее 4 символов");
            return false;
        }
        if (loginField.getText().trim().length() > 16) {
            failLabel.setText("Логин не может быть длиннее 16 символов");
            return false;
        }

        if (passwordField.getText().trim().length() < 4) {
            failLabel.setText("Пароль должен быть длиннее 4 символов");
            return false;
        }
        if (passwordField.getText().trim().length() > 16) {
            failLabel.setText("Пароль не может быть длиннее 16 символов");
            return false;
        }

        try {
            File file = new File(relativePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            boolean recordNotExists = true;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                int index = line.indexOf(';');
                if (index != -1) {
                    line = line.substring(0, index);
                    if (line.contains(login)) {
                        recordNotExists = false;
                        break;
                    }
                }
            }

            bufferedReader.close();

            if (recordNotExists) {
                FileWriter fileWriter = new FileWriter(file, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

                bufferedWriter.write(data);
                bufferedWriter.newLine();

                bufferedWriter.close();
                return true;
            }
        } catch (IOException e) {
            failLabel.setText("Произошла ошибка при обработке файла: " + e.getMessage());
            return false;
        }
        failLabel.setText("Аккаунт с таким именем уже существует");
        loginField.setStyle("-fx-background-color: #fafafa; -fx-background-radius: 10; -fx-border-color: red; -fx-border-radius: 10;");

        return false;
    }

}
