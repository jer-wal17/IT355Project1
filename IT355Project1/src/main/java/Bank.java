package main.java;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.SerializablePermission;

public class Bank implements Serializable{
    private static final long serialVersionUID = 1L;
    private long uniqueId;
    private String name;
    private BankAccount[] accountList;
    private long openedAccounts = 0;/* using this to generate a unique ID for accounts */

    /**
     * Constructor for a bank object
     * 
     * @param uniqueId unique identifier for the bank
     * @param name     name of the bank
     */
    public Bank(long uniqueId, String name) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.accountList = new BankAccount[10];/*
                                                * just have this at 10 for now, we can change how this works at any
                                                * point lol
                                                */
    }

    /**
     * Copy constructor for a bank object
     * 
     * @param bank a passed in bank object
     */
    public Bank(Bank bank) {
        this.uniqueId = bank.getUniqueId();
        this.name = bank.getName();
        this.accountList = bank.getAccountList();
        this.openedAccounts = bank.getOpenedAccounts();
    }

    /**
     * opens an account in the bank if ones available using a User's id and a given
     * balance
     * 
     * @param userId  user id that will be set to the owner of the bank account
     * @param balance starting balance of the account
     */
    public BankAccount openAccount(int userId, double balance) {
        /* find a open space and open account there */
        BankAccount openedAccount = null;
        for (int i = 0; i < accountList.length; i++) {
            if (accountList[i] == null) {
                openedAccount = new CheckingBankAccount(openedAccounts++, uniqueId, userId, balance);
                accountList[i] = openedAccount;
                break;
            }
        }
        return openedAccount;
    }

    /**
     * Method that looks for an open account that matches the userId and accountId
     * given, and closes it if so.
     * 
     * @param userId    id of the owner of the account
     * @param accountId unique id of the account
     */
    public void closeAccount(int userId, int accountId) {
        for (int i = 0; i < accountList.length; i++) {
            if (accountList[i] != null) {
                /* check if owner and account id are the same */
                if (accountList[i].getOwnerId() == userId && accountList[i].getUniqueId() == accountId) {
                    accountList[i] = null;
                    break;
                }
            }
        }
    }

    /**
     * Method that returns a copy of the account with the given userid and accountId
     * 
     * @param userId    user id of the owner of the account
     * @param accountId unique id of the account
     * @return bank account with passed in userid and accountid or null
     */
    public BankAccount getAccount(int userId, int accountId) {
        for (int i = 0; i < accountList.length; i++) {
            if (accountList[i] != null) {
                /* check if owner and account id are the same */
                if (accountList[i].getOwnerId() == userId && accountList[i].getUniqueId() == accountId) {
                    BankAccount returnBankAccount = new CheckingBankAccount(accountList[i].getUniqueId(),
                            accountList[i].getBankId(), accountList[i].getOwnerId(), accountList[i].getBalance());
                    return returnBankAccount;
                }
            }
        }
        return null;
    }
    /**
     * Method that updates the AccountList with a given account, userid, and account id of a valid account
     * @param userId user id of the owner of the account
     * @param accountId account id of the account your looking to update
     * @param updatedAccount given BankAccount object to update the accountlist with
     */
    public void updateAccount(long userId, long accountId, BankAccount updatedAccount) {
        for (int i = 0; i < accountList.length; i++) {
            if (accountList[i] != null) {
                /* check if owner and account id are the same */
                if (accountList[i].getOwnerId() == userId && accountList[i].getUniqueId() == accountId) {
                    if (accountList[i].getClass() == updatedAccount.getClass()){
                        accountList[i] = updatedAccount;
                    }
                }
            }
        }
    }

    /** getters and setters that are needed */
    public final long getUniqueId() {
        return uniqueId;
    }

    public final String getName() {
        return name;
    }

    public final long getOpenedAccounts() {
        return openedAccounts;
    }

    /**
     * Method to return a deep copy of the Bank Account array
     * 
     * @return a deep copy of the accountList
     */
    public final BankAccount[] getAccountList() {
        BankAccount[] copiedList = new BankAccount[accountList.length];
        for (int i = 0; i < accountList.length; i++) {
            if (accountList[i] != null) {
                copiedList[i] = new CheckingBankAccount(accountList[i].getUniqueId(), accountList[i].getBankId(), // MET52-J.
                        accountList[i].getOwnerId(), accountList[i].getBalance());
            }
        }
        return copiedList;
    }

    public void printAccounts() {
        for (int i = 0; i < openedAccounts; i++) {
            System.out.print(accountList[i]);
        }
    }

    /**
     * Custom serialization method.
     * This method checks for the "serialize" permission using the SecurityManager.
     *
     * @param out The ObjectOutputStream to write the object to.
     * @throws IOException If an I/O error occurs during serialization.
     */
    @SuppressWarnings("removal")
    private void writeObject(ObjectOutputStream out) throws IOException {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new SerializablePermission("serialize"));
        }
        out.defaultWriteObject();
    }

    /**
     * Custom deserialization method.
     * This method checks for the "deserialize" permission using the SecurityManager.
     *
     * @param in The ObjectInputStream to read the object from.
     * @throws IOException            If an I/O error occurs during deserialization.
     * @throws ClassNotFoundException If the class of the serialized object cannot be found.
     */
    @SuppressWarnings("removal")
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new SerializablePermission("deserialize"));
        }
        in.defaultReadObject();
    }
}
