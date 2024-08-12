package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import bank.exceptions.AmountException;

public class Menu {

  Scanner scanner;

  public static void main(String[] args) {
    //welcome message...
    System.out.println("Welcome to Globe Bank International");

    //Instanciate new menu and new scanner(based on the menu object)...
    Menu menu = new Menu();
    menu.scanner = new Scanner(System.in);

    //authenticateUser
    Customer customer = menu.authenticateUser();

    //get account and show menu
    if(customer != null){
      
      Account account = DataSource.getAccount(customer.getAccountid());
      menu.showMenu(customer, account);

    }




    menu.scanner.close();

  }

  Customer authenticateUser(){
    Customer customer = null;

    //get username and password from user...
    System.out.println("Please enter username :");
    String username = scanner.next();

    System.out.println("Please enter password :");
    String password = scanner.next();

    try {
      customer = Authenticator.login(username, password);
      
    } catch (LoginException e) {
      System.out.println("There was an error :" + e.getMessage());
    }


    return customer;
  }

  void showMenu(Customer customer, Account account){
     
    int selection = 0;

    while (selection != 4 && customer.isAuthenticated()) {
      //Options...
      System.out.println("============================================");
      System.out.println("Please select one of the following options :");
      System.out.println("1 : Deposit.");
      System.out.println("2 : Withdraw.");
      System.out.println("3 : Check balance.");
      System.out.println("4 : Exit.");
      System.out.println("============================================");

      //get user selection input...
      selection = scanner.nextInt();
      double amount = 0;

      switch (selection) {
        case 1:
        System.out.println("How much would you like to deposit ?");
        amount = scanner.nextDouble();
        try {
           account.deposit(amount);  
        } catch (AmountException e) {
           System.out.println(e.getMessage());
        }
        
          
        break;

        case 2:
        System.out.println("How much would you like to withdraw ?");
        amount = scanner.nextDouble();
        account.withdraw(amount);
        break;

        case 3:
        System.out.println("Current balance : " + account.getBalance());
        break;

        case 4:
        Authenticator.logout(customer);
        System.out.println("Thanks for banking at Globe bank international!");
        break;
      
        default:
          System.out.println("Invalid option. Please try again!");
          break;
      }
 
      
    }

  }

}
