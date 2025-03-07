package main.java.trevin;
//Author: Trevin van Hook
//Date: 2/16/2025
//Description: Compliant code for rule OBJ01_J and recommendations OBJ55-J

import java.util.Arrays;

public class OBJ01_J {
    /**Integer array containing account balances, just have 6 for testing  */
    private static Integer[] accountBalances = new Integer[]{100,300,0,25,30,9235};

    /**
     * main method
     * 
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("starting program...");
        System.out.println("initial balances: " + Arrays.toString(accountBalances));
        System.out.println("setting second account balance to 500...");
        setAccountBalance(1, 500);
        System.out.println("current balances: " + Arrays.toString(accountBalances));
        System.out.println("getting the 3rd account balance: " + getAccountBalance(3).toString());
        System.out.println("setting all short-lived objects to null...");
        for (int i = 0; i < accountBalances.length; i++){
            accountBalances[i] = null;
        }
        System.out.println("current balances: " + Arrays.toString(accountBalances));
        System.out.println("all tasks performed properly and securely, program done.");
    }

    /**
     * method that returns the array of account balances
     * @return a cloned array of the account balances object
     */
    public static Integer[] getAccountBalances(){
        return accountBalances.clone();
    }
    /**
     * method that sets the account balance given the position and amount in the list if it's a valid position
     * @param position the position of the balance you want to set the amount to
     * @param amount the amount you want to set the balance to
     */
    public static void setAccountBalance(int position, int amount){
        if (accountBalances.length >= position){
            accountBalances[position] = amount;
        }
    }
    /**
     * method that returns a balance given a position if it's a valid position
     * @param position the position of the account balance you want to get
     * @return the balance of that account, or null if it's a invalid postiion
     */
    public static Integer getAccountBalance(int position){
        if (accountBalances.length >= position){
            return accountBalances[position-1];
        }
        return null;
    }
}
