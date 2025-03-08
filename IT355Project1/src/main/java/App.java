package main.java;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.print("Enter desired bank name: ");
        Scanner in = new Scanner(System.in);
        String bankName = in.nextLine();
        String betterBankName = handleInput(bankName);
        System.out.print("Enter bank ID: ");
        int bankID = in.nextInt();
        in.nextLine();
        Bank bank = new Bank(bankID, betterBankName);
        System.out.println("Welcome to " + bank.getName() + " Bank!");
        boolean keepGoing = true;

        while (keepGoing) {
            System.out.println("\nMenu:");
            System.out.println("1. List Bank Accounts");
            System.out.println("2. Open Account");
            System.out.println("3. Check Balance");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Close Account");
            System.out.println("7. Import Account Details");
            System.out.println("8. Export Account Details");
            System.out.println("9. Quit");
            System.out.print("Enter an option: ");
            int choice = -1;
            if (in.hasNextInt()) {
                choice = in.nextInt();
            }

            // Allow for quitting by entering "Q" or "q"
            if (in.nextLine().equalsIgnoreCase("q")) {
                choice = 9;
            }

            switch (choice) {
                case 1:
                    if (bank.getOpenedAccounts() > 0) {
                        bank.printAccounts();
                    } else {
                        System.out.println("No accounts to list.");
                    }
                    break;

                case 2:
                    System.out.print("Enter user ID of account owner: ");
                    int openUserID = in.nextInt();
                    in.nextLine();
                    boolean goodStartBal = false;
                    double startBal = 0.00;
                    do {
                        System.out.print("Enter starting balance: ");
                        startBal = in.nextDouble();
                        in.nextLine();
                        if (startBal < 0.00) {
                            System.out.println("Invalid starting balance.");
                        } else {
                            goodStartBal = true;
                        }
                    } while (!goodStartBal);
                    BankAccount newAccount = bank.openAccount(openUserID, startBal);
                    System.out.println("Account opened successfully.\n");
                    System.out.print(newAccount);
                    break;

                case 3:
                    BankAccount checkAccount = getAccount(in, bank);
                    if (checkAccount != null) {
                        System.out.println("\nBalance: " + checkAccount.balanceAsString());
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 4:
                    BankAccount depositAccount = getAccount(in, bank);
                    if (depositAccount != null) {
                        System.out.print("Enter amount to deposit: ");
                        int depositAmount = in.nextInt();
                        in.nextLine();
                        if (depositAmount <= 0) {
                            System.out.println("Invalid deposit amount.");
                        } else {
                            depositAccount.deposit(depositAmount);
                            bank.updateAccount(depositAccount.getOwnerId(), depositAccount.getUniqueId(),
                                    depositAccount);
                            System.out.println("\nBalance after deposit: " + depositAccount.balanceAsString());
                        }
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 5:
                    BankAccount withdrawAccount = getAccount(in, bank);
                    if (withdrawAccount != null) {
                        System.out.print("Enter amount to withdraw: ");
                        int withdrawAmount = in.nextInt();
                        in.nextLine();
                        if (withdrawAmount > withdrawAccount.getBalance()) {
                            System.out.println("Withdrawal amount more than balance.");
                        } else if (withdrawAmount <= 0) {
                            System.out.println("Invalid withdrawal amount.");
                        } else {
                            withdrawAccount.withdraw(withdrawAmount);
                            bank.updateAccount(withdrawAccount.getOwnerId(), withdrawAccount.getUniqueId(),
                                    withdrawAccount);
                        }
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 6:
                    System.out.print("Enter user ID of account: ");
                    int userID = in.nextInt();
                    in.nextLine();
                    System.out.print("Enter account ID: ");
                    int accID = in.nextInt();
                    in.nextLine();
                    bank.closeAccount(userID, accID);
                    break;

                case 7:
                    break;

                case 8:
                    break;

                case 9:
                    keepGoing = false;
                    break;

                default:
                    System.out.println("Choice not recognized. Please choose again.");
                    break;
            }
        }

        in.close();
    }

    /**
     * Method that normalizes and does string validations
     * 
     * @param str the string to normalize and modify
     * @return the normalized and modified string
     */
    static String handleInput(String str) {
        String s = Normalizer.normalize(str, Form.NFKC);

        // Replaces all noncharacter code points with Unicode U+FFFD
        s = s.replaceAll("[\\p{Cn}]", "\uFFFD");

        return s;
    }

    /**
    * Retrieves a bank account based on the provided user ID and account ID.
    * This method ensures that the bank object is not null and handles cases where the account is not found.
    * @param in   The Scanner object used to read user input.
    * @param bank The Bank object containing the account list.
    * @return The BankAccount object if found, or null if the account is not found.
    * @throws IllegalArgumentException If the bank object is null.
    */
    static BankAccount getAccount(Scanner in, Bank bank) {
        System.out.print("Enter user ID of account: ");
        int userID = in.nextInt();
        in.nextLine();
        System.out.print("Enter account ID: ");
        int accID = in.nextInt();
        in.nextLine();
        if (bank == null) {
            throw new IllegalArgumentException("Bank object cannot be null");
        }
        BankAccount bankAccount = bank.getAccount(userID, accID);
        if (bankAccount == null) {
            System.out.println("Account not found.");
            return null;
        }
    
        return bankAccount;
    }
}