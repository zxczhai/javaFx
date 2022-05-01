package helloFx.DaoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import helloFx.Beans.SortsBean;
import helloFx.DAO.SortsDao;
import helloFx.SQL.Appstu;

public class SortsDaoImp extends BaseDao implements SortsDao {

    @Override
    public Vector<SortsBean> selectSortsByInfo() {
        String sql = "select distinct mainsort,subsort from booksort";
        this.con = Appstu.getConnection();
        SortsBean sBean = null;
        Vector<SortsBean> resBean = new Vector<SortsBean>();
        try {
            this.preparedStatement = this.con.prepareStatement(sql);
            ResultSet executeQuery = this.preparedStatement.executeQuery();
            while (executeQuery.next()) {
                sBean = new SortsBean(executeQuery.getString(1), executeQuery.getString(2));
                resBean.add(sBean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll();
        }
        return resBean;

    }

}
