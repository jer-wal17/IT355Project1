package main.java;

class BankAccount{
    private long uniqueId;
    private long bankId;
    private long ownerId;
    private float balance;

    /**
     * Constructor class
     * @param bankID bank account id
     * @param ownerID owner account id
     * @param balance starting balance for the account
     */
    public BankAccount(long uniqueId, long bankId, long ownerId, float balance){
        this.uniqueId = uniqueId;
        this.bankId = bankId;
        this.ownerId = ownerId;
        this.balance = balance;
    }
    /**
     * Copy constructor for a bank account object
     * @param account passed in bank account object
     */
    public BankAccount(BankAccount account){
        this.uniqueId = account.getUniqueId();
        this.bankId = account.getBankId();
        this.ownerId = account.getOwnerId();
        this.balance = account.getBalance();
    }
    
    /*Getters and setters */
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

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
