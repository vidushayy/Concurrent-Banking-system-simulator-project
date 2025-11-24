package src;

import java.io.Serializable;

public abstract class Account implements Serializable {
    private static final long serialVersionUID = 1L; // For File Handling
    private final String accountNumber;
    private double balance;
    private final String accountHolderName;

    // Constructor using 'this'
    public Account(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    // Abstract methods (Polymorphism)
    public abstract String getAccountType();
    public abstract double getMinimumBalance();

    // Synchronization is key here for thread safety
    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(Thread.currentThread().getName() + ": Deposited " + amount + " to Account " + this.accountNumber);
        }
    }

    public synchronized void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (balance - amount < getMinimumBalance()) {
            throw new InsufficientFundsException("Account " + this.accountNumber + " requires a minimum balance of " + getMinimumBalance());
        }
        balance -= amount;
        System.out.println(Thread.currentThread().getName() + ": Withdrew " + amount + " from Account " + this.accountNumber);
    }

    // Getters
    public String getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public String getAccountHolderName() { return accountHolderName; }

    @Override
    public String toString() {
        return getAccountType() + " [#" + accountNumber + ", Holder: " + accountHolderName + ", Balance: " + balance + "]";
    }
}