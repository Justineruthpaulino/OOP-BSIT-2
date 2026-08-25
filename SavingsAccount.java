public class SavingsAccount extends Account {

    private static final double MIN_BALANCE = 500.00;

    public SavingsAccount(String accountNumber, String ownerName, double openingBalance) {
        super(accountNumber, ownerName, openingBalance);
        if (openingBalance < MIN_BALANCE) {
            throw new IllegalArgumentException(
                    "Savings opening balance must be at least PHP " + String.format("%.2f", MIN_BALANCE));
        }
    }

    public SavingsAccount(String accountNumber, String ownerName, double openingBalance, double interestRate) {
        this(accountNumber, ownerName, openingBalance);
    }

    @Override
    public String getAccountType() {
        return "SAVINGS";
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        double projected = getBalance() - amount;
        if (projected < MIN_BALANCE) {
            double shortfall = MIN_BALANCE - projected;
            throw new InsufficientFundsException(shortfall);
        }
        applyWithdrawal(amount);
    }
}