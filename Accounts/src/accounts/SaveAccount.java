//

package accounts;

import java.util.Date;

/**
 * Class name: SaveAccount
 * User: User
 * Date: 02.11.13
 * Time: 13:52
 */
public class SaveAccount extends BaseAccount {
    protected double interest_rate;
    protected Date period;
    protected Date date;

    public SaveAccount(double balance, double interest_rate, Date period, Date date) {
        super(balance);
        this.interest_rate = interest_rate;
        this.period = period;
        this.date = date;
    }

    @Override
    public double getMoney(double count) {
        double result =  super.getMoney(count);
        Date currentDate = new Date();
        return result;
        //if()
    }

    @Override
    public double checkBalance() {
        return super.checkBalance();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
