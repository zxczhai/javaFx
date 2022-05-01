package helloFx.Beans;

public class UserBean {
    private String Accountname;
    private String Password;
    private int User_id;

    public UserBean(String accountname, String password, int user_id) {
        Accountname = accountname;
        Password = password;
        User_id = user_id;
    }

    public UserBean(String accountname, String password) {
        Accountname = accountname;
        Password = password;
    }

    public String getAccountname() {
        return Accountname;
    }

    public void setAccountname(String accountname) {
        Accountname = accountname;
    }

    public UserBean() {
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getUser_id() {
        return User_id;
    }

    public void setUser_id(int user_id) {
        User_id = user_id;
    }

}
