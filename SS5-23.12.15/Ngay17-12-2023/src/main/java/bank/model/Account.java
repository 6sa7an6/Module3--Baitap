package bank.model;

public class Account {
    int id;
    int account_holder_id;
    double balance ;

    public Account() {
    }

    public Account(int id, int account_holder_id, double balance) {
        this.id = id;
        this.account_holder_id = account_holder_id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount_holder_id() {
        return account_holder_id;
    }

    public void setAccount_holder_id(int account_holder_id) {
        this.account_holder_id = account_holder_id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", account_holder_id=" + account_holder_id +
                ", balance=" + balance +
                '}';
    }
}
