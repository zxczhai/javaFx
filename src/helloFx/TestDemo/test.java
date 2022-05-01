package helloFx.TestDemo;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class test extends Application {

    private HBox bottomControls;
    private ProgressBar pb;
    private Label messageLabel;

    private TextField tfURL;

    private TextArea contents;

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent p = createMainView();

        Scene scene = new Scene(p);

        primaryStage.setTitle("ProgressBarApp");
        primaryStage.setWidth(667);
        primaryStage.setHeight(376);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Parent createMainView() {

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        HBox topControls = new HBox();
        topControls.setAlignment(Pos.CENTER_LEFT);
        topControls.setSpacing(4);

        Label label = new Label("URL");
        tfURL = new TextField();
        HBox.setHgrow(tfURL, Priority.ALWAYS);
        Button btnGetHTML = new Button("Get HTML");
        btnGetHTML.setOnAction(this::getHTML);
        topControls.getChildren().addAll(label, tfURL, btnGetHTML);

        contents = new TextArea();
        VBox.setVgrow(contents, Priority.ALWAYS);

        bottomControls = new HBox();
        bottomControls.setVisible(false);
        bottomControls.setSpacing(4);
        HBox.setMargin(bottomControls, new Insets(4));

        pb = new ProgressBar();
        messageLabel = new Label("");
        bottomControls.getChildren().addAll(pb, messageLabel);

        vbox.getChildren().addAll(topControls, contents, bottomControls);

        return vbox;
    }

    public void getHTML(ActionEvent evt) {

        String url = tfURL.getText();

        Task<String> task = new Task<String>() {

            @Override
            protected String call() throws Exception {

                updateMessage("Getting HTML from " + url);
                updateProgress(0.5d, 1.0d);

                HttpURLConnection c = null;
                InputStream is = null;
                String retval = "";

                try {

                    c = (HttpURLConnection) new URL(url).openConnection();

                    updateProgress(0.6d, 1.0d);
                    is = c.getInputStream();
                    int ch;
                    while ((ch = is.read()) != -1) {
                        retval += (char) ch;
                    }

                } finally {
                    if (is != null) {
                        is.close();
                    }
                    if (c != null) {
                        c.disconnect();
                    }
                }

                updateMessage("HTML retrieved");
                updateProgress(1.0d, 1.0d);

                return retval;
            }

            @Override
            protected void succeeded() {
                contents.setText(getValue());
            }

            @Override
            protected void failed() {
                Alert alert = new Alert(Alert.AlertType.ERROR, getException().getMessage());
                alert.showAndWait();
            }
        };

        bottomControls.visibleProperty().bind(task.runningProperty());
        pb.progressProperty().bind(task.progressProperty());
        messageLabel.textProperty().bind(task.messageProperty());

        new Thread(task).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}