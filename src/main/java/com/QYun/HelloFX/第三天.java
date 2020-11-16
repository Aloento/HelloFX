package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.TilePane;
import javafx.scene.text.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class 第三天 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 创建文本
        Text T1 = new Text("Hello World");
        Text T2 = new Text("I‘m Here");
        Text T3 = new Text("OpenJFX");
        // 设置文本效果
        T1.setFont(Font.font(20));
        T2.setStyle("-fx-fill: #ff3c3c");
        T3.setFont(Font.font("Helvetica", FontPosture.ITALIC, 20)); // 设置字体与斜体
        T3.setStyle("-fx-font-weight: BOLD"); // 加粗
        // 超链接样式的文本，用.setOnAction处理，它上面的Button也有同样效果
        Hyperlink QA = new Hyperlink("官网", new Button("打开"));

        // 创建文本流
        TextFlow textFlow = new TextFlow();
        textFlow.setStyle("-fx-background-color: #b3b3b3");
        textFlow.setTextAlignment(TextAlignment.CENTER); // 对齐方式
        textFlow.getChildren().addAll(T1, T2, T3, QA);

        // 设置一个按钮与对话框布局
        Button B1 = new Button("弹窗");
        B1.setOnAction(new EventHandler<ActionEvent>() {
            @Override // 按钮按下弹出对话框
            public void handle(ActionEvent event) {
                DialogPane dialogPane = new DialogPane(); // 对话框布局，不是很好用
                dialogPane.setHeaderText("弹窗标题");
                dialogPane.setContentText("这是弹窗");
                dialogPane.getButtonTypes().add(ButtonType.CLOSE); // 创建一个按钮，有应用关闭上一步等按钮
                dialogPane.setGraphic(new ImageView("Ubuntu.png")); // 设置一个图片
                dialogPane.setExpandableContent(new Text("扩展内容")); // 显示详细内容的选项

                Scene scene = new Scene(dialogPane);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("弹窗");
                stage.initOwner(primaryStage);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initStyle(StageStyle.UTILITY);
                stage.show();
                // 将对话框内置的按钮转换为一个实体按钮（返回一个Node）
                Button Close = (Button) dialogPane.lookupButton(ButtonType.CLOSE);
                Close.setOnAction(new EventHandler<ActionEvent>() {
                    @Override // 设置按钮动作：关闭对话框
                    public void handle(ActionEvent event) {
                        stage.close();
                    }
                });

                scheduledService Counter = new scheduledService(dialogPane, stage); // 实体化多线程
                Counter.setDelay(Duration.seconds(1)); // 给多任务一个启动条件，1秒后开始运行
                Counter.setPeriod(Duration.millis(1000)); // 设置间隔1秒运行
                Counter.start(); // 启动计划任务

            }
        });

        // 创建绝对布局，并赋予文本流
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(textFlow, B1);
        AnchorPane.setTopAnchor(textFlow, 50.0); // 设置边距
        AnchorPane.setLeftAnchor(textFlow, 50.0);

        // TilePane磁贴布局的特性就是每一个组件的大小都一样
        // TilePane tilePane = new TilePane(); // 和Grid与FlowPane差不多

        // 设置场景
        Scene scene = new Scene(anchorPane); // 绑定绝对布局到场景
        primaryStage.setScene(scene);
        primaryStage.setTitle("第三天");
        primaryStage.setWidth(400);
        primaryStage.setHeight(400);
        primaryStage.show();

        anchorPane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override // 让文本流的宽度随着布局变化而变化
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                textFlow.setPrefWidth(newValue.doubleValue() - AnchorPane.getLeftAnchor(textFlow));
            } // 减去一个内边距
        });


    }

    public static void main(String[] args) {
        launch();
    }

}

// 定义一个抽象类，多任务，效果是在弹出窗口倒计时
class scheduledService extends ScheduledService<Integer> { // 需要有一个<泛型>
    int i = 3; // 初始化变量
    private DialogPane dialogPane = null;
    private Stage stage = null;
    // 传入参数
    public scheduledService (DialogPane dialogPane, Stage stage){
        this.dialogPane = dialogPane;
        this.stage = stage;
    }

    @Override
    protected Task<Integer> createTask() { // Task是必须方法
        return new Task<Integer>()
        {
            @Override // call不是FX的线程，所以不能更新UI，这里面可以做一些其他的事情，是必须的
            protected Integer call() {
                return i--; // 这里返回的指会给到下面的value
            }

            @Override // 所以我们需要其他方法完成，它是一个FX线程，用来更新UI
            protected void updateValue(Integer value) {
                if (i >= 0)
                    dialogPane.setContentText(value + "秒后关闭");
                else
                {
                    scheduledService.this.stage.close(); // 也可以直接stage.close
                    scheduledService.this.cancel(); // 关闭任务，需要使用.this方法
                }
            }
        };
    }
}

//Written by Aloento.