import org.joda.time.DateTime;
import org.joda.time.Months;

/**
 * Class name: OverdraftAccount
 * User: User
 * Date: 08.11.13
 * Time: 15:44
 */
public class OverdraftAccount extends BaseAccount {
    protected double interest_rate;
    protected int periodMonths;
    protected DateTime lastUpdateDate;

    public OverdraftAccount(double balance, DateTime date, double interest_rate, int periodMonths) {
        super(balance);
        this.periodMonths = periodMonths;
        this.lastUpdateDate = date;
        this.interest_rate = interest_rate;
    }

    @Override
    public void updateBalance() {
        DateTime currentDate = new DateTime();
        Months delta = Months.monthsBetween(currentDate, lastUpdateDate);
        if(Math.abs(delta.getMonths()) >= periodMonths) {
            balance -= interest_rate * Math.abs(balance);
        }

        lastUpdateDate = currentDate;
    }

    @Override
    public double getMoney(double count) {
        balance -= count;
        return count;
    }

    @Override
    public double checkBalance() {
        return super.checkBalance();
    }
}
