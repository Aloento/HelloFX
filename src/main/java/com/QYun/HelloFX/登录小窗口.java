package com.QYun.HelloFX;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class 登录小窗口 extends Application {

    @Override
    public void start(Stage primaryStage) {

        Label label_A = new Label("账号：");
        Label label_P = new Label("密码：");

        label_A.setStyle("-fx-font-size: 14; -fx-text-fill: white;");
        label_P.setStyle("-fx-font-size: 14; -fx-text-fill: white;");

        TextField textField_A = new TextField("输入账号与密码");
        PasswordField passwordField_P = new PasswordField();

        textField_A.setUserData("QA");
        textField_A.setTooltip(new Tooltip("请输入用户名"));
        passwordField_P.setUserData("Q-Audio");
        passwordField_P.setTooltip(new Tooltip("请输入密码"));

        Button button_Login = new Button("登录");
        Button button_clear = new Button("清除");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(20);
        GridPane.setMargin(button_Login, new Insets(0,0,0,120));
        gridPane.setStyle("-fx-background-image: url('QA.jpg');" +
                "-fx-background-size: cover");

        gridPane.add(label_A, 0, 0);
        gridPane.add(textField_A, 1, 0);
        gridPane.add(label_P, 0, 1);
        gridPane.add(passwordField_P, 1, 1);
        gridPane.add(button_clear, 0, 2);
        gridPane.add(button_Login, 1, 2);

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("登录小窗口");
        primaryStage.setHeight(400);
        primaryStage.setWidth(400);
        primaryStage.setResizable(false);
        primaryStage.show();

        button_clear.setOnAction(event -> {
            textField_A.setText("");
            passwordField_P.setText("");
        });

        button_Login.setOnAction(event -> {
            if (textField_A.getText().equals(textField_A.getUserData()) && passwordField_P.getText().equals(passwordField_P.getUserData()))
            {
                new Win_Success(textField_A.getText(), passwordField_P.getText());
            }
            else
            {
                primaryStage.setTitle("账号或密码错误");
                FadeTransition fadeTrans = new FadeTransition(Duration.millis(500), gridPane);
                fadeTrans.setFromValue(0);
                fadeTrans.setToValue(1);
                fadeTrans.play();
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }

}

class Win_Success
{
    public Win_Success(String A, String P)
    {
        DialogPane dialogPane = new DialogPane();
        dialogPane.setContentText("登录成功");
        dialogPane.setGraphic(new ImageView("Ubuntu.png"));
        dialogPane.setExpandableContent(new Text("账号：" + A + "      密码：" + P));
        dialogPane.getButtonTypes().add(ButtonType.OK);

        Scene scene = new Scene(dialogPane);
        Stage stage = new Stage();
        stage.setTitle("Q-Audio");
        stage.setScene(scene);
        stage.initStyle(StageStyle.UTILITY);
        stage.show();

        Button close = (Button) dialogPane.lookupButton(ButtonType.OK);
        close.setOnAction(event -> stage.close());
    }
}

//Written by Aloento.