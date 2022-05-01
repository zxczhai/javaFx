package helloFx.UI;

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

public class WarnView {
    private Stage primaryStage = new Stage();

    private Group root = new Group();
    private Scene scene = new Scene(root);

    public WarnView(String msg) {
        primaryStage.setTitle("Error");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:Image/key.png"));
        primaryStage.setWidth(300);
        primaryStage.setHeight(180);
        primaryStage.setResizable(false);

        Label warn = new Label(msg);
        // warn.setAlignment(Pos.CENTER);
        warn.setLayoutX(60);
        warn.setLayoutY(40);
        warn.setFont(Font.font(20));
        // confirm Button
        Button Confirm_Button = new Button("Confirm");
        Confirm_Button.setLayoutX(100);
        Confirm_Button.setLayoutY(90);
        // Confirm_Button.setAlignment(Pos.CENTER);
        Confirm_Button.setFont(Font.font(14));
        BackgroundFill bgf = new BackgroundFill(Paint.valueOf("#B0C4DE"), new CornerRadii(15), new Insets(0));
        Background bg = new Background(bgf);
        Confirm_Button.setBackground(bg);
        // 鼠标单击事件
        // 退出当前警告窗口
        Confirm_Button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }

        });

        root.getChildren().add(Confirm_Button);
        root.getChildren().addAll(warn);
        primaryStage.show();

    }

}
