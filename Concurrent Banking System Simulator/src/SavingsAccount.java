package src;

public class SavingsAccount extends Account {
    private static final double SAVINGS_MIN_BALANCE = 1000.0;

    // Uses 'super' to call parent constructor
    public SavingsAccount(String accountNumber, String accountHolderName, double initialBalance) {
        super(accountNumber, accountHolderName, initialBalance);
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }

    @Override
    public double getMinimumBalance() {
        return SAVINGS_MIN_BALANCE;
    }
}