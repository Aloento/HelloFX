package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class 第四天 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 新建绝对布局
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-image: url('QA.jpg');");
        // 新建菜单栏与分割线
        MenuBar menuBar = new MenuBar();
        SeparatorMenuItem separator1 = new SeparatorMenuItem();
        SeparatorMenuItem separator2 = new SeparatorMenuItem();
        // 新建菜单
        Menu menu1 = new Menu("菜单1");
        Menu menu2 = new Menu("菜单2");
        Menu menu3 = new Menu("菜单3");
        Menu menu4 = new Menu("菜单4");
        // 新建选项
        MenuItem menuItem1 = new MenuItem("选项1");
        MenuItem menuItem2 = new MenuItem("选项2");
        MenuItem menuItem3 = new MenuItem("选项3");
        MenuItem menuItem4 = new MenuItem("选项4");
        // 菜单下面还可以有别的菜单
        menu4.getItems().addAll(menuItem4); // 选项给菜单
        menu1.getItems().addAll(menuItem1, separator1, menuItem2, separator2, menuItem3, menu4); // 把选项与分割线给菜单，再给一个菜单4
        menuBar.getMenus().addAll(menu1, menu2, menu3); // 把菜单给菜单栏
        // 设置窗口
        anchorPane.getChildren().addAll(menuBar);
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("第四天");
        primaryStage.setHeight(400);
        primaryStage.setWidth(400);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

}
//Written by Aloento.