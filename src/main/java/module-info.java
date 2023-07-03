module project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens project to javafx.fxml;
    exports project;
    exports project.Controllers;
    opens project.Controllers to javafx.fxml;
}