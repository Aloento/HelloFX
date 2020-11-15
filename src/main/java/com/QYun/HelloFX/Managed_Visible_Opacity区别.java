package com.QYun.HelloFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Managed_Visible_Opacity区别 extends Application {
    static boolean isManaged = false;
    static boolean isVisible = false;
    static double value_Opacity = 0;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Button B1 = new Button("Button 1");
        Button B2 = new Button("Button 2");
        Button B3 = new Button("Button 3");
        Button B4 = new Button("Button 4");

        Button Bc1 = new Button("B3.setManaged(false)");
        Button Bc2 = new Button("B3.setVisible(false)");
        Button Bc3 = new Button("B3.setOpacity(0)");

        // B3.setManaged(false);
        // B3.setVisible(false);
        // B3.setOpacity(0);

        AnchorPane anchorPane_1 = new AnchorPane();
        anchorPane_1.setStyle("-fx-background-color:#FFFFFF;");
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(20));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(B1, B2, B3, B4);

        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().addAll(Bc1, Bc2, Bc3);

        AnchorPane.setTopAnchor(vBox, 100.0);
        AnchorPane.setLeftAnchor(vBox, 20.0);
        anchorPane_1.getChildren().addAll(vBox, hBox);

        primaryStage.setScene(new Scene(anchorPane_1));
        primaryStage.setHeight(300);
        primaryStage.setWidth(400);
        primaryStage.setTitle("Managed / Visible / Opacity 的区别");
        primaryStage.show();

        Bc1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                B3.setManaged(isManaged);
                new Print(hBox);
                if (isManaged == true)
                {
                    isManaged = false;
                    Bc1.setText("B3.setManaged(" + isManaged + ")");
                }
                else
                {
                    isManaged = true;
                    Bc1.setText("B3.setManaged(" + isManaged + ")");
                }
            }
        });

        Bc2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                B3.setVisible(isVisible);
                new Print(hBox);
                if (isVisible == true)
                {
                    isVisible = false;
                    Bc2.setText("B3.setVisible(" + isVisible + ")");
                }
                else
                {
                    isVisible = true;
                    Bc2.setText("B3.setVisible(" + isVisible + ")");
                }
            }
        });

        Bc3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                B3.setOpacity(value_Opacity);
                new Print(hBox);
                if (value_Opacity == 0)
                {
                    value_Opacity = 1;
                    Bc3.setText("B3.setOpacity(" + value_Opacity + ")");
                }
                else
                {
                    value_Opacity = 0;
                    Bc3.setText("B3.setOpacity(" + value_Opacity + ")");
                }
            }
        });

    }
    
    public static void main(String[] args) {
        launch();
    }

}

class Print {
    Print (HBox hBox) {
        System.out.println("组件数量：" + hBox.getChildren().size());
    }
}

//Written by Aloento.