package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.Mnemonic;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        // String javaVersion = System.getProperty("java.version");
        // String javafxVersion = System.getProperty("javafx.version");

        stage.setTitle("JavaFX"); //设置标题
        stage.getIcons().add(new Image("ubuntu.png")); //设置最小化图标

        stage.setHeight(800); //设置窗口宽高
        stage.setWidth(800);

        stage.setMaxHeight(1000);
        stage.setMaxWidth(1000);

        stage.setMinHeight(300);
        stage.setMinWidth(300);

        Label l = new Label("Hello, JavaFX " + System.getProperty("javafx.version") + ", running on Java "
                + System.getProperty("java.version") + "."); //创建一个不可修改的标签
        l.setCursor(Cursor.TEXT); //为部件单独设置鼠标形态
        l.setFont(Font.font(30)); //改变字体
        l.setTextFill(Paint.valueOf("#6200EA")); //为文字设置颜色
        // Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(new Scene(new StackPane(l), 640, 480)); // 设置画布

        // stage.setResizable(false);
        // stage.setFullScreen(true); //自动全屏
        // stage.setIconified(true); //自动最小化
        // stage.setMaximized(true); //自动最大化

        stage.setX(500); // 设置窗口始位置
        stage.setY(100); // 屏幕左上角为0.0

        // stage.initStyle(StageStyle.UNDECORATED); //纯白背景无标题
        // stage.initStyle(StageStyle.UNIFIED); //颜色统一
        // stage.initStyle(StageStyle.UTILITY); //对话框模式

        stage.yProperty().addListener(new ChangeListener<Number>() { //获取属性并创建监听
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("当前Y轴：" + newValue.doubleValue());
            }
        });

        stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("宽度：" + newValue.doubleValue());
            }
        });

        Stage Note = new Stage(); //正常创建窗口
        Note.setTitle("Hello");
        Note.initStyle(StageStyle.UTILITY);
        Note.initModality(Modality.WINDOW_MODAL); // 模态化窗口

        Note.setOpacity(0.9); // 设置背景透明度
        Note.setAlwaysOnTop(true); // 窗口置顶
        Note.initOwner(stage); // 设定窗口拥有者，.WINDOW_MODAL使用需要

        Button B1 = new Button("Hello World"); //创建按钮
        Button B2 = new Button("I'm Here");
        B1.setLayoutY(50); B2.setLayoutY(120); //设置按钮相对父级（Group）位置
        Group group = new Group(B1, B2); //创建组并绑定按钮

        Scene wait = new Scene(group, 320, 240); //创建scene并绑定组为根
        wait.setCursor(Cursor.cursor(new Image("ubuntu.png").getUrl())); //设置鼠标在scene上的样式
        Note.setScene(wait); //绑定Scene

        wait.addMnemonic(new Mnemonic(B2, new KeyCodeCombination(KeyCode.SPACE, KeyCombination.CONTROL_DOWN))); //设置Ctrl+空格快捷键给B2按钮，普通方法
        wait.getAccelerators().put(new KeyCodeCombination(KeyCode.B, KeyCodeCombination.SHORTCUT_DOWN), new Runnable() { //设置快捷键的最好方法
            @Override
            public void run() {
                System.out.println("按下快捷键"); }
        });

        Object[] button = group.getChildren().toArray(); //把组转换为一个数组
        for (Object i : button) {
            Button Temp = (Button)i;
            Temp.setBackground(new Background(new BackgroundFill(Paint.valueOf("#BDBDBD"), new CornerRadii(5), new Insets(3)))); //设置按钮背景与弧度
            Temp.setBorder(new Border(new BorderStroke(Paint.valueOf("#424242"), BorderStrokeStyle.DASHED, new CornerRadii(5), new BorderWidths(2)))); //创建边框，传统方式
            Temp.setLayoutX(115); //批量对组内元素进行操作
            Temp.setPrefSize(100, 40); //设置按钮宽高
        }

        B2.setStyle( //CSS方式修改按钮
                        "-fx-background-color:#795548;" + 
                        "-fx-background-radius:5;" +
                        "-fx-text-fill:#EFEBE9;"
                    );

        System.out.println(Platform.isSupported(ConditionalFeature.SCENE3D)); //检测电脑是否支持某个功能
        Platform.runLater(new Runnable() { //共用线程的稍后运行，实际上是个队列
            @Override
            public void run() {
                int i = 1;
                while (i > 0) { //三秒倒计时锁定GUI
                    try {
                        Thread.sleep(1000); //休眠一秒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i--; System.out.println(i);
                }
            }
        });

        System.out.println("屏幕可视高度：" + Screen.getPrimary().getVisualBounds().getHeight());
        System.out.println("屏幕实际高度：" + Screen.getPrimary().getBounds().getHeight());
        System.out.println("DPI：" + Screen.getPrimary().getDpi());

        B1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() { //冒泡事件处理鼠标点击
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1 && event.getButton().name().equals(MouseButton.SECONDARY.name())) //鼠标右击
                    System.out.println("Hello World");
                else if (event.getClickCount() == 2 && event.getButton().name().equals(MouseButton.PRIMARY.name())) //鼠标双击
                    System.out.println("I'm Here");
            }
        }); 
        
        B1.setOnKeyTyped(new EventHandler<KeyEvent>() { //获取键盘输入（有问题）
            @Override
            public void handle(KeyEvent event) {
                System.out.print(event.getText()); //可处理大小写
                System.out.print(event.getCode().getName()); //可处理功能键
            }
        });

        B2.setOnAction(new EventHandler<ActionEvent>(){ //Button单击事件简单写法
            @Override
            public void handle(ActionEvent event) { //event可以获取事件源信息
                HostServices Web = getHostServices();
                Web.showDocument("www.Q-Audio.org"); //用默认浏览器打开一个网页
            }
        });
        
        stage.show();
        Note.show();
        // stage.close();

        // Platform.setImplicitExit(false); //即使窗口关闭也不终止程序
        // Platform.exit(); //则需要用此关闭
    }

    public static void main(String[] args)
    {
        launch();
    }

}
