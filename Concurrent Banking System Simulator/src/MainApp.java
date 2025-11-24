package src;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static final Bank bank = new Bank();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- VITyarthi Concurrent Banking System Simulator ---");

        int choice;
        do {
            displayMenu();
            choice = getIntInput("Enter your choice: ");

            try {
                switch (choice) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        listAccounts();
                        break;
                    case 3:
                        runConcurrentSimulation();
                        break;
                    case 4:
                        bank.saveAccounts();
                        System.out.println("Exiting system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
            }
        } while (choice != 4);
    }

    private static void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Create New Account (User Input)");
        System.out.println("2. List All Accounts & Balances");
        System.out.println("3. Run Concurrent Transaction Simulation");
        System.out.println("4. Save & Exit");
        System.out.print("-----------------\n");
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // consume the invalid input
            System.out.print(prompt);
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a numerical amount.");
            scanner.next(); // consume the invalid input
            System.out.print(prompt);
        }
        double value = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        return value;
    }

    // --- Functional Module 1: Create Account ---
    private static void createAccount() {
        System.out.println("\n--- Create Account ---");
        System.out.print("Enter Account Holder Name: ");
        String name = scanner.nextLine();

        System.out.println("Select Account Type:");
        System.out.println("  1. Savings Account");
        System.out.println("  2. Current Account");

        int typeChoice = getIntInput("Enter type (1 or 2): ");
        double initialBalance = getDoubleInput("Enter Initial Balance: ");

        // Generate a simple unique account number (better to use UUID in production)
        String newAccNum = "A" + (bank.getAllAccounts().size() + 101);

        Account newAccount = null;
        if (typeChoice == 1) {
            newAccount = new SavingsAccount(newAccNum, name, initialBalance);
        } else if (typeChoice == 2) {
            // Assuming you add CurrentAccount.java implementation
            // For now, defaulting to Savings if CurrentAccount isn't implemented yet
            newAccount = new SavingsAccount(newAccNum, name, initialBalance);
        } else {
            System.out.println("Invalid account type selected.");
            return;
        }

        bank.addAccount(newAccount);
        System.out.println("SUCCESS: Account created with number: " + newAccNum);
    }

    // --- Functional Module 1: List Accounts ---
    private static void listAccounts() {
        List<Account> accounts = bank.getAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("\nNo accounts found. Please create one first.");
            return;
        }
        System.out.println("\n--- Account Balances ---");
        for (Account acc : accounts) {
            System.out.println("-> " + acc.toString());
        }
    }

    // --- Functional Module 2: Run Simulation ---
    private static void runConcurrentSimulation() throws InterruptedException {
        List<Account> accounts = bank.getAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("\nCannot run simulation. Create accounts first.");
            return;
        }

        System.out.println("\n--- Starting Concurrent Transactions (Threads) ---");

        List<Thread> threads = new ArrayList<>();

        // Use a list of accounts created by the user
        for (Account acc : accounts) {
            Thread t = new Thread(new CustomerTask(acc), "T-Customer-" + acc.getAccountNumber());
            threads.add(t);
            t.start(); // Start the thread
        }

        // Wait for all threads to finish
        for (Thread t : threads) {
            t.join(); // Blocks until the thread dies
        }

        System.out.println("\n--- All Transactions Completed ---");
        listAccounts(); // Show final balances
    }
}