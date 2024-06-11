import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class ExpenseTracker {

    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nExpense Tracker");
            System.out.println("1. Add Expense");
            System.out.println("2. View Transactions");
            System.out.println("3. Total Expenses");
            System.out.println("4. Exit");

            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewTransactions();
                    break;
                case 3:
                    totalExpenses();
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 4.");
            }
        }
    }

    private static int getUserChoice() {
        int choice = -1;
        while (choice == -1) {
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next(); // Clear the invalid input
            }
        }
        return choice;
    }

    private static void addExpense() {
        try {
            System.out.print("Enter expense amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character

            System.out.print("Enter expense description: ");
            String description = scanner.nextLine();

            transactions.add(new Transaction(amount, description));
            System.out.println("Expense added successfully!");
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount! Please enter a valid number.");
            scanner.next(); // Clear the invalid input
        }
    }

    private static void viewTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found!");
            return;
        }

        System.out.println("\nTransactions:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private static void totalExpenses() {
        double total = 0;
        for (Transaction transaction : transactions) {
            total += transaction.getAmount();
        }

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        System.out.println("Total expenses: " + currencyFormat.format(total));
    }
}

class Transaction {
    private double amount;
    private String description;

    public Transaction(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        return "Amount: " + currencyFormat.format(amount) + ", Description: " + description;
    }
}
