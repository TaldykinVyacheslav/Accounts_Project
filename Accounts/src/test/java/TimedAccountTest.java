import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;

/**
 * Class name: TimedAccountTest
 * User: User
 * Date: 08.11.13
 * Time: 20:06
 */
public class TimedAccountTest {
    @Test
    public void testGetMoney() throws Exception {
        TimedAccount timedAccount1 = new TimedAccount(10000, 0.05, 2, new DateTime(), 0.1);
        TimedAccount timedAccount2 = new TimedAccount(10000, 0.05, 2, (new DateTime()).minusMonths(3), 0.1);

        String errorMessage = "Error in TimedAccount.getMoney()";
        double result;

        result = timedAccount1.getMoney(5000);
        assertEquals(errorMessage, 4500, result, 0.0);

        result = timedAccount1.getMoney(20000);
        assertEquals(errorMessage, 0.0, result, 0.0);

        result = timedAccount2.getMoney(10500);
        assertEquals(errorMessage, 10500, result, 0.0);
    }

    @Test
    public void testCheckBalance() throws Exception {
        TimedAccount timedAccount1 = new TimedAccount(10000, 0.05, 2, new DateTime(), 0.1);
        TimedAccount timedAccount2 = new TimedAccount(10000, 0.05, 2, (new DateTime()).minusMonths(3), 0.1);
        TimedAccount timedAccount3 = new TimedAccount(10000, 0.05, 2, (new DateTime()).minusMonths(1), 0.1);

        String errorMessage = "Error in TimedAccount.checkBalance()";
        double result;

        result = timedAccount1.checkBalance();
        assertEquals(errorMessage, 10000, result, 0.0);

        result = timedAccount2.checkBalance();
        assertEquals(errorMessage, 10500, result, 0.0);

        result = timedAccount3.checkBalance();
        assertEquals(errorMessage, 10000, result, 0.0);
    }

    @Test
    public void test1AddMoney() {
        TimedAccount timedAccount = new TimedAccount(10000, 0.05, 2, new DateTime(), 0.1);

        String errorMessage = "Error in TimedAccount.addMoney()";
        double result;

        timedAccount.addMoney(5000);
        result = timedAccount.checkBalance();
        assertEquals(errorMessage, 15000, result, 0.0);
    }

    @Test
    public void test2AddMoney() {
        TimedAccount timedAccount = new TimedAccount(10000, 0.05, 2, new DateTime(), 0.1);

        String errorMessage = "Error in TimedAccount.addMoney()";
        double result;

        timedAccount.addMoney(5000);
        timedAccount.getMoney(12000);
        result = timedAccount.checkBalance();
        assertEquals(errorMessage, 3000, result, 0.0);
    }
}
