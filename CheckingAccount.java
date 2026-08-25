public class CheckingAccount extends Account {

    private final double overdraftLimit;

    public CheckingAccount(String accountNumber, String ownerName, double openingBalance, double overdraftLimit) {
        super(accountNumber, ownerName, openingBalance);
        if (overdraftLimit < 0) {
            throw new IllegalArgumentException("Overdraft limit cannot be negative");
        }
        this.overdraftLimit = overdraftLimit;
    }

    public CheckingAccount(String accountNumber, String ownerName, double openingBalance) {
        this(accountNumber, ownerName, openingBalance, 0.0);
    }

    @Override
    public String getAccountType() {
        return "CHECKING";
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        double projected = getBalance() - amount;
        if (projected < -overdraftLimit) {
            double shortfall = -overdraftLimit - projected;
            throw new InsufficientFundsException(shortfall);
        }
        applyWithdrawal(amount);
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }
}