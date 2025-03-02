package main.java;

public class Bank {
    private long uniqueId;
    private String name;
    private BankAccount[] accountList;
    private long openedAccounts = 0;/*using this to generate a unique ID for accounts*/
    /**
     * Constructor for a bank object
     * @param uniqueId unique identifier for the bank
     * @param name name of the bank
     */
    public Bank(long uniqueId, String name){
        this.uniqueId = uniqueId;
        this.name = name;
        this.accountList = new BankAccount[10];/*just have this at 10 for now, we can change how this works at any point lol */
    }
    /**
     * Copy constructor for a bank object
     * @param bank a passed in bank object
     */
    public Bank(Bank bank){
        this.uniqueId = bank.getUniqueId();
        this.name = bank.getName();
        this.accountList = bank.getAccountList();
        this.openedAccounts = bank.getOpenedAccounts();
    }

    /**
     * opens an account in the bank if ones available using a User's id and a given balance
     * @param userId user id that will be set to the owner of the bank account
     * @param balance starting balance of the account
     */
    public void openAccount(int userId, int balance){
        /*find a open space and open account there*/
        for (int i = 0; i < accountList.length;i++){
            if (accountList[i] == null){
                accountList[i] =  new BankAccount(openedAccounts++, uniqueId, userId, balance);
                break;
            }
        }
    }
    /**
     * Method that looks for an open account that matches the userId and accountId given, and closes it if so.
     * @param userId id of the owner of the account
     * @param accountId unique id of the account
     */
    public void closeAccount(int userId, int accountId){
        for (int i = 0; i < accountList.length;i++){
            if (accountList[i] != null){
                /*check if owner and account id are the same */
                if (accountList[i].getOwnerId() == userId && accountList[i].getUniqueId() == accountId){
                    accountList[i] = null;
                    break;
                }
            }
        }
    }
    /**
     * Method that returns a copy of the account with the given userid and accountId
     * @param userId user id of the owner of the account
     * @param accountId unique id of the account
     * @return bank account with passed in userid and accountid or null
     */
    public BankAccount getAccount(int userId, int accountId){
        for (int i = 0; i < accountList.length;i++){
            if (accountList[i] != null){
                /*check if owner and account id are the same */
                if (accountList[i].getOwnerId() == userId && accountList[i].getUniqueId() == accountId){
                    BankAccount returnBankAccount = new BankAccount(accountList[i].getUniqueId(), accountList[i].getBankId(), accountList[i].getOwnerId(), accountList[i].getBalance());
                    return returnBankAccount;
                }
            }
        }
        return null;
    }

    /**getters and setters that are needed */
    public long getUniqueId() {
        return uniqueId;
    }
    public String getName() {
        return name;
    }
    public long getOpenedAccounts() {
        return openedAccounts;
    }
    /**
     * Method to return a deep copy of the Bank Account array
     * @return a deep copy of the accountList
     */
    public BankAccount[] getAccountList() {
        BankAccount[] copiedList = new BankAccount[accountList.length];
        for (int i = 0; i < accountList.length;i++){
            if (accountList[i] != null){
                copiedList[i] = new BankAccount(accountList[i].getUniqueId(), accountList[i].getBankId(), accountList[i].getOwnerId(), accountList[i].getBalance());
            }
        }
        return copiedList;
    }
}
