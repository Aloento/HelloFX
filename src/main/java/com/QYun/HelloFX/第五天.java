package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class 第五天 extends Application implements EventHandler<ActionEvent> { // 设置全局方法

    @Override
    public void start(Stage primaryStage) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-image: url('QA.jpg')");

        TextField tField = new TextField(); // 单行文本
        TextArea tArea = new TextArea(); // 多行文本，和单行文本差不多
        AnchorPane.setTopAnchor(tArea, 50.0);
        tArea.setWrapText(true); // 设置自动换行

        tField.setTextFormatter(new TextFormatter<String>(change -> { // 限制用户输入类型
            String value = change.getText();
            // 设置正则表达式
            if (value.matches("[0-9]*"))
                return change;
            return null;
        }));

        Button b1 = new Button("应用");
        Button b2 = new Button("上一步");
        Button b3 = new Button("关闭");
        b2.setPrefWidth(100);
        // 按钮栏
        ButtonBar bar = new ButtonBar();
        ButtonBar.setButtonData(b1, ButtonData.APPLY); // 设置按钮类型
        ButtonBar.setButtonData(b2, ButtonData.BACK_PREVIOUS);
        ButtonBar.setButtonData(b3, ButtonData.CANCEL_CLOSE);
        bar.getButtons().addAll(b1, b2, b3); // 添加按钮
        bar.setButtonOrder(ButtonBar.BUTTON_ORDER_LINUX); // 设置排列方式
        ButtonBar.setButtonUniformSize(b2, false); // 设置按钮大小是否影响全局
        AnchorPane.setTopAnchor(bar, 250.0);
        // 下拉列表里面可以放分隔符Separator()
        ChoiceBox<String> cBox = new ChoiceBox<>(); // 下拉列表
        cBox.getItems().addAll("选项1", "选项2", "选项3");
        cBox.setValue("选项2"); // 设置默认选项
        AnchorPane.setTopAnchor(cBox, 300.0);

        // 重要内容，创建“学生”，在Student中改写toString方法
        Student p1 = new Student("张三", 16, 50);
        Student p2 = new Student("李四", 10, 60);
        Student p3 = new Student("王老五", 20, 70);
        // 创建学生的下拉列表，可以通过重写toString方法解决
        ChoiceBox<Student> scBox = new ChoiceBox<>(); // 泛型是Student.toString
        scBox.getItems().addAll(p1, p2, p3); // 自动调用重写的toString

        // 下面的方法object始终为null，暂时无解
//        scBox.setConverter(new StringConverter<Student>() {
//            @Override
//            public String toString(Student object) {
//                return object.getName();
//            }
//
//            @Override // 无效输入
//            public Student fromString(String string) {
//                return null;
//            }
//        });

        AnchorPane.setLeftAnchor(scBox, 200.0);

        anchorPane.getChildren().addAll(tField, tArea, bar, cBox, scBox);
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.setTitle("第五天");
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.show();
        tArea.requestFocus(); // 请求默认选中

        tArea.textProperty().addListener((observable, oldValue, newValue) -> { // 当用户输入的时候
        // 这个方法可以拿来做文字替换，用户输入你妈自动换成我妈（3DM）
            tArea.setTextFormatter(new TextFormatter<>(new StringConverter<String>() { // 修改文本
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
            // 需要提交一下用户输入
            tArea.commitValue();
        });
        // 触发动作
        b1.setOnAction(this);
        b2.setOnAction(this);
        b3.setOnAction(this);
        // 设置选中动作
        cBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> System.out.println(newValue));
        scBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> System.out.println(newValue.getScore())); // 选中输出分数

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override // 处理方法
    public void handle(ActionEvent event) {
        Button button = (Button) event.getSource();
        // 设置动作
        switch (ButtonBar.getButtonData(button)) {
            case APPLY:

            case BACK_PREVIOUS:

            case CANCEL_CLOSE:
                System.out.println(button.getText());
                break;

            default:
                break;
        }
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