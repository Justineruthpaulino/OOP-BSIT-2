public class ATMService {

    public void deposit(Account account, double amount) {
        account.deposit(amount);
        System.out.printf("Deposited PHP %.2f%n", amount);
    }

    public void deposit(Account account, double amount, String note) {
        account.deposit(amount);
        System.out.printf("Deposited PHP %.2f (%s)%n", amount, note);
    }

    public double depositAll(Account account, double... amounts) {
        double total = 0;
        for (double a : amounts) {
            deposit(account, a);
            total += a;
        }
        return total;
    }

    public void tryToReplace(Account account) {
        account = new SavingsAccount("XX-000", "Ghost Account", 500.00);
        System.out.println("Inside the method: " + account);
        // Java is pass-by-value: reassigning the local copy does NOT change main's variable.
    }

    public void addBonus(Account account, double bonus) {
        account.deposit(bonus);
        // Mutation IS visible in main because we change the object, not the reference.
    }

    public void transfer(Account from, Account to, double amount) throws InsufficientFundsException {
        from.withdraw(amount);   // withdraw FIRST
        to.deposit(amount);
    }
}