package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class 第三天 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 创建文本
        Text T1 = new Text("Hello World");
        Text T2 = new Text("I‘m Here");
        Text T3 = new Text("OpenJFX");
        // 设置文本效果
        T1.setFont(Font.font(20));
        T2.setStyle("-fx-fill: #ff3c3c");
        T3.setFont(Font.font("Helvetica", FontPosture.ITALIC, 20)); // 设置字体与斜体
        T3.setStyle("-fx-font-weight: BOLD"); // 加粗
        // 创建文本流
        TextFlow textFlow = new TextFlow();
        textFlow.setStyle("-fx-background-color: #b3b3b3");
//        textFlow.setTextAlignment(TextAlignment.RIGHT); // 对齐方式
        textFlow.getChildren().addAll(T1, T2, T3);
        // 创建绝对布局，并赋予文本流
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(textFlow);
        AnchorPane.setTopAnchor(textFlow, 50.0); // 设置边距
        AnchorPane.setLeftAnchor(textFlow, 50.0);
        // 设置场景
        Scene scene = new Scene(anchorPane); // 绑定绝对布局到场景
        primaryStage.setScene(scene);
        primaryStage.setTitle("第三天");
        primaryStage.setWidth(400);
        primaryStage.setHeight(400);
        primaryStage.show();

        anchorPane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override // 让文本流的宽度随着布局变化而变化
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                textFlow.setPrefWidth(newValue.doubleValue() - AnchorPane.getLeftAnchor(textFlow));
            } // 减去一个内边距
        });

    }

    public static void main(String[] args) {
        launch();
    }

}
//Written by Aloento.