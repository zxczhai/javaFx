package helloFx.DaoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import helloFx.Beans.UserBean;
import helloFx.Beans.UserInfoBean;
import helloFx.DAO.UserInfoDao;
import helloFx.SQL.Appstu;

public class UserInfoDaoImp extends BaseDao implements UserInfoDao {

    @Override
    public UserInfoBean selectInfoBean(UserBean uBean) {
        String sql = "select location,sex,actualname from userinfo where userid = ?";
        this.con = Appstu.getConnection();
        UserInfoBean resultBean = null;
        try {
            this.preparedStatement = this.con.prepareStatement(sql);
            this.preparedStatement.setString(1, String.valueOf(uBean.getUser_id()));
            ResultSet executeQuery = this.preparedStatement.executeQuery();
            if (executeQuery.next()) {
                resultBean = new UserInfoBean(executeQuery.getString(1), executeQuery.getString(2),
                        executeQuery.getString(3), uBean.getUser_id());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll();
        }
        return resultBean;
    }

    @Override
    public Boolean storageInfoBean(UserInfoBean uInfoBean) {
        String sql = "insert into userInfo(location,sex,actualname,userId) values (\"" + uInfoBean.getLocation() + "\","
                + "\"" + uInfoBean.getSex() + "\","
                + "\"" + uInfoBean.getActualName() + "\","
                + "\"" + uInfoBean.getUserId() + "\");";
        this.con = Appstu.getConnection();
        try {
            this.state = this.con.createStatement();
            state.executeUpdate(sql);
            // System.out.println(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            this.closeAll();
        }
        return true;
    }

    @Override
    public Boolean updateInfoBean(UserInfoBean uInfoBean) {
        String sql = "update userinfo set location = ? ,sex =?,actualname =? where userid = ?";
        this.con = Appstu.getConnection();
        try {
            this.preparedStatement = this.con.prepareStatement(sql);
            this.preparedStatement.setString(1, uInfoBean.getLocation());
            this.preparedStatement.setString(2, uInfoBean.getSex());
            this.preparedStatement.setString(3, uInfoBean.getActualName());
            this.preparedStatement.setString(4, String.valueOf(uInfoBean.getUserId()));
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            this.closeAll();
        }
        return true;
    }

}
