package src;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private final Map<String, Account> accounts = new HashMap<>();
    private static final String DATA_FILE = "accounts.dat";

    public Bank() {
        loadAccounts(); // Load state upon initialization
    }

    // Module 1: Core Account Management
    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
        System.out.println("Account created: " + account.getAccountNumber());
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }

    // Module 3: Persistence (File Handling)
    public void saveAccounts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(accounts);
            System.out.println("\n[SYSTEM] Account data saved successfully.");
        } catch (IOException e) {
            System.err.println("\n[SYSTEM] Error saving accounts: " + e.getMessage());
        }
    }

    // Module 3: Persistence (File Handling & Exception Handling)
    @SuppressWarnings("unchecked") // Suppress warning for type casting
    private void loadAccounts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            Map<String, Account> loadedAccounts = (Map<String, Account>) ois.readObject();
            accounts.putAll(loadedAccounts);
            System.out.println("[SYSTEM] Account data loaded successfully. Total accounts: " + accounts.size());
        } catch (FileNotFoundException e) {
            System.out.println("[SYSTEM] No existing data file found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("[SYSTEM] Error loading accounts: " + e.getMessage());
        }
    }
}