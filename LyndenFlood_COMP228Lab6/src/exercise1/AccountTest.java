package exercise1;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AccountTest {
    public static void main(String[] args) {
        Account sharedAccount = new Account(1000.00);

        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(sharedAccount, 200, "withdraw"));
        transactions.add(new Transaction(sharedAccount, 150, "deposit"));
        transactions.add(new Transaction(sharedAccount, 300, "withdraw"));
        transactions.add(new Transaction(sharedAccount, 100, "deposit"));
        transactions.add(new Transaction(sharedAccount, 500, "withdraw"));

        ExecutorService executor = Executors.newFixedThreadPool(3);

        System.out.println("Starting Transactions...\n");

        for (Transaction t : transactions) {
            executor.execute(t);
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
            // Wait until all threads are finished
        }

        System.out.printf("\nFinal Balance: $%.2f\n", sharedAccount.getBalance());
    }
}
