package model;

import model.exceptions.AccountAlreadyExisted;

import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private static AccountManager theManager;
    private Map<String, Account> accounts;
    private Account signedIn;

    // Constructor: construct a account manager
    // Singleton Design Pattern: Prevent external construction.
    private AccountManager() {
        accounts = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: create a instance of AccountManager if it doesn't already exist.
    public static AccountManager getInstance() {
        if (theManager == null) {
            theManager = new AccountManager();
        }
        return theManager;
    }

    // MODIFIES: this
    // EFFECTS: throw AccountAlreadyExisted exception if given userName is not globally unique
    //          OW create a new account with given name and password,
    //             also sign in this newly created account
    public void signUp(String userName, String password) throws AccountAlreadyExisted {
        if (accounts.containsKey(userName)) {
            throw new AccountAlreadyExisted();
        }
        signedIn = new Account(userName, password);
        accounts.put(userName, signedIn);
    }

    public void signIn(String userName, String password) {
        if (!accounts.containsKey(userName)) {

        }
    }
}
