package helloFx.Service;

import helloFx.Beans.UserBean;

public interface UserService {
    public UserBean findUserInfo(String username, String Password);
}
