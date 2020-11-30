package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;

public class 第11天 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 单向绑定
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(5);

        x.bind(y); // X绑定在Y的上面，Y是什么X就是什么
//      x.set(8); // 无效语句，不能操作x
        y.set(10);

        System.out.println("单向绑定：" + x.get() + " - " + y.get());
        x.unbind(); // 解除绑定

        // 双向绑定
        SimpleIntegerProperty a = new SimpleIntegerProperty(2);
        SimpleIntegerProperty b = new SimpleIntegerProperty(6);
        a.bindBidirectional(b); // 把a绑定在b上面，初始化的时候以括号内的为主
        // 它俩互相影响
        a.set(100);
        b.set(200);

        System.out.println("双向绑定：" + a.get() + " - " + b.get());
        a.unbindBidirectional(b); // 接触绑定顺序不重要

        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
//Written by Aloento.