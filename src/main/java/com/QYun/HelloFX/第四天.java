package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
        mButton.getItems().addAll(new MenuItem("选项")); // 设置组件
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

        TitledPane tPane = new TitledPane("可展开的按钮", new Button("选项")); // 里面可以放布局
        TitledPane tPane2 = new TitledPane("列表1", new Button("选项2"));
        TitledPane tPane3 = new TitledPane("列表2", new Button("选项3"));
        AnchorPane.setTopAnchor(tPane, 200.0); // 可展开的列表

        Accordion accordion = new Accordion(); // 手风琴折叠式，只能张开一个
        accordion.getPanes().addAll(tPane2, tPane3); // 获得组件
        AnchorPane.setTopAnchor(accordion, 300.0);

        TabPane tabPane = new TabPane(); // 选项卡栏
        Tab tab1 = new Tab("Tab1"); // 选项卡
        Tab tab2 = new Tab("Tab2");
        Tab tab3 = new Tab("Tab3");
        tabPane.getTabs().addAll(tab1, tab2, tab3);
        tabPane.setPrefSize(200, 150);
        AnchorPane.setLeftAnchor(tabPane, 250.0);
        // 给Tab1内容
        HBox hbox = new HBox(10);
        hbox.setStyle("-fx-background-color: #ffffff");
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(new Button("按钮1"), new Button("按钮2"));
        tab1.setContent(hbox);
        tab2.setClosable(false); // Tab2不可关闭
        tabPane.getSelectionModel().select(tab2); // 默认选中Tab2
        tabPane.setSide(Side.LEFT); // 设置选项卡栏朝向

        VBox vBox = new VBox();
        ToggleGroup tGroup2 = new ToggleGroup(); // 单选组
        RadioButton rButton1 = new RadioButton("单选框1");
        RadioButton rButton2 = new RadioButton("单选框2");
        RadioButton rButton3 = new RadioButton("单选框3");
        tGroup2.getToggles().addAll(rButton1, rButton2, rButton3); // 单选效果由它提供
        vBox.getChildren().addAll(rButton1, rButton2, rButton3);
        tGroup2.selectToggle(rButton2); // 默认选中
        AnchorPane.setLeftAnchor(vBox, 250.0);
        AnchorPane.setTopAnchor(vBox, 160.0);
        // 多选选项，不确定状态需要用.isIndeterminate获取
        HBox hBox2 = new HBox();
        CheckBox cBox1 = new CheckBox("多选1");
        CheckBox cBox2 = new CheckBox("多选2");
        CheckBox cBox3 = new CheckBox("多选3");
        cBox2.setIndeterminate(true); // 设置不确定状态
        cBox3.setAllowIndeterminate(true); // 允许设置为不确定状态
        hBox2.getChildren().addAll(cBox1, cBox2, cBox3);
        AnchorPane.setLeftAnchor(hBox2, 250.0);
        AnchorPane.setTopAnchor(hBox2, 220.0);

        // 设置窗口
        aPane.getChildren().addAll(menuBar, mButton, sButton, button_R, tPane, accordion, tabPane, vBox, hBox2);
        Scene scene = new Scene(aPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("第四天");
        primaryStage.setHeight(500);
        primaryStage.setWidth(500);
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

        accordion.expandedPaneProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) // 需要对折叠做处理
                System.out.println(oldValue.getText() + "折叠");
            else System.out.println(newValue.getText() + "展开");
        });

        aPane.setOnMouseClicked(event -> { // 点击一次，输出一次多选状态
            hBox2.getChildren().forEach(item -> {
                CheckBox checkBox = (CheckBox) item;
                System.out.println(checkBox.getText() + "状态是：" + checkBox.isSelected() + "不确定：" + checkBox.isIndeterminate());
            });
        });

    }

    public static void main(String[] args) {
        launch(args);
    }

}
//Written by Aloento.