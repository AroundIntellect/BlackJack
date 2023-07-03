package project;
//Подключение необходимых библиотек
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//Стартовый класс обязан быть унаследован от класса Application
public class Start extends Application {
    private static String login;//Создание глобальной переменной, которая используется в нескольких других классах и служит для запоминания логина аккаунта под которым вошёл пользователь

    public static String getLogin() {
        return login;
    }

    public static void setLogin(String login) {
        Start.login = login;
    }

    @Override //Переопределение метода start() для запуска приложения, в котором создаётся объект stage, являющийся самим окном приложения
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("welcomePage.fxml")); //Создание нового объекта класса FXMLLoader для загрузки в него визульного отображения первой страницы из соответсвующего файла
        Scene scene = new Scene(fxmlLoader.load()); //Создание нового объекта класса Scene и загрузка в него только что созданного объекта класса FXMLLoader
        stage.setResizable(false); //Установка окну приложения запрета на изменение размера
        stage.setTitle("BlackJack"); //Установка имени для окна приложения
        stage.setScene(scene); //Добавление Scene на stage
        stage.show(); //Необходимая строчка, которая позволяет в принципе отобразить само окно приложения
    }

    //Запускающий класс, откуда Java начинает выполнение кода
    public static void main(String[] args) {
        launch(); //Специальный метод класса Application, позволяющий запустить Stage
    }
}