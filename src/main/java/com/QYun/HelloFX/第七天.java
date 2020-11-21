package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;

public class 第七天 extends Application {

    @Override
    public void start(Stage primaryStage) {
        AnchorPane anchorPane = new AnchorPane();
        // 颜色选择器
        ColorPicker cPicker = new ColorPicker(Color.valueOf("#FFFFFF")); // 设置默认选择颜色

        cPicker.valueProperty().addListener(new ChangeListener<Color>() {
            @Override // 设置选择动作
            public void changed(ObservableValue<? extends Color> observable, Color oldValue, Color newValue) {
                System.out.println(newValue.toString()); // 获取颜色HEX带透明度
                // 实时修改AP背景颜色
                anchorPane.setStyle("-fx-background-color: #" + newValue.toString().substring(2)); // 删去前两位

            }
        });
        // 日期选择器
        DatePicker dPicker = new DatePicker(LocalDate.now());
        dPicker.setEditable(true); // 能否编辑
        AnchorPane.setLeftAnchor(dPicker, 150.0);

        dPicker.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override // 设置选择动作
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                System.out.println(newValue.toString());
                // 可以用.getYear()等方法获得详细信息
            }
        });

        dPicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                DateCell cell = new DateCell(){
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

        pagination.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
            @Override // 获得页数
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(newValue.intValue()+1);
            }
        });

        pagination.setPageFactory(new Callback<Integer, Node>() {
            @Override // 设置页面内容
            public Node call(Integer param) { // param就是选择的页数
                return switch (param) { // 设置每一页内容
                    case 1 -> new Button("2");
                    case 2 -> new Button("3");
                    default -> new Button("?");
                };
            }
        });

        anchorPane.getChildren().addAll(cPicker, dPicker, pagination);
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.setTitle("第七天");
        primaryStage.setHeight(400);
        primaryStage.setWidth(400);
        primaryStage.show();

    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
//Written by Aloento.