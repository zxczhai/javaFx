package helloFx.Beans;

import java.util.Vector;

public class LocationBean {
    // there is 687 cities in China
    private Vector<String> city = new Vector<String>(4);

    public LocationBean(Vector<String> city) {
        this.city = city;
    }

    public void setCity(Vector<String> city) {
        this.city = city;
    }

    public Vector<String> getCity() {
        return city;
    }

    public LocationBean() {
    }

}
