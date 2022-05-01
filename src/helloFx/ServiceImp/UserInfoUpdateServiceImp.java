package helloFx.ServiceImp;

import helloFx.Beans.UserInfoBean;
import helloFx.DAO.UserInfoDao;
import helloFx.DaoImp.UserInfoDaoImp;
import helloFx.Service.UserInfoUpdateService;

public class UserInfoUpdateServiceImp implements UserInfoUpdateService{

    @Override
    public Boolean updateInfoBy(String location, String sex, String actualName, int userId) {
        UserInfoDao uInfoDao = new UserInfoDaoImp();
        return uInfoDao.updateInfoBean(new UserInfoBean(location, sex, actualName, userId));
    }
    
}
