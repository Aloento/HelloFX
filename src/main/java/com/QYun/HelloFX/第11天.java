package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class 第11天 extends Application {

    @Override
    public void start(Stage primaryStage) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-image: url('QA.jpg')");

        Button b1 = new Button("按钮");
        TextField t1 = new TextField();
        TextField t2 = new TextField();

        AnchorPane.setTopAnchor(t1, 50.0);
        AnchorPane.setTopAnchor(t2, 50.0);
        AnchorPane.setLeftAnchor(t2, 200.0);

        anchorPane.getChildren().addAll(b1, t1, t2);
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.setTitle("第11天");
        primaryStage.setWidth(400);
        primaryStage.setHeight(400);
        primaryStage.show();

        // 高效单向绑定，按钮随着AP变化而变化，且按钮不得自己变化
        b1.prefWidthProperty().bind(anchorPane.widthProperty());
//      b1.translateXProperty().bind();
        // 双向绑定的文本输入框，还可以设置数据格式
        t1.textProperty().bindBidirectional(t2.textProperty(), new StringConverter<>() {
            @Override
            public String toString(String object) {
                return object;
            }

            @Override
            public String fromString(String string) {
                if (string.contains("你妈"))
                    return string.replace("你妈", "我妈");
                return string;
            }
        });

    }

    public static void main(String[] args) {
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

        launch(args);
    }

}
//Written by Aloento.