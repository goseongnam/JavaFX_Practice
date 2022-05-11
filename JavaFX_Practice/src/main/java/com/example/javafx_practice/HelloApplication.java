package com.example.javafx_practice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("Main.fxml"));
        StageStore.stage=stage;
        Scene scene1 = new Scene(fxmlLoader1.load(), 670, 350);
        stage.setTitle("Foreign exchange information provision system");
        stage.setScene(scene1);
        stage.show();


    }
}