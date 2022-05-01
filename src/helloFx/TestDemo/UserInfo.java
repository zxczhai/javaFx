package helloFx.TestDemo;

import helloFx.Beans.UserBean;
import helloFx.Beans.UserInfoBean;
import helloFx.DaoImp.UserInfoDaoImp;

public class UserInfo {
    public static void main(String[] args) {
        // UserInfoBean uInfoBean = new UserInfoBean("Hangzhou", "男", "张三", 2);
        // System.out.println(new UserInfoDaoImp().storageInfoBean(uInfoBean));
        UserBean uBean = new UserBean();
        uBean.setUser_id(2);
        UserInfoBean uInfoBean = new UserInfoDaoImp().selectInfoBean(uBean);
        System.out.println(uInfoBean.getLocation());
    }
}
