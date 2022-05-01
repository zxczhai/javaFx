package helloFx.DAO;

import java.util.Vector;

import helloFx.Beans.SortsBean;

public interface SortsDao {
    Vector<SortsBean> selectSortsByInfo();
}
