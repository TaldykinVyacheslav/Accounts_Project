/**
 * Class name: CheckingAccount
 * User: User
 * Date: 08.11.13
 * Time: 13:38
 */
public class CheckingAccount extends BaseAccount {
    private double total_transactions;
    private int monthly_quota;
    private double per_transaction_fee;
    private double fee;

    public CheckingAccount(double balance, int monthly_quota, double per_transaction_fee) {
        super(balance);
        this.total_transactions = 0;
        this.monthly_quota = monthly_quota;
        this.per_transaction_fee = per_transaction_fee;
    }

    public CheckingAccount(double balance, int PIN, int monthly_quota, double per_transaction_fee) {
        super(balance, PIN);
        this.total_transactions = 0;
        this.monthly_quota = monthly_quota;
        this.per_transaction_fee = per_transaction_fee;
    }

    @Override
    protected void updateBalance() {
        if(total_transactions > monthly_quota) {
            balance -= per_transaction_fee;
        }
    }

    @Override
    public double getMoney(double count) {
        if(count <= balance) {
            total_transactions++;
            updateBalance();
            return super.getMoney(count);
        }

        return 0;
    }

    @Override
    public double checkBalance() {
        return super.checkBalance();
    }
}
