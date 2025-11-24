package src;

import java.util.Random;

// Implements Runnable for Threading
public class CustomerTask implements Runnable {
    private final Account account;
    private final Random random = new Random();

    public CustomerTask(Account account) {
        this.account = account;
    }

    // The core of the concurrent transaction processing
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) { // Perform 5 random operations
            double amount = 100 + random.nextInt(400); // 100 to 500

            if (random.nextBoolean()) {
                // Deposit
                account.deposit(amount);
            } else {
                // Withdrawal (with Exception Handling)
                try {
                    account.withdraw(amount);
                } catch (InsufficientFundsException e) {
                    System.err.println(Thread.currentThread().getName() + " - Error: " + e.getMessage());
                } catch (Exception e) {
                    System.err.println(Thread.currentThread().getName() + " - Unexpected Error: " + e.getMessage());
                }
            }

            try {
                // Introduce a pause to simulate real-world processing delay
                Thread.sleep(random.nextInt(50));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}