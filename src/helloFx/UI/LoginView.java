package helloFx.UI;

import helloFx.Beans.UserBean;
import helloFx.Service.UserService;
import helloFx.ServiceImp.UserServiceImp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginView {
    // 密码框
    private PasswordField pwd = new PasswordField();

    // 文本框
    private TextField text = new TextField();

    private Group root = new Group();
    private Scene scene = new Scene(root);
    private Stage primaryStage = new Stage();

    public LoginView() {
        root.setStyle("-fx-background-color:#008B8B");// error
        primaryStage.setScene(scene);
        primaryStage.setTitle("hello world");
        primaryStage.setWidth(400);
        primaryStage.setHeight(300);
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:Image\\order.png"));

        Label accountlLabel = new Label("Account: ");
        Label pwdLabel = new Label("Password: ");

        accountlLabel.setLayoutX(22);
        accountlLabel.setLayoutY(70);
        accountlLabel.setFont(Font.font(12));
        pwdLabel.setLayoutX(22);
        pwdLabel.setLayoutY(120);
        pwdLabel.setFont(Font.font(12));

        pwd.setLayoutX(120);
        pwd.setLayoutY(110);
        pwd.setFont(Font.font(14));
        pwd.setFocusTraversable(false);
        pwd.setPromptText("Enter password");

        // 单击事件,敲完密码自动自动登录
        pwd.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                LoginButton(event);
            }

        });

        // text.setText("这是文本");
        text.setLayoutX(120);
        text.setLayoutY(60);
        text.setFont(Font.font(14));
        text.setFocusTraversable(false);
        Tooltip tip = new Tooltip("zxc is a shuaibi");
        tip.setFont(Font.font(10));
        text.setTooltip(tip);

        text.setPromptText("Less than 12 char");
        // 限制输入文字长度为12位
        text.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > 12)
                    text.setText(oldValue);
            }

        });
        // 密码长度设置为8位
        pwd.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // TODO Auto-generated method stub
                if (newValue.length() > 8)
                    pwd.setText(oldValue);
            }

        });
        // 无数据库时测试
        // text.setUserData("zxczhai");
        // pwd.setUserData("12345789");

        // 添加按纽
        Button login = new Button("Login");
        Button clear = new Button("Clear");
        BackgroundFill bgf = new BackgroundFill(Paint.valueOf("#20B2AA"), new CornerRadii(15), new Insets(0));
        BackgroundFill bgf1 = new BackgroundFill(Paint.valueOf("#F08080"), new CornerRadii(15), new Insets(0));
        Background bg = new Background(bgf);
        Background bg1 = new Background(bgf1);
        login.setBackground(bg);
        login.setFont(Font.font(18));
        login.setLayoutX(210);
        login.setLayoutY(200);
        clear.setBackground(bg1);
        clear.setFont(Font.font(18));
        clear.setLayoutX(110);
        clear.setLayoutY(200);
        // 监听文字选择
        // text.selectedTextProperty().addListener(new ChangeListener<String>() {

        // @Override
        // public void changed(ObservableValue<? extends String> observable, String
        // oldValue, String newValue) {
        // // TODO Auto-generated method stub
        // System.out.println(newValue);
        // }

        // });

        root.getChildren().add(text);
        root.getChildren().add(pwd);
        root.getChildren().add(accountlLabel);
        root.getChildren().add(pwdLabel);
        root.getChildren().add(login);
        root.getChildren().add(clear);
        primaryStage.show();
        // 单击清除按钮,文本框置空
        clear.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                text.setText("");
                pwd.setText("");
            }

        });
        login.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                if (LoginButton(event) == 1) {
                    primaryStage.close();
                }
            }

        });
    }

    protected int LoginButton(ActionEvent e) {
        String Username = this.text.getText();
        String Password = this.pwd.getText();

        UserService userservice = new UserServiceImp();
        UserBean uBean = userservice.findUserInfo(Username, Password);
        if (uBean == null) {
            new WarnView("Check account!");
            return 0;
        } else
            new LoginSuccessView(uBean);
        return 1;

    }
}
