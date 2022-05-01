package helloFx.DAO;

import java.util.Vector;

import helloFx.Beans.BookBeans;

public interface BookDao {
    Vector<BookBeans> selectbookByInfo();
    Vector<BookBeans> selectbookByInfo(BookBeans bookBeans);
}
