import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    Scanner keyboard = new Scanner(System.in);
    Bank bank = new Bank();
    boolean exit;

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.runMenu();
    }

    public void runMenu(){
        printHeader();
        while(!exit) {
            printMenu();
            int choice = getInput();
            performAction(choice);

        }
    }

    private void printMenu() {
        System.out.println("Please make a selection: ");
        System.out.println("1) Create an Account");
        System.out.println("2) Make a deposit; ");
        System.out.println("3) Make a withdrawal; ");
        System.out.println("4) List account balance; ");
        System.out.println("0) Exit");
    }

    private void printHeader() {
        System.out.println("+------------------------------------------------------+");
        System.out.println("|             Welcome to futjiapples                   |");
        System.out.println("|               Banking Application                    |");
        System.out.println("+------------------------------------------------------+");

    }

    private void performAction(int choice) {
     switch(choice) {
            case 0:
                 System.out.print("Thank you for using our application.");
                 System.exit(0);
                 break;
            case 1:
                createAnAccount();
                break;
            case 2:
                makeADeposit();
                break;
            case 3:
                makeAWithdrawal();
                break;
            case 4:
                listBalances();
                break;
            default:
                 System.out.print("Unknown error has occured");
                
        }





    }

    private void listBalances() {
       int account = selectAccount();
       if (account >= 0) {
           System.out.println(bank.getCustomer(account).getAccount());
       }
    }

    private void makeAWithdrawal() {
        int account = selectAccount();
        if (account >= 0) {
            System.out.println("How much would you like to withdraw? ");
            double amount = 0;
            try {
                amount = Double.parseDouble(keyboard.nextLine());

            }catch(NumberFormatException e) {
                amount = 0;
            }
            bank.getCustomer(account).getAccount().withdraw(amount);
        }
        
    }

    private void makeADeposit() {
        int account = selectAccount();
        if (account >= 0) {
            System.out.println("How much would you like to deposit?: ");
            double amount = 0;
            try {
                amount = Double.parseDouble(keyboard.nextLine());
            } catch (NumberFormatException e) {
                amount = 0;
            }
            bank.getCustomer(account).getAccount().deposit(amount);
        }
    }

    // private void makeATransfer() {
        //  int account = selectAccount();
        // if (account >= 0) {
    // System.out.println("How much would you like to transfer?: ");
    // double amount = 0;
    //  try {
    //     amount = Double.parseDouble(keyboard.nextLine());
    // } catch (NumberFormatException e) {
        //      amount 0;
    // }

    // }

    //}


    private int selectAccount() {
        ArrayList<Customer> customers = bank.getCustomers();
        if(customers.size() <= 0) {
            System.out.println("No customers at your bank. ");
            return -1;
        }
        
        System.out.println("Select an account:");
        for(int i = 0; i < customers.size(); i++) {
            System.out.println((i+1) + ") " + customers.get(i).basicInfo());
        }

        int account = 0;
        System.out.print("Please enter your selection: ");
        try{
            account = Integer.parseInt(keyboard.nextLine()) -1;
        }
        catch(NumberFormatException e){
            account = -1;
            
        }
        if(account < 0 || account > customers.size()) {
             System.out.println("Invalid account selected.");
            account = -1;
        }
        return account;
    }

    private void createAnAccount(){
        String firstName, lastName, cprNr, accountType = "";
        double initialDeposit = 0;
        boolean valid = false;
        while(!valid){
             System.out.print("Please enter an account type(checking/savings):  ");
             accountType = keyboard.nextLine();
             if(accountType.equalsIgnoreCase("checking") || accountType.equalsIgnoreCase("savings")){
                 valid = true;
             }
             else {
                  System.out.println("Invalid account type selected. Please enter checking or savings.");
             }
        }
         System.out.print("Please enter your first name: ");
         firstName = keyboard.nextLine();
         System.out.println("Please enter your last name: ");
         lastName = keyboard.nextLine();
         System.out.println("Please enter your CPR-number: ");
         cprNr = keyboard.nextLine();
         valid = false;
         while(!valid)    {
                 System.out.println("Please enter an initial deposit: ");
                 try {
                     initialDeposit = Double.parseDouble(keyboard.nextLine())   ;
                 }
                 catch (NumberFormatException e)  {
                      System.out.println("Deposit must be a number.");
                 }
                 if(accountType.equalsIgnoreCase("checking")){
                     if(initialDeposit < 100) {
                          System.out.println("Checking accounts require a minimum of $100 dollars to open");
                        }  else {
                                valid = true;
                     }
                 }   else if(accountType.equalsIgnoreCase("savings")) {
                     if(initialDeposit < 50) {
                          System.out.println("Savings accounts require a minimum of $50 dollars to open");
                     } else {
                         valid = true;
                     }
                 }
         }
              //We can create an account now;
        Account account;
         if(accountType.equalsIgnoreCase("checking")){
             account = new Checking(initialDeposit);
         }  else {
             account = new Savings(initialDeposit);
         }
         Customer customer = new Customer(firstName, lastName, cprNr, account);
         bank.addCustomer(customer);

    }

    private int getInput() {
        int choice = -1;
        do {
            System.out.println("Enter your choice: ");
            try {
                choice = Integer.parseInt(keyboard.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid selection. Numbers only");
            }
            if (choice < 0 || choice > 4) {
                System.out.print("Choice outside of range. Please choice again.");
            }
        } while (choice < 0 || choice > 4);
        return choice;
    }





}
