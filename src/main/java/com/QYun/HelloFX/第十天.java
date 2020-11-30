package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Comparator;

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

        P_Student ps1 = new P_Student("小A", 22);
        ps1.nameProperty().addListener((observableValue, s, t1) -> System.out.println("PN_Old: " + s + "\nPN_New: " + t1));

        // 创建只读的属性，用于做只读API
//        ReadOnlyObjectWrapper roow = new ReadOnlyObjectWrapper();
//        ReadOnlyObjectProperty only = roow.getReadOnlyProperty();

        anchorPane.getChildren().addAll(b1, b2);
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.setTitle("第十天");
        primaryStage.setHeight(400);
        primaryStage.setWidth(400);
        primaryStage.show();

        // 更改监听
        s1.pcs.addPropertyChangeListener(evt -> System.out.println("N_Old: " + evt.getOldValue() + "\nN_New: " + evt.getNewValue()));

        // 这个方法可以拿来做过滤
        s2.pcs.addPropertyChangeListener("setAge_Pro", evt -> System.out.println("A_Old: " + evt.getOldValue() + "\nA_New: " + evt.getNewValue()));

        b1.setOnAction(actionEvent -> {
            s1.setName("老刘");
            s2.setAge(18);
        });

        b2.setOnAction(actionEvent -> ps1.setName("小B"));

        // 可观察List的增删改查与监听
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("A", "B", "C");
        SimpleListProperty<String> slp = new SimpleListProperty<>(list);

        slp.addListener((ListChangeListener<String>) change -> {
            System.out.println(change);
            change.reset();

            while (change.next()) {
                System.out.println("增删改查:" + change.getFrom() + change.wasAdded() + change.wasPermutated());
            }
        });

        slp.addAll("D", "E");
        list.sort(Comparator.reverseOrder());

        // 可观察Set的增删改查与监听
        ObservableSet<String> set = FXCollections.observableSet("A", "B", "C");
        SimpleSetProperty<String> ssp = new SimpleSetProperty<>(set);

        ssp.addListener((SetChangeListener<String>) change -> System.out.println(change.wasAdded()));
        ssp.add("D");
        ssp.forEach(System.out::println);

        // 可观察Map的增删改查与监听
        ObservableMap<String, String> map = FXCollections.observableHashMap();
        map.put("1", "A");
        map.put("2", "B");

        SimpleMapProperty<String, String> smp = new SimpleMapProperty<>(map);
        smp.addListener((MapChangeListener<String, String>) change -> System.out.println(change.wasAdded()));
        smp.forEach((s, s21) -> System.out.println(s + "-" + s21));

        // 延迟监听（InvalidationListener）的用法，是为了优化执行效率而设计的
        SimpleIntegerProperty sip = new SimpleIntegerProperty(1);
        sip.addListener(observable -> System.out.println("延迟监听"));
        // 无论设置多少次，只有get的时候才会触发监听，前提是没有调用ChangeListener
        sip.set(2);
        sip.get();
        sip.set(3);
        sip.set(4);
        sip.get();

        // 弱监听器，优化内存使用，防止溢出
//        WeakChangeListener<Number> weak = new WeakChangeListener<>(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
//                System.out.println("弱监听");
//            }
//        });
//        sip.addListener(weak);

    }

    public static void main(String[] args) {
        launch(args);
    }

}

