package com.company;
import java.util.Scanner;

public class MenuBuilder {

        public void run() {


        int userChoice;


        do {
            userChoice = MenuBuilder.startMenu();

            switch (userChoice) {

                case AppConstants.EMPLOYEE:
                    String pin = getBankEmployeePin();
                    SecurityBanking sb = new SecurityBanking();
                    boolean isAuthenticated = sb.login(pin);

                    if (isAuthenticated) {
                        int bankEmployeeAction;

                        do {
                            bankEmployeeAction = MenuBuilder.bankingMenu();

                            switch (bankEmployeeAction) {
                                case AppConstants.CUSTOMER_CREATE:
                                    BankCustomer newCustomer = addCustomerForm();
                                    BankingAction.addCustomer(newCustomer);
                                    MenuBuilder.bankingMenu();
                                    break;
                                case AppConstants.CUSTOMER_DELETE:
                                    DeleteCustomer();
                                    break;
                                case AppConstants.TRANSACTION_CREATE:

                                    int transactionChoice = 0;

                                    do {
                                        transactionChoice = MenuBuilder.transactionMenu();

                                        switch (transactionChoice) {
                                            case AppConstants.TRANSACTION_LODGE:
                                                BankingTransaction btl = createTransactionLodgeForm();
                                                BankingAction.createTransaction(btl);
                                                MenuBuilder.bankingMenu();
                                                break;
                                            case AppConstants.TRANSACTION_WITHDRAW:
                                                BankingTransaction btw = createTransactionWithdrawForm();
                                                BankingAction.createTransaction(btw);
                                                MenuBuilder.bankingMenu();
                                                break;
                                            default:
                                                System.out.println("Transaction choice invalid!");
                                        }

                                    }
                                    while (transactionChoice != 2);

                                    break;

                                case AppConstants.CUSTOMER_LIST:
                                    BankingAction.listCustomersBalance();

                                    break;
                                case AppConstants.QUIT:
                                    quit();
                                    break;
                                default:
                                    System.out.println("Transaction Choice is invalid!");
                            }

                        }
                        while (bankEmployeeAction != 5);

                    } else {
                        System.out.println("Authentication error");

                    }

                    break;

                case AppConstants.CUSTOMER:


                    SecurityCustomer sc = new SecurityCustomer();
                    String[] credentials = getCustomerCredentials();

                    boolean isCustomerAuthenticated = sc.getCredentials(credentials[0], credentials[1], credentials[2], credentials[3]);

                    if (isCustomerAuthenticated) {
                        BankCustomer c = FileManager.getCustomer(credentials[2]);

                        int customerAction;

                        do {
                            customerAction = MenuBuilder.customerMenu();

                            switch (customerAction) {
                                case AppConstants.TRANSACTION_LODGE:
                                    BankingTransaction btl = createTransactionLodgeForm(c);
                                    BankingAction.createTransaction(btl);
                                    break;
                                case AppConstants.TRANSACTION_WITHDRAW:
                                    BankingTransaction btw = createTransactionWithdrawForm(c);
                                    BankingAction.createTransaction(btw);
                                    break;
                                case AppConstants.TRANSACTION_LIST:
                                    int type = createTransactionListForm(c);
                                    BankingAction.listOfTransactionHistory(c.getCustomerAccount(), type);
                                    break;
                                case AppConstants.QUIT:
                                    startMenu();
                                    break;
                                default:
                                    System.out.println("Action is invalid!");
                            }
                        }
                        while (customerAction != 5);

                    } else {
                        System.out.println("error....");
                        return;
                    }

                    break;

                case 3:
                    quit();
                    break;
                case 4:
                    return;
            }

        }
        while (userChoice != 5);

    }


    private static int startMenu() {

        int selection;
        Scanner input = new Scanner(System.in);
        /***************************************************/
        System.out.println("-------------------------\n");
        System.out.println("Welcome to JAVABANK!");
        System.out.println("Choose from these choices below:");
        System.out.println("-------------------------\n");

        System.out.println("1 - Bank Employee Login");
        System.out.println("2 - Customer Login");
        System.out.println("3 - Quit");

        selection = input.nextInt();
        return selection;

    }

    private static int bankingMenu() {

        int selection;
        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------------------\n");
        System.out.println("1 - Add Customer");
        System.out.println("2 - Delete Customer");
        System.out.println("3 - Create Transaction");
        System.out.println("4 - List customers");
        System.out.println("5 - Quit");

        selection = scanner.nextInt();
        return selection;

    }

    private static String getBankEmployeePin() {

        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter Pin");

        String pin = myObj.nextLine();

        return pin;
    }

