module com.example.project_part2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires java.desktop;


    opens com.example.project_part2 to javafx.fxml;
    exports com.example.project_part2;
}