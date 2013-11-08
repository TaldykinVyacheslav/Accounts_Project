import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Class name: SaveAccountTest
 * User: User
 * Date: 08.11.13
 * Time: 19:18
 */
public class SaveAccountTest {

    @Test
    public void testGetMoney() throws Exception {
        SaveAccount saveAccount = new SaveAccount(10000, 0.03, 2, new DateTime());
        SaveAccount saveAccount2 = new SaveAccount(10000, 0.03, 2, (new DateTime()).minusMonths(3));

        String errorMessage = "Error in SaveAccount.getMoney()";

        double result = saveAccount.getMoney(5000);
        assertEquals(errorMessage, 5000, result, 0.0);

        result = saveAccount.getMoney(10000);
        assertEquals(errorMessage, 0.0, result, 0.0);

        result = saveAccount2.getMoney(10300);
        assertEquals(errorMessage, 10300, result, 0.0);
    }

    @Test
    public void testCheckBalance() throws Exception {
        SaveAccount saveAccount = new SaveAccount(10000, 0.03, 2, new DateTime());
        SaveAccount saveAccount2 = new SaveAccount(10000, 0.03, 2, (new DateTime()).minusMonths(3));
        SaveAccount saveAccount3 = new SaveAccount(10000, 0.03, 2, (new DateTime()).minusMonths(1));

        String errorMessage = "Error in SaveAccount.getMoney()";
        double result;

        result = saveAccount.checkBalance();
        assertEquals(errorMessage, 10000, result, 0.0);

        result = saveAccount2.checkBalance();
        assertEquals(errorMessage, 10300, result, 0.0);

        result = saveAccount3.checkBalance();
        assertEquals(errorMessage, 10000, result, 0.0);
    }

    @Test
    public void test1AddMoney() {
        SaveAccount saveAccount = new SaveAccount(10000, 0.05, 2, new DateTime());

        String errorMessage = "Error in SaveAccount.addMoney()";
        double result;

        saveAccount.addMoney(5000);
        result = saveAccount.checkBalance();
        assertEquals(errorMessage, 15000, result, 0.0);
    }

    @Test
    public void test2AddMoney() {
        SaveAccount saveAccount = new SaveAccount(10000, 0.05, 2, new DateTime());

        String errorMessage = "Error in SaveAccount.addMoney()";
        double result;

        saveAccount.addMoney(5000);
        saveAccount.getMoney(12000);
        result = saveAccount.checkBalance();
        assertEquals(errorMessage, 3000, result, 0.0);
    }
}
