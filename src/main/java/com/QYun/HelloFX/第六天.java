package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class 第六天 extends Application { // 设置全局方法

    @Override
    public void start(Stage primaryStage) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-image: url('QA.jpg')");

        TextField tField = new TextField(); // 单行文本
        Button button_C = new Button("修改");
        AnchorPane.setLeftAnchor(button_C, 150.0);

        // 重要内容，创建“学生”，在Student中改写toString方法
        Student p1 = new Student("张三", 18, 50);
        Student p2 = new Student("李四", 12, 60);
        Student p3 = new Student("王老五", 25, 70);
        // 创建学生的下拉列表，可以通过重写toString方法解决
        ChoiceBox<Student> scBox = new ChoiceBox<>(); // 泛型是Student.toString
        scBox.getItems().addAll(p1, p2, p3); // 自动调用重写的toString
        AnchorPane.setTopAnchor(scBox, 50.0);

        anchorPane.getChildren().addAll(tField, button_C, scBox);
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.setTitle("第五天");
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.show();

        scBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> System.out.println(newValue.getScore())); // 选中输出分数

    }

    public static void main(String[] args) {
        launch(args);
    }

    static class Student { // 定义类
        private String name;
        private int age;
        private double score;

        public Student(String name, int age, double score) { // 构造函数
            this.name = name;
            this.age = age;
            this.score = score;
        }

        // 自动创建Getter和Setter
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        @Override // 重写toString，重点
        public String toString() {
            return name + "：" + age + "岁";
        }
    }

}
//Written by Aloento.