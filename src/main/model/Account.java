package model;

public class Account {
    private String userName;
    private String password;
    private UserInfo info;

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public boolean isPasswordMatching(String password) {
        return this.password.equals(password);
    }
}
