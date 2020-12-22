package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class 第14天 extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-image: url('QA.jpg')");
        HBox hBox = new HBox(5);
        // 图片类，文件流
        FileInputStream fis1 = new FileInputStream("C:\\Users\\SoarT\\Pictures\\Aloento.jpg");
        Image image1 = new Image(fis1, 200, 0, true, true);
        ImageView view1 = new ImageView(image1);
        // 相对路径，本地绝对路径需要加file:
        Image image2 = new Image("ubuntu.png");
        ImageView view2 = new ImageView(image2);
        // URL网络图片后台异步加载
        Image image3 = new Image("https://q-audio.org/images/LogoA.png", 100, 0, true, true, true);
        ImageView view3 = new ImageView(image3);
        // 通过类加载器加载图片
        URL url = getClass().getClassLoader().getResource("ubuntu.png");
        Image image4 = new Image(Objects.requireNonNull(url).toExternalForm());
        ImageView view4 = new ImageView(image4);

        hBox.getChildren().addAll(view1, view2, view3, view4);
        anchorPane.getChildren().addAll(hBox);
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.setWidth(400);
        primaryStage.setHeight(400);
        primaryStage.setTitle("第14天");
        primaryStage.show();
        // 显示加载进度
        image3.progressProperty().addListener((observableValue, number, t1) -> System.out.println(t1.doubleValue()));

    }

    public static void main(String[] args) {
        launch(args);
    }

}
