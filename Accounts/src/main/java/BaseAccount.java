/**
 * Class name: BaseAccount
 * User: User
 * Date: 02.11.13
 * Time: 13:48
 */
public abstract class BaseAccount {
    protected double balance;
    protected int PIN;

    public int getPIN() {
        return PIN;
    }

    protected void updateBalance() {
        ;
    }

    public BaseAccount(double balance) {
        this.balance = balance;
    }

    protected BaseAccount(double balance, int PIN) {
        this.balance = balance;
        this.PIN = PIN;
    }

    public void addMoney(double count) {
        balance += count;
    }

    public double getMoney(double count) {
        if(count <= balance) {
            balance -= count;
            return count;
        } else {
            return 0;
        }
    }

    public double checkBalance() {
        return balance;
    }
}
