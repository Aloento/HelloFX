package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class 第五天 extends Application {

    @Override
    public void start(Stage primaryStage) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-image: url('QA.jpg')");

        TextField tField = new TextField(); // 单行文本
        TextArea tArea = new TextArea(); // 多行文本，和单行文本差不多
        AnchorPane.setTopAnchor(tArea, 50.0);
        tArea.setWrapText(true); // 设置自动换行

        tField.setTextFormatter(new TextFormatter<String>(change -> { // 限制用户输入类型
            System.out.println(change.getText());
            String value = change.getText();
            // 设置正则表达式
            if (value.matches("[0-9]*"))
                return change;
            return null;
        }));

        anchorPane.getChildren().addAll(tField, tArea);
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.setTitle("第五天");
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.show();

        tArea.textProperty().addListener((observable, oldValue, newValue) -> {
        // 这个方法可以拿来做文字替换，用户输入你妈自动换成我妈（3DM）
            tArea.setTextFormatter(new TextFormatter<String>(new StringConverter<>() {
                @Override
                public String toString(String object) { // 输出
                    return object;
                }

                @Override
                public String fromString(String string) { // 输入
                    if (string.contains("你妈"))
                        return string.replace("你妈", "我妈");
                    return string;
                }
            }));

            tArea.commitValue();
        });


    }

    public static void main(String[] args) {
        launch(args);
    }

}
//Written by Aloento.