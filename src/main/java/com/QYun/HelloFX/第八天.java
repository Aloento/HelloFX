package com.QYun.HelloFX;

import com.QYun.HelloFX.第五天.Student;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class 第八天 extends Application {

    @Override
    public void start(Stage primaryStage) {
        AnchorPane aPane = new AnchorPane();
        aPane.setStyle("-fx-background-image: url('QA.jpg')");

        Spinner<Integer> spinner = new Spinner<>(0, 10, 5); // 值选择器
        spinner.getStyleClass().add(Spinner.STYLE_CLASS_ARROWS_ON_LEFT_HORIZONTAL); // 设置样式

        ObservableList<String> list = FXCollections.observableArrayList(); // 还能放字符串
        list.addAll("A", "B", "C");
        Spinner<String> spinner1 = new Spinner<>(list); // 设置字符串为内容
        AnchorPane.setTopAnchor(spinner1, 50.0);

        Student s1 = new Student("张三", 16, 50);
        Student s2 = new Student("李四", 10, 60);
        Student s3 = new Student("王老五", 20, 70);
        ObservableList<Student> s_list = FXCollections.observableArrayList();
        s_list.addAll(s1, s2, s3);

        Spinner<Student> spinner2 = new Spinner<>(); // 其他类型的数据
        AnchorPane.setTopAnchor(spinner2, 100.0);
        SVF svf = new SVF(s_list);
        spinner2.setValueFactory(svf);
        svf.setConverter(new StringConverter<>() { // 设置数据输出格式
            @Override
            public String toString(Student object) {
                if (object == null)
                    return "";
                return object.toString();
            }

            @Override
            public Student fromString(String string) {
                return null;
            }
        });

        // 也可以这么实现，但是没有循环
//        SpinnerValueFactory.ListSpinnerValueFactory<Student> slvf = new SpinnerValueFactory.ListSpinnerValueFactory<Student>(s_list);
//        slvf.setConverter(new StringConverter<Student>() {
//            @Override
//            public String toString(Student object) {
//                return object.toString();
//            }
//
//            @Override
//            public Student fromString(String string) {
//                return null;
//            }
//        });
//        spinner2.setValueFactory(slvf);

        aPane.getChildren().addAll(spinner, spinner1, spinner2);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.setTitle("第八天");
        primaryStage.setWidth(400);
        primaryStage.setHeight(400);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}

class SVF extends SpinnerValueFactory<Student> { // 设置按钮获取数据的方法，带循环
    private int i = -1;
    private ObservableList<Student> list;
    public SVF(ObservableList<Student> list) {
        this.list = list;
    }

    @Override
    public void decrement(int steps) {
        if (i + steps >= list.size())
            i = 0;
        else i+= steps;
        this.setValue(list.get(i));
    }

    @Override
    public void increment(int steps) {
        if (i + steps <= -1)
            i = list.size() - 1;
        else i-= steps;
        this.setValue(list.get(i));
    }
}

//Written by Aloento.