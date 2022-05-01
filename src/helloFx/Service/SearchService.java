package helloFx.Service;

import java.util.Vector;

import helloFx.Beans.BookBeans;

public interface SearchService {
    public Vector<BookBeans> findBookInfo(String bookName, String sort);
    
}
