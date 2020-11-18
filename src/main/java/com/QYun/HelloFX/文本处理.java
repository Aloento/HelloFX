package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;

public class 文本处理 extends Application {

    int index = 0;
    String sub = "";

    @Override
    public void start(Stage primaryStage) {

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-image: url('QA.jpg')");

        TextField textField = new TextField();
        Button button_Find = new Button("查找");
        Button button_Sort = new Button("排序");
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(textField, button_Find, button_Sort);

        TextArea textArea = new TextArea("SoarTeam_QYun_Q-Audio_Aloento");
        textArea.setWrapText(true);
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(hBox, textArea);

        AnchorPane.setTopAnchor(vBox, 10.0);
        AnchorPane.setLeftAnchor(vBox, 10.0);
        AnchorPane.setRightAnchor(vBox, 10.0);
        anchorPane.getChildren().addAll(vBox);
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.setTitle("文本处理");
        primaryStage.setHeight(300);
        primaryStage.setWidth(500);
        primaryStage.show();

        button_Find.setOnAction(event -> textArea.getParagraphs().forEach(charSequence -> {
            sub = charSequence.toString().substring(index);

            if (sub.contains(textField.getText()))
            {
                System.out.println("找到：" + textField.getText());
                textArea.requestFocus();
                button_Find.setText("下一个");
                int start = sub.indexOf(textField.getText());
                int tmp = start + index;
                index = start + index + textField.getText().length();
                textArea.selectRange(tmp, index);
            }
            else
            {
                System.out.println("空");
                button_Find.setText("查找");
                sub = ""; index = 0;
            }
        }));

        button_Sort.setOnAction(event -> {
            char[] ch = textArea.getText().toCharArray();
            Arrays.sort(ch);

            String string = new String(ch);
            textArea.setText(string);
        });

    }

    public static void main(String[] args) {
        launch(args);
    }

}
