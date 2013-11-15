import static org.junit.Assert.assertEquals;

import accounts.CheckingAccount;
import org.junit.Test;

/**
 * Class name: CheckingAccountTest
 * User: User
 * Date: 08.11.13
 * Time: 20:56
 */
public class CheckingAccountTest {
    @Test
    public void testGetMoney() throws Exception {
        CheckingAccount checkingAccount = new CheckingAccount(10000, 5, 100);

        String errorMessage = "Error in AccountTest.getMoney()";
        double result;

        //balance = 10000

        result = checkingAccount.getMoney(1000);
        assertEquals(errorMessage, 1000, result, 0.0);

        //balance = 9000

        for(int i = 0; i < 4; i++) {
            result = checkingAccount.getMoney(500);
        }
        assertEquals(errorMessage, 500, result, 0.0);

        //balance = 7000

        result = checkingAccount.getMoney(20000);
        assertEquals(errorMessage, 0.0, result, 0.0);

        //balance = 7000

        result = checkingAccount.getMoney(1000);
        assertEquals(errorMessage, 1000, result, 0.0);

        //balance = 5900

        result = checkingAccount.getMoney(5900);
        assertEquals(errorMessage, 0.0, result, 0.0);
    }

    @Test
    public void testCheckBalance() throws Exception {
        CheckingAccount checkingAccount1 = new CheckingAccount(2000, 5, 100);
        CheckingAccount checkingAccount2 = new CheckingAccount(5000, 5, 100);
        CheckingAccount checkingAccount3 = new CheckingAccount(10000, 5, 100);

        String errorMessage = "Error in accounts.CheckingAccount.checkBalance()";
        double result;

        result = checkingAccount1.checkBalance();
        assertEquals(errorMessage, 2000, result, 0.0);

        result = checkingAccount2.checkBalance();
        assertEquals(errorMessage, 5000, result, 0.0);

        result = checkingAccount3.checkBalance();
        assertEquals(errorMessage, 10000, result, 0.0);
    }

    @Test
    public void test1AddMoney() {
        CheckingAccount checkingAccount = new CheckingAccount(10000, 5, 100);

        String errorMessage = "Error in accounts.CheckingAccount.AddMoney()";
        double result;

        checkingAccount.addMoney(5000);
        result = checkingAccount.checkBalance();
        assertEquals(errorMessage, 15000, result, 0.0);
    }

    @Test
    public void test2AddMoney() {
        CheckingAccount checkingAccount = new CheckingAccount(10000, 5, 100);

        String errorMessage = "Error in accounts.CheckingAccount.AddMoney()";
        double result;

        checkingAccount.getMoney(3000);
        checkingAccount.addMoney(5000);
        result = checkingAccount.checkBalance();
        assertEquals(errorMessage, 12000, result, 0.0);
    }

    @Test
    public void test3AddMoney() {
        CheckingAccount checkingAccount = new CheckingAccount(10000, 5, 100);

        String errorMessage = "Error in accounts.CheckingAccount.AddMoney()";
        double result;

        checkingAccount.getMoney(3000);

        //balance = 7000

        for(int i = 0; i < 4; i++) {
            result = checkingAccount.getMoney(500);
        }

        //balance = 5000

        checkingAccount.addMoney(15000);

        //balance = 20000

        result = checkingAccount.checkBalance();
        assertEquals(errorMessage, 20000, result, 0.0);
    }
}
