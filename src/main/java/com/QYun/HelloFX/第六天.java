package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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

        var lambda = new Object() {Student tmpStudent;}; // 匿名对象变量
        scBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue.getScore());
            lambda.tmpStudent = newValue;
            lambda.tmpStudent.getNameProperty().addListener((observable1, oldValue1, newValue1) -> { // 删除再加载
                int i = scBox.getItems().indexOf(lambda.tmpStudent);
                scBox.getItems().remove(lambda.tmpStudent);
                scBox.getItems().add(i, lambda.tmpStudent);
            }); // 更新列表但是UI不刷新
        }); // 选中输出分数，并且执行修改

        button_C.setOnAction(event -> lambda.tmpStudent.setName(tField.getText()));
    }

    public static void main(String[] args) {
        launch(args);
    }

    static class Student { // 定义类，采用可变列表，难点
        private SimpleStringProperty name = new SimpleStringProperty();
        private SimpleIntegerProperty age = new SimpleIntegerProperty();
        private SimpleDoubleProperty score = new SimpleDoubleProperty();

        public Student(String name, int age, double score) {
            this.name.setValue(name);
            this.age.setValue(age);
            this.score.setValue(score);
        }

        // 自动创建Getter和Setter
        public String getName() {
            return name.getValue();
        }

        public void setName(String name) {
            this.name.setValue(name);
        }

        public int getAge() {
            return age.getValue();
        }

        public void setAge(int age) {
            this.age.setValue(age);
        }

        public double getScore() {
            return score.getValue();
        }

        public void setScore(double score) {
            this.score.setValue(score);
        }

        public SimpleStringProperty getNameProperty(){
            return name;
        }

        @Override
        public String toString() { // 需要添加.getValue()
            return this.name.getValue() + "：" + this.age.getValue() + "岁";
        }
    }

}
//Written by Aloento.