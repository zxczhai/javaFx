package helloFx.DaoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import helloFx.Beans.BookBeans;
import helloFx.DAO.BookDao;
import helloFx.SQL.Appstu;

public class BookDaoImp extends BaseDao implements BookDao {

    @Override
    public Vector<BookBeans> selectbookByInfo() {
        String sql = "select bookinfo.bookId,bookName,bookAuthor,bookNum,booksort.mainsort,booksort.subsort from bookInfo,booksort where bookinfo.bookid=booksort.bookid";
        this.con = Appstu.getConnection();
        BookBeans bookBeans = null;
        Vector<BookBeans> resBean = new Vector<BookBeans>();
        try {
            this.preparedStatement = this.con.prepareStatement(sql);
            ResultSet executeQuery = this.preparedStatement.executeQuery();
            while (executeQuery.next()) {
                bookBeans = new BookBeans(executeQuery.getString(1), executeQuery.getString(2),
                        executeQuery.getString(3),
                        executeQuery.getInt(4), executeQuery.getString(5), executeQuery.getString(6));
                resBean.add(bookBeans);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll();
        }
        return resBean;
    }

    @Override
    public Vector<BookBeans> selectbookByInfo(BookBeans boBeans) {
        String sql = null;
        int selectFlag = 0;
        if (boBeans.getBook_Name() == null && boBeans.getBook_Subsort() == null) {
            sql = "select bookinfo.bookId,bookName,bookAuthor,bookNum,booksort.mainsort,booksort.subsort from bookInfo,booksort where bookinfo.bookid=booksort.bookid";
            selectFlag = 1;
        } else if (boBeans.getBook_Name() == null && boBeans.getBook_Subsort() != null) {
            sql = "select bookinfo.bookId,bookName,bookAuthor,bookNum,booksort.mainsort,booksort.subsort from bookInfo,booksort where bookinfo.bookid=booksort.bookid and subsort = ?";
            selectFlag = 2;
        } else if (boBeans.getBook_Name() != null && boBeans.getBook_Subsort() == null) {
            sql = "select bookinfo.bookId,bookName,bookAuthor,bookNum,booksort.mainsort,booksort.subsort from bookInfo,booksort where bookinfo.bookid=booksort.bookid and bookName like ?";
            selectFlag = 3;
        } else if (boBeans.getBook_Name() != null && boBeans.getBook_Subsort() != null) {
            sql = "select bookinfo.bookId,bookName,bookAuthor,bookNum,booksort.mainsort,booksort.subsort from bookInfo,booksort where bookinfo.bookid=booksort.bookid and bookName like ? and subsort = ?";
            selectFlag = 4;
        }
        this.con = Appstu.getConnection();
        BookBeans bookBeans = null;
        Vector<BookBeans> resBean = new Vector<BookBeans>();
        try {
            this.preparedStatement = this.con.prepareStatement(sql);
            switch (selectFlag) {
                case 1:
                    break;
                case 2:
                    this.preparedStatement.setString(1, boBeans.getBook_Subsort());
                    break;
                case 3:
                    this.preparedStatement.setString(1, "%"+boBeans.getBook_Name()+"%");
                    break;
                case 4:
                    this.preparedStatement.setString(1, "%"+boBeans.getBook_Name()+"%");
                    this.preparedStatement.setString(2, boBeans.getBook_Subsort());
                    break;
            }
            ResultSet executeQuery = this.preparedStatement.executeQuery();
            while (executeQuery.next()) {
                bookBeans = new BookBeans(executeQuery.getString(1), executeQuery.getString(2),
                        executeQuery.getString(3),
                        executeQuery.getInt(4), executeQuery.getString(5), executeQuery.getString(6));
                resBean.add(bookBeans);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll();
        }
        return resBean;
    }

}
