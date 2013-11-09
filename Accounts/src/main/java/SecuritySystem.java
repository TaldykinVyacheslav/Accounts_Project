import org.joda.time.DateTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

//Printed report + diagrams + Git link
//2 pages
//listing
//demo

/**
 * Class name: SecuritySystem
 * User: User
 * Date: 09.11.13
 * Time: 13:09
 */
public class SecuritySystem {
    private Map<Integer, BaseAccount> accounts;

    public SecuritySystem() {
        accounts = new HashMap<Integer, BaseAccount>();
    }

    public BaseAccount getAccount(int key) {
        return accounts.get(key);
    }

    public boolean verifyPIN(int PIN, BaseAccount account) {
        return PIN == account.getPIN();
    }

    public BaseAccount createAccount(String type, int number) {
        int PIN = 0;
        double balance = 0;
        double interest_rate;
        double withdraw_amount;
        DateTime dateTime;
        double per_transaction_fee;
        int monthly_quota;
        int periodMonths;
        String inStr;

        AccountEnum accountType = AccountEnum.valueOf(type.toUpperCase());
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));
        BaseAccount account;

        makeMargin();
        try {
            System.out.println("ENTER PIN:");
            inStr = inputStream.readLine();
            PIN = Integer.valueOf(inStr);
        } catch(IOException e) {
            e.printStackTrace();
        }

        makeMargin();
        switch (accountType) {
            case OVERDRAFT:
                dateTime = new DateTime();
                interest_rate = 0;
                periodMonths = 0;

                try {
                    System.out.println("ENTER MONTHS IN PERIOD:");
                    inStr = inputStream.readLine();
                    periodMonths = Integer.valueOf(inStr);
                    interest_rate = periodMonths / 100.0;

                } catch(IOException e) {
                    e.printStackTrace();
                }
                account = new OverdraftAccount(balance, PIN, interest_rate, periodMonths, dateTime);

                break;
            case CHECKING:
                per_transaction_fee = 0;
                monthly_quota = 0;

                try {
                    System.out.println("ENTER MONTHLY QUOTA:");
                    inStr = inputStream.readLine();
                    monthly_quota = Integer.valueOf(inStr);
                    per_transaction_fee = monthly_quota * 100;

                } catch(IOException e) {
                    e.printStackTrace();
                }
                account = new CheckingAccount(balance, PIN, monthly_quota, per_transaction_fee);

                break;
            case SAVE:
                dateTime = new DateTime();
                interest_rate = 0;
                periodMonths = 0;

                try {
                    System.out.println("ENTER MONTHS IN PERIOD:");
                    inStr = inputStream.readLine();
                    periodMonths = Integer.valueOf(inStr);
                    interest_rate = periodMonths / 100.0;

                } catch(IOException e) {
                    e.printStackTrace();
                }
                account = new SaveAccount(balance, PIN, interest_rate, periodMonths, dateTime);

                break;
            case TIMED:
                dateTime = new DateTime();
                interest_rate = 0;
                periodMonths = 0;
                withdraw_amount = 0;

                try {
                    System.out.println("ENTER MONTHS IN PERIOD:");
                    inStr = inputStream.readLine();
                    periodMonths = Integer.valueOf(inStr);
                    interest_rate = periodMonths / 100.0;
                    withdraw_amount = (periodMonths + 1) / 100.0;

                } catch(IOException e) {
                    e.printStackTrace();
                }
                account = new TimedAccount(balance, PIN, interest_rate, periodMonths, dateTime, withdraw_amount);

                break;
            default:
                return null;
        }

        accounts.put(number, account);

        return account;
    }

    private void makeMargin() {
        System.out.println("**********************************************************");
    }
}
