package com.zoo.app.model.people;

import com.zoo.app.model.base.Living;

public abstract class Person extends Living {

    private String lastName;
    private String username;
    private String password;
    private String emailAddress;
    private String phoneNumber;

    public Person(String name, String id, String lastName, String username, 
                  String password, String emailAddress, String phoneNumber) {
        super(name, id);
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
