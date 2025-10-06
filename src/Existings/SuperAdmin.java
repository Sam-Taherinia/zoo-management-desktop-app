package Existings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SuperAdmin extends Person implements Showable {

    private String Money ;

    public SuperAdmin(String Name, String ID, String LastName, String username, String password, String EmailAddress, String PhoneNumber , String Money) {
        super(Name, ID, LastName, username, password, EmailAddress, PhoneNumber);
        this.Money = Money ;
    }


    public String getMoney() {
        return Money;
    }

    public void setMoney(String money) {
        Money = money;
    }

    public void addMoney(String money) {
        Money += money;
    }


}
