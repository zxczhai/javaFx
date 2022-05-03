/*
 * @Author: zxczhai
 * @Date: 2022-04-18 20:06:57
 * @LastEditors: zxczhai
 * @LastEditTime: 2022-05-03 19:58:59
 * @Description: 请填写简介
 */
package helloFx;

import helloFx.UI.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainClass extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new LoginView();
    }
}
