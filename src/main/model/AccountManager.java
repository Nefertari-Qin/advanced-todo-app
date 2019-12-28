package model;

import model.exceptions.AccountAlreadyExisted;
import model.exceptions.AccountDoesntExist;

import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private static AccountManager theManager;
    private Map<String, Account> accounts;
    private Account signedIn;

    // Constructor: construct a account manager.
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
    // EFFECTS: if given userName is not globally unique, throw AccountAlreadyExisted exception;
    //          OW create a new account with given name and password,
    //             also sign into this newly created account.
    public void signUp(String userName, String password) throws AccountAlreadyExisted {
        if (accounts.containsKey(userName)) {
            throw new AccountAlreadyExisted();
        }
        signedIn = new Account(userName, password);
        accounts.put(userName, signedIn);
    }

    // MODIFIES: this
    // EFFECTS: if account with given userName doesn't exist, throw AccountDoesntExist exception;
    //          OW if password matches with the account, sign into account and return true;
    //             if password doesn't match, return false.
    public boolean signIn(String userName, String password) throws AccountDoesntExist {
        Account toSignIn = accounts.get(userName);
        if (toSignIn == null) {
            throw new AccountDoesntExist();
        }
        if (toSignIn.isPasswordMatching(password)) {
            signedIn = toSignIn;
            return true;
        }
        return false;
    }
}
