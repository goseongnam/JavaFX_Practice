module com.example.javafx_practice {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;


    opens com.example.javafx_practice to javafx.fxml;
    exports com.example.javafx_practice;
}