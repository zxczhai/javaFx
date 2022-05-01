package helloFx.DaoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import helloFx.Beans.LocationBean;
import helloFx.DAO.LocationDao;
import helloFx.SQL.Appstu;

public class LocationDaoImp extends BaseDao implements LocationDao {

    @Override
    public LocationBean SelectcityByInfo() {
        String sql = "select city from location";
        this.con = Appstu.getConnection();
        LocationBean resultBean = null;
        Vector<String> cities = new Vector<String>();
        try {
            this.preparedStatement = this.con.prepareStatement(sql);
            ResultSet executeQuery = this.preparedStatement.executeQuery();
            while (executeQuery.next()) {
                cities.add(executeQuery.getString(1));
            }
            resultBean = new LocationBean(cities);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll();
        }
        return resultBean;
    }

}
