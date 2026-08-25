import java.util.Scanner;

public class ATMApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ATMService atm = new ATMService();

        Account account = new SavingsAccount("SA-1001", "Justine Ruth", 5000.00, 0.02);

        boolean running = true;
        while (running) {
            printHeader(account);
            printMenu();
            System.out.print("Choose an option: ");

            try {
                int choice = Integer.parseInt(input.nextLine().trim());

                switch (choice) {
                    case 1:
                        System.out.printf("Balance: PHP %.2f%n", account.getBalance());
                        break;

                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double dep = Double.parseDouble(input.nextLine().trim());
                        atm.deposit(account, dep);
                        System.out.printf("New balance: PHP %.2f%n", account.getBalance());
                        break;

                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double wd = Double.parseDouble(input.nextLine().trim());
                        account.withdraw(wd);
                        System.out.printf("Withdrew PHP %.2f. New balance: PHP %.2f%n",
                                wd, account.getBalance());
                        break;

                    case 4:
                        double total = atm.depositAll(account, 100, 250.5, 300);
                        System.out.printf("Total deposited: PHP %.2f. New balance: PHP %.2f%n",
                                total, account.getBalance());
                        break;

                    case 5:
                        System.out.printf("Balance before: PHP %.2f%n", account.getBalance());
                        atm.tryToReplace(account);
                        System.out.printf("After tryToReplace (still original): PHP %.2f  [%s]%n",
                                account.getBalance(), account);
                        atm.addBonus(account, 50);
                        System.out.printf("After addBonus(+50): PHP %.2f%n", account.getBalance());
                        break;

                    case 0:
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option. Choose 0 to 5.");
                }
            } catch (InsufficientFundsException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.printf("You need PHP %.2f more.%n", e.getShortfall());
            } catch (NumberFormatException e) {
                System.out.println("Error: Please type a number, not letters.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                System.out.println("========================================");
            }
        }

        input.close();
        System.out.println("Thank you for using Liceo ATM!");
    }

    private static void printHeader(Account account) {
        System.out.println("========================================");
        System.out.println("          LICEO ATM MACHINE");
        System.out.println("========================================");
        System.out.printf("Account : %s (%s)%n", account.getAccountNumber(), account.getOwnerName());
        System.out.printf("Type    : %s%n", account.getAccountType());
        System.out.printf("Balance : PHP %.2f%n", account.getBalance());
        System.out.println("----------------------------------------");
    }

    private static void printMenu() {
        System.out.println("[1] Check Balance");
        System.out.println("[2] Deposit");
        System.out.println("[3] Withdraw");
        System.out.println("[4] Deposit Multiple Amounts");
        System.out.println("[5] Pass-by-Value Demo");
        System.out.println("[0] Exit");
    }
}