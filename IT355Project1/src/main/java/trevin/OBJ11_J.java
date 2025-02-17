package main.java.trevin;
//Author: Trevin van Hook
//Date: 2/16/2025
//Description: Compliant code example for rule OBJ11_J

/**
 * Account class
 */
final class Account {
    private static volatile boolean initialized = false;
    /**
     * Method to simulate login, and verify that the object is fully constructed
     */
    private static void accountLogin() {
        if (!accountVerification()) {
            /** if this works, means not initialized */
            return;
        }
        initialized = true;
    }
    /**
     * method to verify account details, only returning true for this example
     * @return only true
     */
    private static boolean accountVerification() {
        /** for the simulation, just assume account details are true */
        return true;
    }
    /**
     * method to run a simulation of logging into an account and testing if the current Accoutn object has initialized properly.
     */
    public void accountSimulation() {
        /* do account login */
        accountLogin();
        /* check if things have initialized and login is valid */
        if (!initialized) {
            throw new SecurityException("Invalid Login");
        } else {
            System.out.println("Login successful.");
        }
    }
}
 public final class OBJ11_J{
        /**main method */
        public static void main(String[] args) {
            System.out.println("starting program");
            Account testAccount = new Account();
            testAccount.accountSimulation();
            System.out.println("ending program");
        }
 }