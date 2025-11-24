package src;

// Custom Checked Exception
public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        // Calls the constructor of the Exception superclass
        super(message);
    }
}