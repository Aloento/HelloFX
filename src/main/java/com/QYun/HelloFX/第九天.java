package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class 第九天 extends Application {

    @Override
    public void start(Stage primaryStage) {

        AnchorPane aPane = new AnchorPane();
        aPane.setStyle("-fx-background-image: url('QA.jpg')");

        primaryStage.setScene(new Scene(aPane));
        primaryStage.setTitle("第九天");
        primaryStage.setHeight(400);
        primaryStage.setWidth(400);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
//Written by Aloento.