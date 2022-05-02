package helloFx.UI;

import java.util.Enumeration;
import java.util.Vector;

import helloFx.Beans.BookBeans;
import helloFx.Beans.SortsBean;
import helloFx.Beans.UserBean;
import helloFx.Beans.UserInfoBean;
import helloFx.DaoImp.BookDaoImp;
import helloFx.DaoImp.SortsDaoImp;
import helloFx.DaoImp.UserInfoDaoImp;
import helloFx.ServiceImp.SearchServiceImp;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Callback;

public class SearchView {
    private Group root = new Group();
    private Scene scene = new Scene(root);
    private Stage primaryStage = new Stage();

    // 传入ubean用户参数
    public SearchView(UserBean uBean) {
        UserInfoBean uInfoBean = new UserInfoDaoImp().selectInfoBean(uBean);
        primaryStage.setTitle("SearchPage");
        primaryStage.setScene(scene);
        primaryStage.setWidth(720);
        primaryStage.setHeight(640);

        // 效果:您好,张三 //来自: 桑海
        // @code + uBean.getAccountName();
        Label userLabel = new Label("Welcome,  " + uInfoBean.getActualName());
        Label userLocationLabel = new Label("from:   " + uInfoBean.getLocation());
        userLabel.setLayoutX(20);
        userLabel.setLayoutY(20);
        userLocationLabel.setLayoutX(580);
        userLocationLabel.setLayoutY(20);

        // 书籍名查询组件
        Label bookNameLabel = new Label("BookName: ");
        bookNameLabel.setLayoutX(20);
        bookNameLabel.setLayoutY(100);
        TextField bookName = new TextField();
        bookName.setText(null);
        // 限制输入书名限制长度为8位
        bookName.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > 8)
                    bookName.setText(oldValue);
            }

        });
        bookName.setPromptText("Enter to Search...");
        bookName.setLayoutX(120);
        bookName.setLayoutY(95);
        // 分类查询组件
        Label sortLabel = new Label("Sort: ");
        sortLabel.setLayoutX(350);
        sortLabel.setLayoutY(100);
        // 分类选择ChoiceBos
        Vector<SortsBean> rBeans = new SortsDaoImp().selectSortsByInfo();
        ChoiceBox<String> sortBox = new ChoiceBox<String>();
        Enumeration<SortsBean> rSort = rBeans.elements();

        while (rSort.hasMoreElements()) {
            sortBox.getItems().add(rSort.nextElement().getSubSort());
        }
        sortBox.getItems().add(null);
        // sortBox.getSelectionModel().select("");
        sortBox.setLayoutX(400);
        sortBox.setLayoutY(95);
        sortBox.setPrefWidth(130);
        // sortBox.setOnAction(e -> {
        // System.out.println(sortBox.getValue());
        // });

        // 查询和购买按钮组件
        Button searchbButton = new Button("Search");
        Button parchasebButton = new Button("Parchase");

        BackgroundFill bgf = new BackgroundFill(Paint.valueOf("#20B2AA"), new CornerRadii(15), new Insets(0));
        BackgroundFill bgf1 = new BackgroundFill(Paint.valueOf("#F08080"), new CornerRadii(15), new Insets(0));
        Background bg = new Background(bgf);
        Background bg1 = new Background(bgf1);
        searchbButton.setBackground(bg);
        searchbButton.setLayoutX(600);
        // 搜索功能
        searchbButton.setOnAction(e -> {
            String searchName = bookName.getText();
            String sort = sortBox.getValue();
            root.getChildren().add(selectView(goodsSearchByInfo(searchName, sort)));
        });

        searchbButton.setLayoutY(95);
        parchasebButton.setBackground(bg1);
        parchasebButton.setLayoutX(590);
        parchasebButton.setLayoutY(510);

        root.getChildren().addAll(userLabel, userLocationLabel);
        root.getChildren().addAll(searchbButton, parchasebButton);
        root.getChildren().addAll(bookName, bookNameLabel);
        root.getChildren().addAll(sortLabel, sortBox);
        primaryStage.show();

    }

    // 商品信息列表
    ObservableList<BookBeans> goodsSearchByInfo(String bookName, String booksort) {
        Vector<BookBeans> bookBeans = null;
        // System.out.println(booksort);
        // System.out.println(bookName);
        if (bookName == null && booksort == null) {
            // return Vector<BookBeans>
            bookBeans = new BookDaoImp().selectbookByInfo();
        } else {
            bookBeans = new SearchServiceImp().findBookInfo(bookName, booksort);
        }

        Enumeration<BookBeans> eBookBeans = bookBeans.elements();
        ObservableList<BookBeans> bookList = FXCollections.observableArrayList();
        while (eBookBeans.hasMoreElements()) {
            bookList.add(eBookBeans.nextElement());
        }
        if (bookList.size() == 0) {
            new WarnView("goodsList is empty!");
            return null;
        }
        return bookList;
    }

    /* 商品具体信息显示部分 */
    TableView<BookBeans> selectView(ObservableList<BookBeans> bookList) {
        // TableView
        TableView<BookBeans> bookInfoView = new TableView<BookBeans>(bookList);
        bookInfoView.setLayoutX(10);
        bookInfoView.setLayoutY(140);
        bookInfoView.setPrefSize(680, 360);
        // TableColumn 信息列
        TableColumn<BookBeans, String> tc_bookId = new TableColumn<BookBeans, String>("BookID");
        tc_bookId.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<BookBeans, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<BookBeans, String> param) {
                        SimpleStringProperty id = new SimpleStringProperty(param.getValue().getBook_Id());
                        return id;
                    }

                });
        TableColumn<BookBeans, String> tc_bookName = new TableColumn<BookBeans, String>("BookName");
        tc_bookName.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<BookBeans, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<BookBeans, String> param) {
                        SimpleStringProperty name = new SimpleStringProperty(param.getValue().getBook_Name());
                        return name;
                    }

                });
        TableColumn<BookBeans, String> tc_bookAuthor = new TableColumn<BookBeans, String>("BookAuthor");
        tc_bookAuthor.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<BookBeans, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<BookBeans, String> param) {
                        SimpleStringProperty author = new SimpleStringProperty(param.getValue().getBook_Author());
                        return author;
                    }

                });
        TableColumn<BookBeans, Number> tc_bookNum = new TableColumn<BookBeans, Number>("BookNum");
        tc_bookNum.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<BookBeans, Number>, ObservableValue<Number>>() {

                    @Override
                    public ObservableValue<Number> call(CellDataFeatures<BookBeans, Number> param) {
                        SimpleIntegerProperty num = new SimpleIntegerProperty(param.getValue().getBook_Num());
                        return num;
                    }

                });
        TableColumn<BookBeans, String> tc_mainSort = new TableColumn<BookBeans, String>("MainSort");
        tc_mainSort.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<BookBeans, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<BookBeans, String> param) {
                        SimpleStringProperty main = new SimpleStringProperty(param.getValue().getBook_Mainsort());
                        return main;
                    }

                });
        TableColumn<BookBeans, String> tc_subSort = new TableColumn<BookBeans, String>("SubSort");
        tc_subSort.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<BookBeans, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<BookBeans, String> param) {
                        SimpleStringProperty sub = new SimpleStringProperty(param.getValue().getBook_Subsort());
                        return sub;
                    }

                });
        bookInfoView.getColumns().add(tc_bookId);
        bookInfoView.getColumns().add(tc_bookName);
        bookInfoView.getColumns().add(tc_bookAuthor);
        bookInfoView.getColumns().add(tc_bookNum);
        bookInfoView.getColumns().add(tc_mainSort);
        bookInfoView.getColumns().add(tc_subSort);
        /************** END... ***************/
        return bookInfoView;
    }
}
