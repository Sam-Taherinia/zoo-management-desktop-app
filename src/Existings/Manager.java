package Existings;

import java.io.Serializable;

public class Manager extends Person implements Serializable {
    public Manager(String Name, String ID, String LastName, String username, String password, String EmailAddress, String PhoneNumber) {
        super(Name, ID, LastName, username, password, EmailAddress, PhoneNumber);
    }
}
