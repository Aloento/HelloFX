package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCombination;
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
        Menu menu5 = new Menu("菜单5");
        // 新建选项
        MenuItem mItem1 = new MenuItem("选项1");
        MenuItem mItem2 = new MenuItem("选项2");
        MenuItem mItem3 = new MenuItem("选项3");
        MenuItem mItem4 = new MenuItem("选项4");
        mItem3.setDisable(true); // 把选项3禁用
        mItem4.setAccelerator(KeyCombination.valueOf("ctrl+k")); // 设置快捷键

        // 设置单选选项
        ToggleGroup tGroup = new ToggleGroup(); // 首先需要有一个组
        RadioMenuItem rMenuItem1 = new RadioMenuItem("单选1");
        RadioMenuItem rMenuItem2 = new RadioMenuItem("单选2");
        RadioMenuItem rMenuItem3 = new RadioMenuItem("单选3");
        // 设置多选选项，不需要加在组里面
        CheckMenuItem cMenuItem1 = new CheckMenuItem("多选1");
        CheckMenuItem cMenuItem2 = new CheckMenuItem("多选2");
        CheckMenuItem cMenuItem3 = new CheckMenuItem("多选3");
        // 设置可自定义的选项
        CustomMenuItem cItem1 = new CustomMenuItem(new Button("自定义"));
        CustomMenuItem cItem2 = new CustomMenuItem(); // 这上面甚至可以放布局如VBox
        ProgressBar pBar = new ProgressBar(0.5); // 设置一个进度条，进度一半
        cItem2.setContent(pBar); // 把进度条给自定义选项

        // 把单选选项放到组里面
        rMenuItem1.setToggleGroup(tGroup);
        rMenuItem2.setToggleGroup(tGroup);
        rMenuItem3.setToggleGroup(tGroup);
        rMenuItem1.setSelected(true); // 把单选1设置为默认值
        // 菜单下面还可以有别的菜单
        menu2.getItems().addAll(rMenuItem1, rMenuItem2, rMenuItem3);
        menu3.getItems().addAll(cMenuItem1, cMenuItem2, cMenuItem3);
        menu4.getItems().addAll(mItem4); // 选项给菜单
        menu5.getItems().addAll(cItem1, cItem2);
        menu1.getItems().addAll(mItem1, sMenuItem1, mItem2, sMenuItem2, mItem3, menu4); // 把选项与分割线给菜单，再给一个菜单4
        menuBar.getMenus().addAll(menu1, menu2, menu3, menu5); // 把菜单给菜单栏
        
        MenuButton mButton = new MenuButton("下拉菜单按钮"); // 创建一个下拉菜单
        mButton.getItems().addAll(menu4); // 设置组件
        AnchorPane.setTopAnchor(mButton, 50.0); // 设置间距

        SplitMenuButton sButton = new SplitMenuButton(); // 这玩意可以点击
        sButton.setText("分离下拉菜单");
        sButton.getItems().addAll(menu4);
        AnchorPane.setTopAnchor(sButton, 100.0);

        ContextMenu cMenu = new ContextMenu(); // 右键菜单
        cMenu.getItems().addAll(new MenuItem("右键菜单"));
        Button button_R = new Button("单击右键");
        button_R.setContextMenu(cMenu); // 绑定右键菜单
        AnchorPane.setTopAnchor(button_R, 150.0);

        // 设置窗口
        aPane.getChildren().addAll(menuBar, mButton, sButton, button_R);
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

        mItem4.setOnMenuValidation(event -> { // 这是专门响应快捷键的
            System.out.println("快捷键");
        });
        
        button_R.setOnContextMenuRequested(event -> { // 弹出右菜单时的动作
            System.out.println("弹出右菜单");
        });

    }

    public static void main(String[] args) {
        launch(args);
    }

}
//Written by Aloento.