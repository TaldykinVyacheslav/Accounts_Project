import org.joda.time.DateTime;
import org.joda.time.Months;

/**
 * Class name: TimedAccount
 * User: User
 * Date: 08.11.13
 * Time: 12:46
 */
public class TimedAccount extends SaveAccount {
    private double withdraw_amount;

    public TimedAccount(double balance, double interest_rate, int period, DateTime date, double withdraw_amount) {
        super(balance, interest_rate, period, date);
        this.withdraw_amount = withdraw_amount;
    }

    public TimedAccount(double balance, int PIN, double interest_rate, int periodMonths,
                        DateTime lastUpdateDate, double withdraw_amount) {
        super(balance, PIN, interest_rate, periodMonths, lastUpdateDate);
        this.withdraw_amount = withdraw_amount;
    }

    @Override
    public void addMoney(double count) {
        super.addMoney(count);
    }

    @Override
    protected void updateBalance() {
        DateTime currentDate = new DateTime();
        Months delta = Months.monthsBetween(currentDate, lastUpdateDate);
        if(Math.abs(delta.getMonths()) >= periodMonths) {
            balance += (interest_rate * balance);
            lastUpdateDate = currentDate;
        }
    }

    @Override
    public double checkBalance() {
        updateBalance();

        return balance;
    }

    @Override
    public double getMoney(double count) {
        double result;

        if(count <= (balance + (interest_rate * balance))) {
            DateTime currentDate = new DateTime();
            Months delta = Months.monthsBetween(currentDate, lastUpdateDate);

            updateBalance();
            result = super.getMoney(count);
            if(Math.abs(delta.getMonths()) < periodMonths) {
                result -= result * withdraw_amount;
            }

            return result;
        }

        return 0;
    }
}
