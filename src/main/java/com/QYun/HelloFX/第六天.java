package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

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

        // 这玩意和ChoicesBox几乎一样，区别就在于它可以允许用户编辑，还可以自定义单元格样式
        ComboBox<Student> cbBox = new ComboBox<>();
        cbBox.getItems().addAll(p1, p2, p3);
        cbBox.setPromptText("请输入"); // 设置提示
        cbBox.setPlaceholder(new Text("无结果")); // 当列表内容为空的时候显示的
        cbBox.setEditable(true); // 区别，允许编辑
        AnchorPane.setTopAnchor(cbBox, 100.0);

        cbBox.setConverter(new StringConverter<>() { // 按回车以后数据就进这里
            @Override // 它负责数据处理
            public String toString(Student object) {
                if (object == null)
                    return null; // 解决报错
                String value = object.getName() + "：" + object.getAge();

                if (!cbBox.getItems().contains(object))
                    cbBox.getItems().add(object);

                return value;
            }

            @Override
            public Student fromString(String string) { // 回车后数据由这里传入

                if (string.equals(""))
                    return null;

                return new Student(string, 10, 100);
            }
        });

        cbBox.setCellFactory(new Callback<>() { // 设置下拉样式，重难点
            @Override // 它负责样式处理
            public ListCell<Student> call(ListView<Student> param) {

                return new ListCell<>() {
                    @Override
                    protected void updateItem(Student item, boolean empty) { // 匿名内部类
                        super.updateItem(item, empty);
                        if (!empty) {
                            HBox hBox = new HBox(10);
                            Label name = new Label(item.getName());
                            Label age = new Label(item.getAge() + "岁");
                            Label score = new Label(item.getScore() + "分");
                            hBox.getChildren().addAll(name, age, score);
                            this.setGraphic(hBox);
                        }
                    }
                };
            }
        });

        anchorPane.getChildren().addAll(tField, button_C, scBox, cbBox);
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

        ObservableList<Student> allStudents = cbBox.getItems(); // 提取原列表
        cbBox.editorProperty().get().textProperty().addListener((observable, oldValue, newValue) -> { // 做一个查找功能
            if (newValue == null)
                return; // 处理输入为空

            FilteredList<Student> find = allStudents.filtered(student -> { // 新的找到的列表
                return student.getName().contains(newValue); // 返回找到的列表
            });

            if (find.isEmpty())
                cbBox.setItems(null);
            else {
                cbBox.setItems(find);
                cbBox.hide(); // 刷新列表
                cbBox.show();
            }
        });

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