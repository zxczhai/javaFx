package helloFx.Beans;

public class UserInfoBean {
    private String location;
    private String sex;
    private String actualName;
    private int userId;

    public UserInfoBean() {
    }

    public UserInfoBean(String location, String sex, String actualName) {
        this.location = location;
        this.sex = sex;
        this.actualName = actualName;
    }

    public int getUserId() {
        return userId;
    }

    public UserInfoBean(String location, String sex, String actualName, int userId) {
        this.location = location;
        this.sex = sex;
        this.actualName = actualName;
        this.userId = userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

}
