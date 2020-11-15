package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class 第二天 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 设置元数据
        primaryStage.setTitle("第二天");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        // 绑定场景
        Group Group_one = new Group(); // 新建组
        Scene Scene_one = new Scene(Group_one); // 新建场景
        primaryStage.setScene(Scene_one); // 绑定场景

        TextField textField_one = new TextField(); // 新建文本框
        textField_one.setText("默认文本"); // 设定默认内容
        textField_one.setFont(Font.font(16)); // 字体大小
        textField_one.setLayoutX(100);
        textField_one.setLayoutY(100); // 设定相对位置
        textField_one.setPrefWidth(200);
        textField_one.setPrefHeight(50); // 设置宽高
        Group_one.getChildren().add(textField_one); // 让组获得文本框

        textField_one.setTooltip(new Tooltip("这是提示")); // 设置鼠标停留提示
        textField_one.setPromptText("请输入不超过7个字"); // 设置在失焦且空内容下的提示文本

        textField_one.selectedTextProperty().addListener(new ChangeListener<String>() { // 用户选择文本监听
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("选择了：" + newValue);
            }
        });

        PasswordField passwordField_one = new PasswordField(); // 新建密码框，上面的方法都能用
        Group_one.getChildren().add(passwordField_one); // 获得孩子（

        passwordField_one.textProperty().addListener(new ChangeListener<String>() { // 监听输入
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > 7) //限制用户只能输入7个字
                {
                    passwordField_one.setText(oldValue);
                    System.out.println(oldValue); // 打印最终密码
                }
                else System.out.println(newValue); // 打印当前输入
            }
        });

        

        primaryStage.show(); //显示窗口
    }

    public static void main(String[] args)
    {
        launch(); //启动舞台
    }

}
//Written by Aloento.