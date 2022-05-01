package helloFx.DaoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import helloFx.Beans.UserBean;
import helloFx.DAO.UserDao;
import helloFx.SQL.Appstu;

public class UserDaoImp extends BaseDao implements UserDao {

    @Override
    public UserBean selectUserByInfo(UserBean userBean) {
        String sql = "select username,userpwd,userid from usermanager where username = ? and userpwd= ?";
        this.con = Appstu.getConnection();
        UserBean resultUser = null;
        try {
            this.preparedStatement = this.con.prepareStatement(sql);
            this.preparedStatement.setString(1, userBean.getAccountname());
            this.preparedStatement.setString(2, userBean.getPassword());
            ResultSet executeQuery = this.preparedStatement.executeQuery();
            if (executeQuery.next()) {
                resultUser = new UserBean(executeQuery.getString(1), executeQuery.getString(2), executeQuery.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll();
        }
        return resultUser;
    }

}
