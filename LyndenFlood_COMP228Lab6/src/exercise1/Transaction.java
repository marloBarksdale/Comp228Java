package exercise1;

public class Transaction implements Runnable {
    private final Account account;
    private final double amount;
    private final String type; // "deposit" or "withdraw"

    public Transaction(Account account, double amount, String type) {
        this.account = account;
        this.amount = amount;
        this.type = type.toLowerCase();
    }

    @Override
    public void run() {
        if (type.equals("deposit")) {
            account.deposit(amount);
        } else if (type.equals("withdraw")) {
            account.withdraw(amount);
        } else {
            System.out.println("Unknown transaction type: " + type);
        }
    }
}