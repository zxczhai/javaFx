package helloFx.ServiceImp;

import java.util.Vector;

import helloFx.Beans.BookBeans;
import helloFx.DAO.BookDao;
import helloFx.DaoImp.BookDaoImp;
import helloFx.Service.SearchService;

public class SearchServiceImp implements SearchService{

    @Override
    public Vector<BookBeans> findBookInfo(String bookName, String sort) {
        BookDao bookDao = new BookDaoImp();
        return bookDao.selectbookByInfo(new BookBeans(bookName, sort));
    }
    
}