    private static int customerMenu() {

        int selection;
        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------------------\n");

        System.out.println("1 - Lodgement");
        System.out.println("2 - Withdraw");
        System.out.println("3 - List transactions");
        System.out.println("4 - Quit");

        selection = scanner.nextInt();
        return selection;
    }

    private static String[] getCustomerCredentials() {

        String v[] = new String[4];
        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter first name");

        v[0] = myObj.nextLine(); //first name
        System.out.println("Enter last name");
        v[1] = myObj.nextLine(); //last name
        System.out.println("Enter bank account");
        v[2] = myObj.nextLine(); //account name
        System.out.println("Enter PIN");
        v[3] = myObj.nextLine(); //pin name

        return v;
    }

    private static int transactionMenu() {

        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("-------------------------\n");
        System.out.println("1 - Lodge Money");
        System.out.println("2 - Withdraw Money");


        selection = input.nextInt();
        return selection;

    }

    private static BankCustomer addCustomerForm() {

        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter first name:");
        String firstName = myObj.nextLine();
        System.out.println();

        System.out.println("Enter last name:");
        String lastName = myObj.nextLine();
        System.out.println();

        System.out.println("Enter e-mail:");
        String eMail = myObj.nextLine();
        System.out.println();


        BankCustomer customer = new BankCustomer(firstName, lastName, eMail);

        FileManager.AddCustomerToFile(customer);

        return customer;

    }

    private static int createTransactionListForm(BankCustomer c) {

        System.out.println("Customer Transaction list");

        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter the type account (1 - Current / 2 - Savings)");
        int typeAccount = myObj.nextInt();


        return typeAccount;

    }

    private static BankingTransaction createTransactionLodgeForm() {

        System.out.println("Transaction lodge form goes here");

        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter the account:");
        String account = myObj.nextLine();

        System.out.println("Enter the amount:");
        double amount = myObj.nextDouble();

        System.out.println("Enter the type account (1 - Current / 2 - Savings)");
        int typeAccount = myObj.nextInt();

        BankCustomer c = FileManager.getCustomer(account);

        if (c == null)
        {
            System.out.println("Customer account: "+ account +" doesn't exist");
            return null;
        }

        System.out.println("Transaction lodge - account: "+ account +" amount: "+ amount +" typeAccount: "+ typeAccount +" ");


        BankingTransactionLodge btl = new BankingTransactionLodge(c, amount, typeAccount);

        return btl;

    }

    private static BankingTransaction createTransactionLodgeForm(BankCustomer c) {

        System.out.println("Customer Transaction lodge form goes here");

        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter the amount:");
        double amount = myObj.nextDouble();

        System.out.println("Enter the type account (1 - Current / 2 - Savings)");
        int typeAccount = myObj.nextInt();

        System.out.println("Transaction lodge - account: "+ c.getCustomerAccount() +" amount: "+ amount +" typeAccount: "+ typeAccount +" ");

        BankingTransactionLodge btl = new BankingTransactionLodge(c, amount, typeAccount);

        return btl;

    }

    private static BankingTransaction createTransactionWithdrawForm() {

        System.out.println("Transaction lodge form goes here");

        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter the account:");
        String account = myObj.nextLine();

        System.out.println("Enter the amount:");

        double amount = myObj.nextDouble();

        System.out.println("Enter the type account (1 - Current / 2 - Savings)");
        int typeAccount = myObj.nextInt();

        System.out.println("Transaction lodge amount: "+ amount +" typeAccount: "+ typeAccount +" ");

        BankCustomer c = FileManager.getCustomer(account);

        if (c == null)
        {
            System.out.println("Customer account: "+ account +" doesn't exist");
            return null;
        }

        BankingTransactionWithdraw btw = new BankingTransactionWithdraw(c, amount, typeAccount);

        return btw;

    }

    private static BankingTransaction createTransactionWithdrawForm(BankCustomer c) {

        System.out.println("customer Transaction lodge form goes here");

        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter the amount:");
        double amount = myObj.nextDouble();

        System.out.println("Enter the type account (1 - Current / 2 - Savings)");
        int typeAccount = myObj.nextInt();

        System.out.println("Transaction lodge amount: "+ c.getCustomerAccount() +" typeAccount: "+ typeAccount +" ");

        BankingTransactionWithdraw btw = new BankingTransactionWithdraw(c, amount, typeAccount);

        return btw;

    }


    private static void DeleteCustomer() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("--------------------------\n");
        System.out.println("Type account number");

        String account = scanner.nextLine();

    }

    private static void quit(){
        System.out.println("Thank you for using JavaBank");
        System.exit(0);
    }


}