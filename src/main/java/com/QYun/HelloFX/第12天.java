package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.beans.binding.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class 第12天 extends Application {

    @Override
    public void start(Stage primaryStage) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-image: url('QA.jpg')");

        ObservableList<String> observableList = FXCollections.observableArrayList();
        SimpleListProperty<String> list = new SimpleListProperty<>(observableList);
        list.addAll("A", "B");

        TextField t1 = new TextField();
        TextField t2 = new TextField();
        HBox hBox = new HBox(5);
        VBox vBox = new VBox(5);
        VBox date = new VBox(5);

        for (int i = 0; i < list.size(); i++) {
            Label label = new Label();
            label.textProperty().bind(list.valueAt(i));
            date.getChildren().add(label);
        }

        hBox.getChildren().addAll(t1, t2);
        vBox.getChildren().addAll(hBox, date);
        anchorPane.getChildren().addAll(vBox);

        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.setHeight(400);
        primaryStage.setWidth(400);
        primaryStage.setTitle("第12天");
        primaryStage.show();

        t2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (t1.getText().equals("") || Integer.parseInt(t1.getText()) > list.size())
                return;

            try {
                int i = Integer.parseInt(t1.getText());
                list.set(i, t2.getText());
            } catch (Exception ignored) {}
        });

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

        // set和map也是一样的，且set是无序的
        ObservableSet<String> obs1 = FXCollections.observableSet("A", "B");
        SimpleSetProperty<String> set1 = new SimpleSetProperty<>(obs1);

        ObservableSet<String> obs2 = FXCollections.observableSet("C", "D");
        SimpleSetProperty<String> set2 = new SimpleSetProperty<>(obs2);

        System.out.println("set1：" + set1.getValue() + "\t" + "set2：" + set2.getValue());
        set1.bind(set2);
        // set1.bindContent(set2);
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

        // valueAt方法
        SimpleIntegerProperty index = new SimpleIntegerProperty(2); // 可以动态变化
        ObjectBinding<String> objectBinding = list1.valueAt(index);
        System.out.println(objectBinding.get()); // binding和list1中index的值绑定在一起

        // Bindings工具类
        SimpleIntegerProperty value = new SimpleIntegerProperty(10);
        System.out.println(Bindings.concat("value = ", value.asString("%s")).get());
        System.out.println(Bindings.format("value = %s", value).get() + "\n");
        // 和直接String不同，这样可以动态变化值
        value.set(20);
        System.out.println(Bindings.concat("value = ", value.asString("%s")).get());
        System.out.println(Bindings.format("value = %s", value).get());
        System.out.println(Bindings.max(x, y).intValue()); // 传回最大的那个数

        // 创建自定义绑定，里面写绑定转换规则
        System.out.println(Bindings.createStringBinding(() -> {
            String value1 = "Hello";
            if (x.greaterThan(y).get())
                value1 = "true";
            return value1;
        }, x, y).get());

        // 一个很复杂的绑定，要求引用的类是public，与最后一个参数绑定，自定义class中get方法名称必须为 变量名Property
        // 类型 名称 = new 类型();
        // SimpleObjectProperty<类型> 对象 = new SimpleObjectProperty<类型>(名称);
        // StringBinding select = Bindings.selectString(对象, "进入第一个类获取，变量名", "进第二个类获取");
        // System.out.println(select.get());

        // 自定义绑定计算
        SimpleIntegerProperty b = new SimpleIntegerProperty(30);
        MyIntegerBinding my = new MyIntegerBinding(10);
        System.out.println(my.get() + "\t" + b.get());
        b.bind(my);
        System.out.println(my.get() + "\t" + b.get());

        launch(args);
    }

}

class MyIntegerBinding extends IntegerBinding
{ // 自定义绑定计算
    private SimpleIntegerProperty a = new SimpleIntegerProperty();

    public MyIntegerBinding(int value) {
        this.bind(a);
        a.set(value);
    }

    @Override
    protected int computeValue() {
        return a.get() * 2; // 自定义计算
    }
}

//Written by Aloento.