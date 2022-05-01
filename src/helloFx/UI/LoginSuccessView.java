package helloFx.UI;

import helloFx.Beans.UserBean;
import helloFx.Beans.UserInfoBean;
import helloFx.DaoImp.UserInfoDaoImp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginSuccessView {

    private Stage primaryStage = new Stage();
    private Group root = new Group();
    private Scene scene = new Scene(root);

    public LoginSuccessView(UserBean uBean) {

        primaryStage.setTitle("Welcome: " + uBean.getAccountname());

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:Image/key.png"));
        primaryStage.setWidth(420);
        primaryStage.setHeight(180);
        primaryStage.setResizable(false);
        Button Confirm_Button = new Button("Confirm");

        Confirm_Button.setLayoutX(190);
        Confirm_Button.setLayoutY(90);
        Confirm_Button.setFont(Font.font(14));

        Label warn = new Label("登录成功!!");
        // warn.setAlignment(Pos.CENTER);
        warn.setLayoutX(95);
        warn.setLayoutY(40);
        warn.setFont(Font.font(20));

        BackgroundFill bgf = new BackgroundFill(Paint.valueOf("#B0C4DE"), new CornerRadii(15), new Insets(0));
        Background bg = new Background(bgf);
        Confirm_Button.setBackground(bg);
        // 鼠标单击事件
        // 用户信息界面
        Confirm_Button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                new UserInformationView(uBean);
                primaryStage.close();
                if (!checkInfo(uBean)) {
                    new WarnView("欢迎老朋友");
                }
            }

        });

        root.getChildren().add(Confirm_Button);
        root.getChildren().add(warn);

        primaryStage.show();

    }

    // 判断该用户是否已填写过信息
    public boolean checkInfo(UserBean uBean) {
        UserInfoBean checkInfoBean = new UserInfoDaoImp().selectInfoBean(uBean);
        if (checkInfoBean == null)
            return true;
        else
            return false;

    }
}
