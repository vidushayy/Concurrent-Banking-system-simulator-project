package src;

public class CurrentAccount extends Account {
    private static final double CURRENT_MIN_BALANCE = 5000.0;

    // Uses 'super' to call parent constructor
    public CurrentAccount(String accountNumber, String accountHolderName, double initialBalance) {
        super(accountNumber, accountHolderName, initialBalance);
    }

    @Override
    public String getAccountType() {
        return "Current";
    }

    @Override
    public double getMinimumBalance() {
        return CURRENT_MIN_BALANCE;
    }
}