package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class 第四天 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 新建绝对布局
        AnchorPane aPane = new AnchorPane();
        aPane.setStyle("-fx-background-image: url('QA.jpg');");

        // 新建菜单栏与分割线
        MenuBar menuBar = new MenuBar();
        SeparatorMenuItem sMenuItem1 = new SeparatorMenuItem();
        SeparatorMenuItem sMenuItem2 = new SeparatorMenuItem();
        // 新建菜单
        Menu menu1 = new Menu("菜单1");
        Menu menu2 = new Menu("菜单2");
        Menu menu3 = new Menu("菜单3");
        Menu menu4 = new Menu("菜单4");
        // 新建选项
        MenuItem mItem1 = new MenuItem("选项1");
        MenuItem mItem2 = new MenuItem("选项2");
        MenuItem mItem3 = new MenuItem("选项3");
        MenuItem mItem4 = new MenuItem("选项4");
        mItem3.setDisable(true); // 把选项3禁用

        // 设置单选选项
        ToggleGroup tGroup = new ToggleGroup(); // 首先需要有一个组
        RadioMenuItem rMenuItem1 = new RadioMenuItem("单选1");
        RadioMenuItem rMenuItem2 = new RadioMenuItem("单选2");
        RadioMenuItem rMenuItem3 = new RadioMenuItem("单选3");
        // 设置多选选项，不需要加在组里面
        CheckMenuItem cMenuItem1 = new CheckMenuItem("多选1");
        CheckMenuItem cMenuItem2 = new CheckMenuItem("多选2");
        CheckMenuItem cMenuItem3 = new CheckMenuItem("多选3");

        // 把单选选项放到组里面
        rMenuItem1.setToggleGroup(tGroup);
        rMenuItem2.setToggleGroup(tGroup);
        rMenuItem3.setToggleGroup(tGroup);
        rMenuItem1.setSelected(true); // 把单选1设置为默认值
        // 菜单下面还可以有别的菜单
        menu2.getItems().addAll(rMenuItem1, rMenuItem2, rMenuItem3);
        menu3.getItems().addAll(cMenuItem1, cMenuItem2, cMenuItem3);
        menu4.getItems().addAll(mItem4); // 选项给菜单
        menu1.getItems().addAll(mItem1, sMenuItem1, mItem2, sMenuItem2, mItem3, menu4); // 把选项与分割线给菜单，再给一个菜单4
        menuBar.getMenus().addAll(menu1, menu2, menu3); // 把菜单给菜单栏

        // 设置窗口
        aPane.getChildren().addAll(menuBar);
        Scene scene = new Scene(aPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("第四天");
        primaryStage.setHeight(400);
        primaryStage.setWidth(400);
        primaryStage.show();
        
        rMenuItem2.setOnAction(event -> { // 给单选2设置动作
            // RadioMenuItem status = (RadioMenuItem) event.getSource();
            // System.out.println(status.isSelected());
            System.out.println("选择R2：" + rMenuItem2.isSelected());
        });

        cMenuItem2.setOnAction(event -> { // 设置动作
            System.out.println("选择C2:" + cMenuItem2.isSelected());
        });
        
    }

    public static void main(String[] args) {
        launch(args);
    }

}
//Written by Aloento.