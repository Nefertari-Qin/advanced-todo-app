package model;

public class Account {
    private String userName;
    private String password;
    private UserInfo info;

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    // Getters and Setters:
    public String getUserName() {
        return userName;
    }
    // TODO: May not need this getter.
    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // EFFECTS: if given password matches user password, return true;
    //          OW return false.
    public boolean isPasswordMatching(String password) {
        return password.equals(this.password);
    }
}
