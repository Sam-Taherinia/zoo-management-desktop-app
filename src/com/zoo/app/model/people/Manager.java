package com.zoo.app.model.people;

import java.io.Serializable;

public class Manager extends Person implements Serializable {
    
    public Manager(String name, String id, String lastName, String username,
                   String password, String emailAddress, String phoneNumber) {
        super(name, id, lastName, username, password, emailAddress, phoneNumber);
    }
}
