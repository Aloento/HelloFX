package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CellFactory extends Application {

    @Override
    public void start(Stage primaryStage) {
        AnchorPane anchorPane = new AnchorPane();

        ComboBox<String> cbBox = new ComboBox<>();
        cbBox.getItems().addAll("选项1", "选项2", "选项3");

        cbBox.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                // param.getItems().forEach(System.out::println); // 方法应用
                return null;
            }
        });

        anchorPane.getChildren().addAll(cbBox);
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.setTitle("CellFactory");
        primaryStage.setHeight(400);
        primaryStage.setWidth(400);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
//Written by Aloento.