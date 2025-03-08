package main.java;

class CheckingBankAccount extends BankAccount {
    /**
     * Constructor class
     * 
     * @param bankID  bank account id
     * @param ownerID owner account id
     * @param balance starting balance for the account
     */
    public CheckingBankAccount(long uniqueId, long bankId, long ownerId, double balance) {
        super(uniqueId, bankId, ownerId, balance);
    }

    @Override
    public double deposit(double amount) {
        this.balance += amount;

        return this.balance;
    }
    
    @Override
    public double withdraw(double amount) {
        if(balance >= amount) 
        {
            this.balance -= amount;
        }

        return this.balance;
    }
}