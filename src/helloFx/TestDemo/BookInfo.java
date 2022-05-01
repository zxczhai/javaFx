package helloFx.TestDemo;

import helloFx.Beans.UserBean;
import helloFx.UI.SearchView;

public class BookInfo {
    public static void main(String[] args) {
        UserBean uBean = new UserBean();
        uBean.setAccountname("root");
        uBean.setPassword("123");
        uBean.setUser_id(2);
        new SearchView(uBean);
    }
}
