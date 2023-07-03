package project;

//Загрузка необходимых библиотек
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OpenNewWindow {
    public static void openNewWindow(Node node, String nameFXMLFile) {
        nameFXMLFile += ".fxml"; //Добавление расширения для имени FXML файла, полученного в метод
        try {
            FXMLLoader loader = new FXMLLoader(Start.class.getResource(nameFXMLFile)); //Загрузка FXML файла с необходимым именем
            Parent newRoot = loader.load(); // Загрузка содержимого FXML файла в объект типа Parent
            Node oldRoot = node.getScene().getRoot(); //Получение корневого узла (root) текущего окна, чтобы заменить его новым окном.
            Scene scene = new Scene(newRoot); //Создание нового объекта сцены, используя новый корневой узел newRoot
            Stage primaryStage = (Stage) oldRoot.getScene().getWindow(); //Получение ссылки на основное окно приложения, что позволяет перенять все настройки по типу setResizable(false)
            primaryStage.setScene(scene); //Добавление новой сцены для основного окна приложения
        } catch (IOException e) { //Обработка возможных ошибок
            e.printStackTrace();
        }
    }
}
