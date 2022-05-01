package helloFx.DAO;

import helloFx.Beans.UserBean;

public interface UserDao {
    UserBean selectUserByInfo(UserBean userBean);
}
