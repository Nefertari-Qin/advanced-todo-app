package model;

import model.exceptions.AccountSameName;
import model.exceptions.AccountSamePassword;
import model.exceptions.NoSuchAccount;
import model.exceptions.OutsideAccount;

import java.util.HashMap;
import java.util.Map;

// Represent a account manager in a application with arb #of accounts.
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
    // EFFECTS: if given userName is not globally unique, throw AccountSameName exception;
    //          OW create a new account with given name and password,
    //             also sign into this newly created account.
    public void signUp(String userName, String password) throws AccountSameName {
        if (accounts.containsKey(userName)) {
            throw new AccountSameName();
        }

        signedIn = new Account(userName, password);
        accounts.put(userName, signedIn);
    }

    // TODO: aad load() feature later
    // MODIFIES: this
    // EFFECTS: if account with given userName doesn't exist, throw NoSuchAccount exception;
    //          OW if password matches with the account, sign into account and return true;
    //             if password doesn't match, return false.
    public boolean signIn(String userName, String password) throws NoSuchAccount {
        Account toSignIn = accounts.get(userName);
        if (toSignIn == null) {
            throw new NoSuchAccount();
        } else if (toSignIn.isPasswordMatching(password)) {
            signedIn = toSignIn;
            return true;
        }
        return false;
    }

    // TODO: add save() feature later
    // MODIFIES: this
    // EFFECTS: if account is already signed out, throw OutsideAccount exception;
    //          OW sign out current account (i.e. no account is signed in).
    public void signOut() throws OutsideAccount {
        if (signedIn == null) {
            throw new OutsideAccount();
        }
        signedIn = null;
    }

    // MODIFIES: this
    // EFFECTS: if haven't signed into any account, throw OutsideAccount exception;
    //          OW if given userName is the same as previous userName, throw AccountSameName exception;
    //             OW if password matches with the account, change the userName to what's given and return true;
    //                if password doesn't match, return false.
    public boolean changeUserName(String userName, String password) throws OutsideAccount, AccountSameName {
        if (signedIn == null) {
            throw new OutsideAccount();
        } else if (userName.equals(signedIn.getUserName())) {
            throw new AccountSameName();
        } else if (signedIn.isPasswordMatching(password)) {
            accounts.remove(signedIn.getUserName(), signedIn);
            signedIn.setUserName(userName);
            accounts.put(signedIn.getUserName(), signedIn);

            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: if haven't signed into any account, throw OutsideAccount exception;
    //          OW if prev password is the same as crnt password, throw AccountSamePassword exception;
    //             OW if password matches with the account, change prev password to crnt password and return true;
    //                if password doesn't match, return false.
    public boolean changePassword(String prev, String crnt) throws OutsideAccount, AccountSamePassword {
        if (signedIn == null) {
            throw new OutsideAccount();
        } else if (prev.equals(crnt)) {
            throw new AccountSamePassword();
        } else if (signedIn.isPasswordMatching(prev)) {
            signedIn.setPassword(crnt);

            return true;
        }
        return false;
    }
}
