package Existings;


public class Visitor extends Person implements Showable {


    private String money ;
    //ticket info


    public Visitor(String Name, String ID, String LastName, String username, String password, String EmailAddress, String PhoneNumber, String money) {
        super(Name, ID, LastName, username, password, EmailAddress, PhoneNumber);
        this.money = money ;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }



}
