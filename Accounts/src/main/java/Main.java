import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Class name: Main
 * User: User
 * Date: 09.11.13
 * Time: 13:30
 */
public class Main {
    public final static int PAGE_SIZE = 25;

    public static void main(String[] args) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        SecuritySystem system = new SecuritySystem();
        BaseAccount account;
        int number;
        int PIN;
        int sum;

        // VERIFY ACCOUNT

        while (true) {
            makeMargin();
            System.out.println("ENTER ACCOUNT NUMBER (0 FOR EXIT):");
            number = getIntegerFromConsole();

            if(number == 0) {
                break;
            }

            account = system.getAccount(number);
            if(account == null) {
                makeMargin();

                System.out.println("THERE IS NO SUCH ACCOUNT!");
                System.out.println("DO YOU WANT TO CREATE NEW ACCOUNT WITH NUMBER " + number + " ?");
                System.out.println("0.NO\n1.YES");

                int selection = getIntegerFromConsole();
                switch (selection) {
                    case 0:
                        break;
                    case 1:
                        makeMargin();
                        String accountType = getAccountType();
                        account = system.createAccount(accountType, number);
                        makeMargin();
                        System.out.println("ACCOUNT CREATED");
                        break;
                    default:
                        System.out.println("ENTER CORRECT NUMBER!");
                }

                if(account == null) {
                    continue;
                }
            }

            // VERIFY PIN

            while(true) {
                makeMargin();

                System.out.println("ENTER PIN:");
                PIN = getIntegerFromConsole();
                if(!system.verifyPIN(PIN, account)) {
                    System.out.println("ERROR! PIN IS NOT CORRECT!");
                    continue;
                } else {
                    break;
                }
            }

            // MAKE SOME OPERATIONS IN ACCOUNT

            while(true) {
                makeMargin();

                System.out.println("SELECT OPERATION ON ACCOUNT:");
                System.out.println("1.ADD MONEY");
                System.out.println("2.GET MONEY");
                System.out.println("3.CHECK BALANCE");
                System.out.println("0.LOG OUT ACCOUNT");

                number = getIntegerFromConsole();

                switch (number) {
                    case 1:
                        System.out.println("ENTER SUM:");
                        sum = getIntegerFromConsole();
                        account.addMoney(sum);

                        makeMargin();
                        System.out.println("OPERATION IS COMPLETED!");
                        System.out.println("SUM IS ADDED: " + formatter.format(sum));
                        System.out.println("PREVIOUS BALANCE: " + formatter.format(account.checkBalance() - sum));
                        System.out.println("CURRENT BALANCE: " + formatter.format(account.checkBalance()));

                        break;
                    case 2:
                        System.out.println("ENTER SUM:");
                        sum = getIntegerFromConsole();

                        makeMargin();
                        if(account.checkBalance() < sum) {
                            System.out.println("OPERATION IS FAILED!");
                            System.out.println("YOU DON'T HAVE ENOUGH MONEY TO GET SUCH SUM!");
                        } else {
                            account.getMoney(sum);
                            System.out.println("OPERATION IS COMPLETED!");
                            System.out.println("SUM IS WITHDRAWN: " + formatter.format(sum));
                            System.out.println("PREVIOUS BALANCE: " + formatter.format(account.checkBalance() + sum));
                            System.out.println("CURRENT BALANCE: " + formatter.format(account.checkBalance()));
                        }

                        break;
                    case 3:
                        makeMargin();
                        System.out.println("OPERATION IS COMPLETED!");
                        System.out.println("CURRENT BALANCE: " + formatter.format(account.checkBalance()));

                        break;
                    case 0:
                        makeMargin();
                        System.out.println("YOU LOGGED OUT ACCOUNT!");
                        break;
                }

                if(number == 0) {
                    break;
                }
            }
        }
    }

    private static int getIntegerFromConsole() {
        int number = 0;
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(System.in));

        String inStr;
        try {
            inStr = inputStream.readLine();
            number = Integer.valueOf(inStr);
        } catch(IOException e) {
            e.printStackTrace();
        }

        return number;
    }

    private static String getAccountType() {
        int result;
        String[] accountTypes = {"CHECKING", "OVERDRAFT", "SAVE", "TIMED"};

        System.out.println("SELECT ACCOUNT TYPE:");
        for (int i = 0; i < accountTypes.length; i++) {
            System.out.println((i + 1) + ". " + accountTypes[i]);
        }
        result = getIntegerFromConsole();

        return accountTypes[(result - 1)];
    }

    private static void makeMargin() {
        System.out.println("**********************************************************");
    }
}
