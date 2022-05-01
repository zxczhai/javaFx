package helloFx.ServiceImp;

import helloFx.Beans.UserBean;
import helloFx.DAO.UserDao;
import helloFx.DaoImp.UserDaoImp;
import helloFx.Service.UserService;

public class UserServiceImp implements UserService {

    @Override
    public UserBean findUserInfo(String Username, String Password) {
        UserDao userRes = new UserDaoImp();
        return userRes.selectUserByInfo(new UserBean(Username, Password));
    }

}
