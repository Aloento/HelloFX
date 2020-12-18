package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class 第13天 extends Application {

    @Override
    public void start(Stage primaryStage) {

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-image: url('QA.jpg')");

        Button b1 = new Button("单选");
        Button b2 = new Button("多选");
        Button b3 = new Button("打开");
        Button b4 = new Button("保存");
        TextArea textArea = new TextArea();
        textArea.setWrapText(true);

        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(b1, b2, b3, b4, textArea);

        anchorPane.getChildren().addAll(vBox);
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.setHeight(400);
        primaryStage.setWidth(400);
        primaryStage.setTitle("第13天");
        primaryStage.show();

        // 文件单选打开弹窗，获取文件路径
        b1.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("单选文件");
            fileChooser.setInitialDirectory(new File("C:" + File.separator + "temp")); // 设置默认目录

            fileChooser.getExtensionFilters().addAll( // 设定可选的类型
                    new FileChooser.ExtensionFilter("图片类型", "*.jpg", "*.png", "*.gif"),
                    new FileChooser.ExtensionFilter("文本类型", "*.txt", "*.docx")
            );

            File open = fileChooser.showOpenDialog(new Stage());
            if (open != null) // 空安全
                System.out.println(open.getAbsolutePath()); // 获取绝对路径
        });
        // 文件多选
        b2.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("多选文件");
            fileChooser.setInitialDirectory(new File("C:" + File.separator + "temp")); // 设置默认目录

            fileChooser.getExtensionFilters().addAll( // 设定可选的类型
                    new FileChooser.ExtensionFilter("图片类型", "*.jpg", "*.png", "*.gif"),
                    new FileChooser.ExtensionFilter("文本类型", "*.txt", "*.docx")
            );

            List<File> list = fileChooser.showOpenMultipleDialog(new Stage()); // 区别
            if (list != null) // 空安全
                list.forEach(file -> System.out.println(file.getAbsolutePath()));
        });

        b3.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("打开文本");
            fileChooser.setInitialDirectory(new File("C:" + File.separator + "temp")); // 设置默认目录

            fileChooser.getExtensionFilters().addAll( // 设定可选的类型
                    new FileChooser.ExtensionFilter("文本类型", "*.txt", "*.docx"));

            File open = fileChooser.showOpenDialog(new Stage());
            if (open == null) // 空安全
                return;
            System.out.println(open.getAbsolutePath()); // 获取绝对路径

            try { // 文本文档打开
                FileInputStream fileInputStream = new FileInputStream(open); // 文件流方式改变编码GBK
                InputStreamReader reader = new InputStreamReader(fileInputStream, "GBK");
                // FileReader fileReader = new FileReader(open); // IO操作
                BufferedReader bufferedReader = new BufferedReader(reader);
                String str;
                while ((str = bufferedReader.readLine()) != null)
                    textArea.appendText(str);

                reader.close(); // 关闭流
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        b4.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("保存文本");
            fileChooser.setInitialFileName("文本");
            fileChooser.setInitialDirectory(new File("C:" + File.separator + "temp")); // 设置默认目录

            fileChooser.getExtensionFilters().addAll( // 设定可选的类型
                    new FileChooser.ExtensionFilter("文本类型", "*.txt", "*.docx"));

            File file = fileChooser.showSaveDialog(new Stage());
            if (file == null) // 空安全
                return;
            System.out.println(file.getAbsolutePath()); // 获取绝对路径

            try {
                // file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file); // 文件流
                OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8); // 输出流
                osw.write(textArea.getText());  // 获得内容

                osw.close(); // 流关闭顺序重要
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }

    public static void main(String[] args) {
        // 可观察的list, set, map
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("a", "b", "c", "d", "e");
        FXCollections.rotate(list, 2); // 从倒数第二个开始排序
        list.forEach(System.out::println);

        SimpleStringProperty s1 = new SimpleStringProperty("A"); // 这是个Property
        SimpleStringProperty s2 = new SimpleStringProperty("B");
        // Callback，需要自己实现
        ObservableList<SimpleStringProperty> call1 = FXCollections.observableArrayList(param -> {
            System.out.println("call : " + param);
            return new Observable[]{param};
        });
        // 可观察列表，同步call1，调用顺序不同
        ObservableList<SimpleStringProperty> call2 = FXCollections.observableList(call1, param -> {
            System.out.println("call2 : " + param);
            return new Observable[] {param};
        });

        call1.addListener((ListChangeListener<SimpleStringProperty>) change -> {
            while (change.next()) // 这时候update就有效了
                System.out.println("onChanged : " + change + " wasUpdated : " + change.wasUpdated());
        });

        call1.addAll(s1);
        s1.set("B"); // update
        call2.addAll(s2);

        launch(args);
    }

}
