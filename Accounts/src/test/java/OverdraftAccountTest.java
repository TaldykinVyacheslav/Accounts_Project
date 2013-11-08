import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Test;

/**
 * Class name: OverdraftAccountTest
 * User: User
 * Date: 08.11.13
 * Time: 21:17
 */
public class OverdraftAccountTest {
    @Test
    public void testGetMoney() throws Exception {
        OverdraftAccount overdraftAccount = new OverdraftAccount(10000, new DateTime(), 0.05, 2);

        String errorMessage = "Error in OverdraftAccount.getMoney()";
        double result;

        result = overdraftAccount.getMoney(1000);
        assertEquals(errorMessage, 1000, result, 0.0);

        result = overdraftAccount.getMoney(20000);
        assertEquals(errorMessage, 20000, result, 0.0);
    }

    @Test
    public void testCheckBalance() throws Exception {
        OverdraftAccount overdraftAccount1 = new OverdraftAccount(2000, new DateTime(), 0.05, 2);
        OverdraftAccount overdraftAccount2 = new OverdraftAccount(5000, new DateTime(), 0.05, 2);
        OverdraftAccount overdraftAccount3 = new OverdraftAccount(10000, new DateTime(), 0.05, 2);

        String errorMessage = "Error in overdraftAccount.checkBalance()";
        double result;

        result = overdraftAccount1.checkBalance();
        assertEquals(errorMessage, 2000, result, 0.0);

        result = overdraftAccount2.checkBalance();
        assertEquals(errorMessage, 5000, result, 0.0);

        result = overdraftAccount3.checkBalance();
        assertEquals(errorMessage, 10000, result, 0.0);
    }

    @Test
    public void test1AddMoney() {
        OverdraftAccount overdraftAccount = new OverdraftAccount(10000, new DateTime(), 0.05, 2);

        String errorMessage = "Error in overdraftAccount.addMoney()";
        double result;

        overdraftAccount.addMoney(10000);
        result = overdraftAccount.checkBalance();
        assertEquals(errorMessage, 20000, result, 0.0);
    }

    @Test
    public void test2AddMoney() {
        OverdraftAccount overdraftAccount = new OverdraftAccount(10000, new DateTime(), 0.05, 2);

        String errorMessage = "Error in overdraftAccount.addMoney()";
        double result;

        overdraftAccount.getMoney(10000);
        result = overdraftAccount.checkBalance();
        assertEquals(errorMessage, 0.0, result, 0.0);
    }

    @Test
    public void test3AddMoney() {
        OverdraftAccount overdraftAccount = new OverdraftAccount(10000, new DateTime(), 0.05, 2);

        String errorMessage = "Error in overdraftAccount.addMoney()";
        double result;

        overdraftAccount.getMoney(20000);
        result = overdraftAccount.checkBalance();
        assertEquals(errorMessage, -10000, result, 0.0);
    }

    @Test
    public void test1UpdateBalance() {
        OverdraftAccount overdraftAccount = new OverdraftAccount(10000, new DateTime(), 0.05, 2);

        String errorMessage = "Error in overdraftAccount.UpdateBalance()";
        double result;

        overdraftAccount.getMoney(20000);
        result = overdraftAccount.checkBalance();
        assertEquals(errorMessage, -10000, result, 0.0);

        overdraftAccount.updateBalance();
        result = overdraftAccount.checkBalance();
        assertEquals(errorMessage, -10000, result, 0.0);
    }

    @Test
    public void test2UpdateBalance() {
        OverdraftAccount overdraftAccount = new OverdraftAccount(10000, (new DateTime()).minusMonths(3), 0.05, 2);

        String errorMessage = "Error in overdraftAccount.UpdateBalance()";
        double result;

        overdraftAccount.getMoney(20000);
        result = overdraftAccount.checkBalance();
        assertEquals(errorMessage, -10000, result, 0.0);

        overdraftAccount.updateBalance();
        result = overdraftAccount.checkBalance();
        assertEquals(errorMessage, -10500, result, 0.0);
    }
}
