package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class 第13天 extends Application {

    @Override
    public void start(Stage primaryStage) {
        Platform.exit();
    }

    public static void main(String[] args) {
        // 可观察的list, set, map
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("a", "b", "c", "d", "e");
        FXCollections.rotate(list, 2); // 从倒数第二个开始排序
        list.forEach(System.out::println);

        SimpleStringProperty s1 = new SimpleStringProperty("A"); // 这是个Property
        // Callback
        ObservableList<SimpleStringProperty> call = FXCollections.observableArrayList(param -> {
            System.out.println("call : " + param);
            return new Observable[]{param};
        });

        call.addListener((ListChangeListener<SimpleStringProperty>) change -> {
            while (change.next())
                System.out.println("onChanged : " + change + " wasUpdated : " + change.wasUpdated()); // 这时候update就有效了
        });

        call.addAll(s1);
        s1.set("B"); // update

        launch(args);
    }

}
