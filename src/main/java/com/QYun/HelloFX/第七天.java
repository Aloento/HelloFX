package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.time.LocalDate;

public class 第七天 extends Application {
    ScheduledService<Double> schedPbar;

    @Override
    public void start(Stage primaryStage) {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-image: url('QA.jpg')");
        // 颜色选择器
        ColorPicker cPicker = new ColorPicker(Color.valueOf("#FFFFFF")); // 设置默认选择颜色

        // 设置选择动作
        cPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue.toString()); // 获取颜色HEX带透明度
            // 实时修改AP背景颜色
            anchorPane.setStyle("-fx-background-color: #" + newValue.toString().substring(2)); // 删去前两位

        });
        // 日期选择器
        DatePicker dPicker = new DatePicker(LocalDate.now());
        dPicker.setEditable(true); // 能否编辑
        AnchorPane.setLeftAnchor(dPicker, 150.0);

        // 设置选择动作
        dPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue.toString());
            // 可以用.getYear()等方法获得详细信息
        });

        dPicker.setDayCellFactory(new Callback<>() {
            @Override
            public DateCell call(DatePicker param) {
                DateCell cell = new DateCell() {
                    @Override // 这个自定义只会自定义选择日期的东西
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
//                        this.setGraphic();
                    }
                };
                return null;
            }
        });
        // 选择页面的选择栏
        Pagination pagination = new Pagination();
        pagination.setPageCount(10); // 设置页数
        pagination.setMaxPageIndicatorCount(5); // 设置一次显示多少个
        pagination.setCurrentPageIndex(2); // 设置当前页数
        pagination.getStyleClass().addAll(Pagination.STYLE_CLASS_BULLET); // 更改样式
        AnchorPane.setTopAnchor(pagination, 50.0);

        // 获得页数
        pagination.currentPageIndexProperty().addListener((observable, oldValue, newValue) -> System.out.println(newValue.intValue() + 1));

        // 设置页面内容
        pagination.setPageFactory(param -> { // param就是选择的页数
            return switch (param) { // 设置每一页内容
                case 1 -> new Button("2");
                case 2 -> new Button("3");
                default -> new Button("?");
            };
        });
        // 可拖动的进度条
        Slider slider = new Slider(0, 10, 4);
        slider.setShowTickLabels(true); // 显示值
        slider.setShowTickMarks(true); // 显示刻度
        slider.setMajorTickUnit(1); // 设置间距
        AnchorPane.setTopAnchor(slider, 50.0);
        AnchorPane.setLeftAnchor(slider, 200.0);

//        slider.setLabelFormatter(new StringConverter<Double>() {
//            @Override
//            public String toString(Double object) {
//                // 这里可以设置下面值的样式
//                return null;
//            }
//
//            @Override
//            public Double fromString(String string) {
//                return null;
//            }
//        });

        // 值改变时的动作
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println(newValue);
            // slider.valueChangingProperty() 是拿来通知改变值的
        });

//        new Thread(new Runnable() {
//            @Override // 自动前进，初级方式
//            public void run() {
//                for (int i = 0; i <=10; i++)
//                {
//                    int finalI = i;
//                    Platform.runLater(new Runnable() {
//                        @Override // 再怎么也得在FX线程里面更新
//                        public void run() {
//                            slider.setValue(finalI);
//                        }
//                    });
//                    try {
//                        Thread.sleep(500); // 实际开发不能这么些
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
        // 用这种方式来自动更新进度条
        Cron cron = new Cron(slider);
        cron.setDelay(Duration.millis(0));
        cron.setPeriod(Duration.millis(500));
        cron.start();

        // 不可拖动的进度条
        ProgressBar pBar = new ProgressBar(0.4); // 0-1
        pBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS); // 设置成未知进度
        AnchorPane.setTopAnchor(pBar, 150.0);
        // 圆形进度条实心
        ProgressIndicator pIndicator = new ProgressIndicator();
        AnchorPane.setTopAnchor(pIndicator, 200.0);

        Button B1 = new Button("B1");
        Button B2 = new Button("B2");
        SplitPane sp = new SplitPane(); // 分割面板
        sp.setPrefWidth(200);
        // 需要StackPane，给StackPane做大小限制就可以限制拖动范围
        StackPane stack1 = new StackPane(B1);
        StackPane stack2 = new StackPane(B2);
        sp.getItems().addAll(stack1, stack2);
        sp.setDividerPosition(0, 0.5); // 平均分，设置默认位置
        AnchorPane.setTopAnchor(sp, 250.0);

        anchorPane.getChildren().addAll(cPicker, dPicker, pagination, slider, pBar, pIndicator, sp);
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.setTitle("第七天");
        primaryStage.setHeight(400);
        primaryStage.setWidth(400);
        primaryStage.show();

        schedPbar = new ScheduledService<>() { // 更新进度条
            double i = 0;
            @Override
            protected Task<Double> createTask() {
                return new Task<>() {
                    @Override
                    protected Double call() {
                        return i = i + 0.1;
                    }

                    @Override
                    protected void updateValue(Double value) {
//                        super.updateValue(value);
                        pBar.setProgress(value);
                        pIndicator.setProgress(value);
                        if (value >= 1) // 需要大于等于1
                            schedPbar.cancel();
                    }
                };
            }
        };

        schedPbar.setDelay(Duration.millis(0));
        schedPbar.setPeriod(Duration.millis(500));
        schedPbar.start();

    }
    
    public static void main(String[] args) {
        launch(args);
    }

}

class Cron extends ScheduledService<Integer>
{ // 自动更新
    int i = 0;
    Slider s;
    public Cron (Slider s) {this.s = s;}

    @Override
    protected Task<Integer> createTask() {

        return new Task<>() {

            @Override
            protected Integer call() {
                return i++;
            }

            @Override
            protected void updateValue(Integer value) {
//                super.updateValue(value); // 这个语句与cron.valueProperty有关系
                s.setValue(value);
            }
        };
    }
}

//Written by Aloento.