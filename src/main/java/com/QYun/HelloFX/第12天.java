package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.When;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import javafx.stage.Stage;

public class 第12天 extends Application {

    @Override
    public void start(Stage primaryStage) {
        Platform.exit();
    }

    public static void main(String[] args) {

        // JavaFX的三元运算符
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(2);
        SimpleBooleanProperty bool = new SimpleBooleanProperty(true);

        When when = new When(bool);
        NumberBinding n = when.then(10).otherwise(20); // 三元运算，可以设定绑定
        System.out.println(n.doubleValue());
        System.out.println(new When (x.greaterThan(y)).then("x").otherwise("y").get());

        // 列表的单向绑定，其实只维护了一个列表，和双向绑定一样
        ObservableList<String> ol1 = FXCollections.observableArrayList();
        SimpleListProperty<String> list1 = new SimpleListProperty<>(ol1);
        list1.addAll("A", "B");

        ObservableList<String> ol2 = FXCollections.observableArrayList();
        SimpleListProperty<String> list2 = new SimpleListProperty<>(ol2);
        list2.addAll("C", "D");

        System.out.println("list1：" + list1.getValue() + "\t" + "list2：" + list2.getValue());
        list1.bind(list2); // 绑定后，它们共同维护列表ol2
//      list1.bindBidirectional(list2); // 双向绑定，和单向绑定效果一样

//      list1.bindContent(list2); // 用这个方法，它们就会各自维护列表
//      list1.bindContentBidirectional(list2); // 真正的双向绑定
        System.out.println("list1：" + list1.getValue() + "\t" + "list2：" + list2.getValue());

        list1.add("E");
        list2.add("F");
        System.out.println("list1：" + list1.getValue() + "\t" + "list2：" + list2.getValue());
        System.out.println("ol1：" + ol1 + "\t" + "ol2：" + ol2 + "\n");

        // set和map也是一样的
        ObservableSet<String> obs1 = FXCollections.observableSet("A", "B");
        SimpleSetProperty<String> set1 = new SimpleSetProperty<>(obs1);

        ObservableSet<String> obs2 = FXCollections.observableSet("C", "D");
        SimpleSetProperty<String> set2 = new SimpleSetProperty<>(obs2);

        System.out.println("set1：" + set1.getValue() + "\t" + "set2：" + set2.getValue());
        set1.bind(set2);
//      set1.bindContent(set2);
        System.out.println("set1：" + set1.getValue() + "\t" + "set2：" + set2.getValue());

        set1.add("E");
        set2.add("F");
        System.out.println("set1：" + set1.getValue() + "\t" + "set2：" + set2.getValue());
        System.out.println("obs1：" + obs1 + "\t" + "obs2：" + obs2 + "\n");

        // 复习如何建立map
        ObservableMap<String, String> om1 = FXCollections.observableHashMap();
        SimpleMapProperty<String, String> map1 = new SimpleMapProperty<>(om1);
        map1.put("A", "1");
        map1.put("B", "2");

        launch(args);
    }

}
