package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.binding.NumberBinding;
import javafx.beans.binding.When;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
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



        launch(args);
    }

}
