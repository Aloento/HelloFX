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
        TextField t3 = new TextField();

        AnchorPane.setTopAnchor(t1, 50.0);
        AnchorPane.setTopAnchor(t2, 50.0);
        AnchorPane.setTopAnchor(t3, 25.0);
        AnchorPane.setLeftAnchor(t2, 200.0);

        anchorPane.getChildren().addAll(b1, t1, t2, t3);
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.setTitle("第11天");
        primaryStage.setWidth(400);
        primaryStage.setHeight(400);
        primaryStage.show();

        SimpleIntegerProperty w = new SimpleIntegerProperty(2);
        // 高效单向绑定，按钮随着AP变化而变化，且按钮不得自己变化，（绑定计算Binding）
        b1.prefWidthProperty().bind(anchorPane.widthProperty().divide(w));
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

        t3.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int width = Integer.parseInt(newValue);
                w.set(width);
            } catch (Exception ignored) {}
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

        // 绑定判断
        System.out.println(a.lessThan(b).get());
        System.out.println(a.isEqualTo(y, 100)); // 后面那个数字是允许的误差

        launch(args);
    }

}
//Written by Aloento.