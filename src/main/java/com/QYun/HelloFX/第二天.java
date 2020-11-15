package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
// import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class 第二天 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 设置元数据
        primaryStage.setTitle("第二天");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        // 绑定场景
        Group Group_one = new Group(); // 新建组，组属于低级的容器，不太算布局，只相当于辅助线的功能
        Scene Scene_one = new Scene(Group_one); // 新建场景并绑定布局
        primaryStage.setScene(Scene_one); // 绑定场景

        Stage stage_Note = new Stage(); // 新建一个子窗口
        stage_Note.setTitle("Q-Audio"); // 元数据设置
        stage_Note.setHeight(570);
        stage_Note.setWidth(950);

        stage_Note.initOwner(primaryStage); // 设置拥有者
        stage_Note.initStyle(StageStyle.UTILITY); // 对话框模式
        stage_Note.setAlwaysOnTop(true); // 置顶

        AnchorPane anchorPane_one = new AnchorPane(); // 创建一个锚板，是真正的绝对布局
        stage_Note.setScene(new Scene(anchorPane_one)); // 新建场景并绑定布局
        // 给锚板设置背景图像，并且开启自适应大小，Group不能设置这些元素
        anchorPane_one.setBackground(new Background(new BackgroundImage(new Image("QA.jpg"), null, null, null, new BackgroundSize(0, 0, false, false, true, false))));

        TextField textField_one = new TextField(); // 新建文本框
        textField_one.setText("默认文本"); // 设定默认内容
        textField_one.setFont(Font.font(16)); // 字体大小
        textField_one.setLayoutX(100);
        textField_one.setLayoutY(100); // 设定相对位置
        textField_one.setPrefSize(200, 50); // 设置宽高
        Group_one.getChildren().add(textField_one); // 让组获得文本框

        textField_one.setTooltip(new Tooltip("这是提示")); // 设置鼠标停留提示
        textField_one.setPromptText("请输入不超过7个字"); // 设置在失焦且空内容下的提示文本

        textField_one.selectedTextProperty().addListener(new ChangeListener<String>() { // 用户选择文本监听
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("选择了：" + newValue);
            }
        });

        PasswordField passwordField_one = new PasswordField(); // 新建密码框，上面的方法都能用
        Group_one.getChildren().add(passwordField_one); // 获得孩子（

        passwordField_one.textProperty().addListener(new ChangeListener<String>() { // 监听输入
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > 7) //限制用户只能输入7个字
                {
                    passwordField_one.setText(oldValue);
                    System.out.println(oldValue); // 打印最终密码
                }
                else System.out.println(newValue); // 打印当前输入
            }
        });

        // 创建两个按钮给锚板使用
        Button button_1 = new Button("按钮一");
        AnchorPane.setTopAnchor(button_1, 40.0); // 设置距离顶部距离和左边距离
        AnchorPane.setLeftAnchor(button_1, 40.0); // 也可以用anchorPane_one.设置
        // 设置的是相对于父控件的绝对位置，可以拿来适窗口大小
        Button button_2 = new Button("按钮二");
        // AnchorPane.setTopAnchor(button_2, 0.0); // 内边距需要与其一同使用
        // AnchorPane.setLeftAnchor(button_2, 0.0); // 否则没有效果
        // 当使用AnchorPane管理组件位置时，组件自己的.setLayout会无效
        anchorPane_one.setPadding(new Insets(20)); // 设置锚板的内边距，所有组件会相对于内边距布局
        anchorPane_one.getChildren().add(button_1); // 锚板获得按钮
        // 也可以把锚板和组结合使用，组内的控件是锚板的孙子，只能通过组来控制
        
        AnchorPane anchorPane_2 = new AnchorPane(); // 设置第二个锚板
        anchorPane_2.setStyle("-fx-background-color:green;"); // 设置2的背景
        anchorPane_one.getChildren().add(anchorPane_2); // 设置2为1的子类

        primaryStage.show(); //显示窗口
        stage_Note.show();

        AnchorPane.setTopAnchor(anchorPane_2, 0.0);
        AnchorPane.setLeftAnchor(anchorPane_2, 40.0);
        // 设置A2随着A1的变化而变化，需要在.show之后才有效
        AnchorPane.setRightAnchor(anchorPane_2, anchorPane_one.getWidth() / 2);
        AnchorPane.setBottomAnchor(anchorPane_2, anchorPane_one.getHeight() /2);

        primaryStage.heightProperty().addListener(new ChangeListener<Number>() { // 让B2的高随始终是B1的一半
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                AnchorPane.setBottomAnchor(anchorPane_2, anchorPane_one.getHeight() /2);
            }
        }); // 简易方法，更高级的方法是绑定

        anchorPane_2.getChildren().add(button_2); // 让B2是A2的子类
        AnchorPane.setRightAnchor(button_2, 0.0); // 让B2始终在A2的右下角
        AnchorPane.setBottomAnchor(button_2, 0.0);
        
        // button_2.setManaged(false); // 让B2脱离管理，不在原来的位置上了
        // button_2.setVisible(true); // 让B2不可见，还在原来的位置上
        // button_2.setOpacity(1); // 设置透明度

        HBox hBox_1 = new HBox(); // 布局，水平排列
        // VBox vBox_1 = new VBox(); // 布局。纵向排列
        hBox_1.setPrefSize(500, 500); // 设置布局的大小
        hBox_1.setStyle("-fx-background-color:red;"); // 给布局一个颜色
        hBox_1.setPadding(new Insets(10)); // 设置内边距
        hBox_1.setSpacing(10); // 设置子组件之间的间距
        HBox.setMargin(textField_one, new Insets(10)); // 设置某个子组件的3外边距
        hBox_1.setAlignment(Pos.CENTER); // 设置排列居中
        hBox_1.getChildren().addAll(textField_one, passwordField_one); // 让文本框属于布局
        Group_one.getChildren().add(hBox_1); // 让布局属于组

        

    }

    public static void main(String[] args)
    {
        launch(); //启动舞台
    }

}
//Written by Aloento.