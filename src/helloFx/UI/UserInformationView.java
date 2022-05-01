package helloFx.UI;

import java.util.Enumeration;

import helloFx.Beans.LocationBean;
import helloFx.Beans.UserBean;
import helloFx.Beans.UserInfoBean;
import helloFx.DaoImp.LocationDaoImp;
import helloFx.DaoImp.UserInfoDaoImp;
import helloFx.ServiceImp.UserInfoUpdateServiceImp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserInformationView {
    private Group root = new Group();
    private Scene scene = new Scene(root);
    private Stage primaryStage = new Stage();
    private String userLocation;
    private String userSex;
    private String actualName;
    private int userId = 0;

    public UserInformationView(UserBean uBean) {
        userId = uBean.getUser_id();
        UserInfoBean uInfoBean = new UserInfoDaoImp().selectInfoBean(uBean);
        primaryStage.setTitle("User Information");
        primaryStage.setScene(scene);
        primaryStage.setHeight(480);
        primaryStage.setWidth(360);
        primaryStage.setResizable(false);
        // 信息标签
        Label username = new Label("UserName: ");
        Label actualname = new Label("ActualName:");
        Label Sex = new Label("Sex:");
        Label location = new Label("Location: ");
        // 布局设置
        username.setLayoutX(20);
        username.setLayoutY(80);
        // 账户名称
        Text userText = new Text(uBean.getAccountname());
        userText.setLayoutX(120);
        userText.setLayoutY(95);
        // 真实姓名
        actualname.setLayoutX(20);
        actualname.setLayoutY(150);

        Sex.setLayoutX(20);
        Sex.setLayoutY(220);

        location.setLayoutX(20);
        location.setLayoutY(290);
        // Location
        LocationBean lBean = new LocationDaoImp().SelectcityByInfo();
        ChoiceBox<String> locationBox = new ChoiceBox<String>();
        Enumeration<String> cities = lBean.getCity().elements();
        while (cities.hasMoreElements()) {
            locationBox.getItems().add(cities.nextElement());
        }
        locationBox.setLayoutX(150);
        locationBox.setLayoutY(285);
        locationBox.setPrefWidth(160);
        locationBox.setOnAction(e -> {
            userLocation = (String) locationBox.getValue();
        });
        if (checkInfo(uBean)) {
            locationBox.getSelectionModel().select(uInfoBean.getLocation());
        }
        // 底部返回确认按钮
        Button confirmButton = new Button("Confirm");
        confirmButton.setLayoutX(80);
        confirmButton.setLayoutY(400);
        confirmButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(confirmSuccess(checkInfo(uBean))){
                primaryStage.close();
                new SearchView(uBean);
                }
            }

        });

        Button backButton = new Button("Back");
        backButton.setLayoutX(180);
        backButton.setLayoutY(400);

        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                new LoginView();
            }

        });
        // 真实姓名输入文本框
        TextField acTextField = new TextField();
        acTextField.setLayoutX(120);
        acTextField.setLayoutY(145);
        if (checkInfo(uBean)) {
            acTextField.setText(uInfoBean.getActualName());
        } else {
            System.out.println("true name is empty");
        }
        actualName = acTextField.getText();
        acTextField.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > 12)
                    acTextField.setText(oldValue);
                // actualName = newValue;

            }

        });
       
        // 男女单选按钮
        ToggleGroup tg = new ToggleGroup();
        RadioButton rb_menButton = new RadioButton("男");
        RadioButton rb_womenButton = new RadioButton("女");
        if (checkInfo(uBean)) {
            if (uInfoBean.getSex() == "男") {
                rb_menButton.setSelected(true);
            } else {
                rb_womenButton.setSelected(true);
            }
        }
        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                RadioButton res = (RadioButton) newValue;
                userSex = res.getText();
            }

        });

        rb_menButton.setLayoutX(120);
        rb_menButton.setLayoutY(220);
        rb_womenButton.setLayoutX(220);
        rb_womenButton.setLayoutY(220);
        rb_menButton.setToggleGroup(tg);
        rb_menButton.setSelected(true);
        rb_womenButton.setToggleGroup(tg);

        // 下拉栏

        root.getChildren().addAll(rb_menButton, rb_womenButton);
        root.getChildren().add(acTextField);
        root.getChildren().addAll(backButton, confirmButton);
        root.getChildren().addAll(username, actualname, Sex, location, locationBox, userText);
        primaryStage.show();
    }

    // 注册信息确认成功return true,存到数据库
    private boolean confirmSuccess(boolean user_flag) {
        // System.out.println(userId + userLocation + userSex + actualName);
        if (userLocation != null && userSex != null && userId != 0 && actualName != null) {
            if (user_flag == false) {
                UserInfoBean uInfoBean = new UserInfoBean(userLocation, userSex, actualName, userId);
                new UserInfoDaoImp().storageInfoBean(uInfoBean);
            } else if (user_flag == true) {
                new UserInfoUpdateServiceImp().updateInfoBy(userLocation, userSex, actualName, userId);
                // System.out.println("update...");
            }
            return true;
        } else {
            new WarnView("请正确填写信息");
            return false;
        }

    }

    // 判断该用户是否已填写过信息
    // 填写过返回为true,未填写返回为false
    private boolean checkInfo(UserBean uBean) {
        UserInfoBean checkInfoBean = new UserInfoDaoImp().selectInfoBean(uBean);
        if (checkInfoBean == null)
            return false;
        else
            return true;

    }
}
