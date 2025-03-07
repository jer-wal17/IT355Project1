package main.java;

import java.text.DecimalFormat;

class BankAccount {
    private long uniqueId;
    private long bankId;
    private long ownerId;
    private double balance;

    /**
     * Constructor class
     * 
     * @param bankID  bank account id
     * @param ownerID owner account id
     * @param balance starting balance for the account
     */
    public BankAccount(long uniqueId, long bankId, long ownerId, double balance) {
        this.uniqueId = uniqueId;
        this.bankId = bankId;
        this.ownerId = ownerId;
        this.balance = balance;
    }

    /**
     * Copy constructor for a bank account object
     * 
     * @param account passed in bank account object
     */
    public BankAccount(BankAccount account) {
        this.uniqueId = account.getUniqueId();
        this.bankId = account.getBankId();
        this.ownerId = account.getOwnerId();
        this.balance = account.getBalance();
    }

    @Override
    public String toString() {
        String outString = "Unique ID: " + uniqueId + "\n";
        outString += "Bank ID: " + bankId + "\n";
        outString += "Owner ID: " + ownerId + "\n";
        DecimalFormat df = new DecimalFormat("0.00");
        outString += "Balance: $" + df.format(balance) + "\n";

        return outString;
    }

    public String balanceAsString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return "$" + df.format(this.getBalance());
    }

    /* Getters and setters */
    public long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public long getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double deposit(double amount) {
        this.balance += amount;

        return this.balance;
    }

    public double withdraw(double amount) {
        this.balance -= amount;

        return this.balance;
    }
}
