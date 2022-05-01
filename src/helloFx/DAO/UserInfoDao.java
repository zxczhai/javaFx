package helloFx.DAO;

import helloFx.Beans.UserBean;
import helloFx.Beans.UserInfoBean;

public interface UserInfoDao {
    UserInfoBean selectInfoBean(UserBean uBean);

    Boolean storageInfoBean(UserInfoBean uInfoBean);

    Boolean updateInfoBean(UserInfoBean uInfoBean);
}
