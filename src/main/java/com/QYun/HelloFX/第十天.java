package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class 第十天 extends Application {

    @Override
    public void start(Stage primaryStage) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-image: url('QA.jpg')");

        Button b1 = new Button("修改数据");
        Button b2 = new Button("修改Data");
        AnchorPane.setLeftAnchor(b2, 100.0);

        Student s1 = new Student("张三", 16, 50);
        Student s2 = new Student("李四", 10, 60);
        Student s3 = new Student("王老五", 20, 70);

        P_Student ps1 = new P_Student("小A", 22);
        ps1.nameProperty().addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                System.out.println("PN_Old: " + s + "\nPN_New: " + t1);
            }
        });

        anchorPane.getChildren().addAll(b1, b2);
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.setTitle("第十天");
        primaryStage.setHeight(400);
        primaryStage.setWidth(400);
        primaryStage.show();

        s1.pcs.addPropertyChangeListener(new PropertyChangeListener() {
            @Override // 更改监听
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("N_Old: " + evt.getOldValue() + "\nN_New: " + evt.getNewValue());
            }
        });

        s2.pcs.addPropertyChangeListener("setAge_Pro", new PropertyChangeListener() {
            @Override // 这个方法可以拿来做过滤
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("A_Old: " + evt.getOldValue() + "\nA_New: " + evt.getNewValue());
            }
        });

        b1.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                s1.setName("老刘");
                s2.setAge(18);
            }
        });

        b2.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ps1.setName("小B");
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }

}

