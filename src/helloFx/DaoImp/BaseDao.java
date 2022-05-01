package helloFx.DaoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {
    protected Connection con = null;

    protected PreparedStatement preparedStatement = null;
    protected Statement state = null;

    protected void closeAll() {
        try {
            if (this.con != null) {
                this.con.close();
            }
            if (this.preparedStatement != null) {
                this.preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
