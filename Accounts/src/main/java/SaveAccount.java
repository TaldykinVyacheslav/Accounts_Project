//

import org.joda.time.DateTime;
import org.joda.time.Months;

/**
 * Class name: SaveAccount
 * User: User
 * Date: 02.11.13
 * Time: 13:52
 */
public class SaveAccount extends BaseAccount {
    protected double interest_rate;
    protected int periodMonths;
    protected DateTime lastUpdateDate;

    public SaveAccount(double balance, double interest_rate, int period, DateTime date) {
        super(balance);
        this.interest_rate = interest_rate;
        this.periodMonths = period;
        this.lastUpdateDate = date;
    }

    public SaveAccount(double balance, int PIN, double interest_rate, int periodMonths, DateTime lastUpdateDate) {
        super(balance, PIN);
        this.interest_rate = interest_rate;
        this.periodMonths = periodMonths;
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    protected void updateBalance() {
        DateTime currentDate = new DateTime();
        Months delta = Months.monthsBetween(currentDate, lastUpdateDate);
        if(Math.abs(delta.getMonths()) > periodMonths) {
            balance += interest_rate * balance;
        }
        lastUpdateDate = currentDate;
    }

    @Override
    public double getMoney(double count) {
        updateBalance();

        return super.getMoney(count);
    }

    @Override
    public double checkBalance() {
        updateBalance();

        return balance;
    }
}
