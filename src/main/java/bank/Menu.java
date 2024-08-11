package bank;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

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
     

  }

}
