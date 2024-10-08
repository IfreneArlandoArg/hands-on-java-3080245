package bank;

import bank.exceptions.AmountException;

public class Account {
  int id;
  String type;
  double balance;

  public Account(int id, String type, double balance){
    setId(id);
    setType(type);
    setBalance(balance);
  }

  public void deposit(double amount) throws AmountException{
    if(amount < 1)
       throw new AmountException("The minimum deposit is 1.00 US$.");

    balance += amount;

    DataSource.updateAccountBalance(id, balance);
  }
  
  public void withdraw(double amount) throws AmountException {

    //Exception if amount negative...
    if(amount < 0)
      throw new AmountException("The withdrawal amount must be greater than 0.");
    //Exception if insufficient funds...
    if (amount > balance)
        throw new AmountException("Insufficient funds!!!");
        
    balance -= amount;

    DataSource.updateAccountBalance(id, balance);

  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getBalance() {
    return this.balance;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }


}
