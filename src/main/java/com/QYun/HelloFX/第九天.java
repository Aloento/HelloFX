package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class 第九天 extends Application {

    @Override
    public void start(Stage primaryStage) {

        AnchorPane aPane = new AnchorPane();
        aPane.setStyle("-fx-background-image: url('QA.jpg')");

        VBox vBox = new VBox(5);
        for (int i = 0; i < 5; i++)
            vBox.getChildren().add(new Button("按钮" + i));

        // 滚动条
        ScrollBar sb = new ScrollBar();
        sb.setOrientation(Orientation.VERTICAL);
        sb.setUnitIncrement(10); // 设置按钮单次变化量
        sb.setBlockIncrement(10); // 设置滚动条点击变化量
        AnchorPane.setLeftAnchor(sb, 50.0);
        sb.valueProperty().addListener((observable, oldValue, newValue) -> vBox.setLayoutY(-newValue.doubleValue()));

        HBox hBox = new HBox(5);
        for (int i = 0; i < 10; i++)
            hBox.getChildren().add(new Button("按钮" + i));
        // 滚动面板
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(hBox);
        scrollPane.setPrefWidth(200.0);
        scrollPane.setPrefHeight(40.0);
        AnchorPane.setTopAnchor(scrollPane, 150.0);

        Separator separator = new Separator(Orientation.VERTICAL); // 灰色一条线的分离器
        separator.setPrefHeight(150.0);
        AnchorPane.setLeftAnchor(separator, 100.0);
        aPane.getChildren().addAll(vBox, sb, scrollPane, separator);
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