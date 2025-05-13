package exercise1;

public class Account {
    private double balance;

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    public synchronized void deposit(double amount) {
        System.out.printf("[Deposit] Amount: $%.2f | Old Balance: $%.2f", amount, balance);
        balance += amount;
        System.out.printf(" | New Balance: $%.2f\n", balance);
    }

    public synchronized void withdraw(double amount) {
        System.out.printf("[Withdraw] Amount: $%.2f | Old Balance: $%.2f", amount, balance);
        if (amount <= balance) {
            balance -= amount;
            System.out.printf(" | New Balance: $%.2f\n", balance);
        } else {
            System.out.println(" | Insufficient funds. Withdrawal denied.");
        }
    }

    public synchronized double getBalance() {
        return balance;
    }
}

// File: exercise1/Transaction.java