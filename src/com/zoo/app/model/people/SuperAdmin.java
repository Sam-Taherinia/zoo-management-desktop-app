package com.zoo.app.model.people;

import com.zoo.app.model.base.Showable;

public class SuperAdmin extends Person implements Showable {

    private String money;

    public SuperAdmin(String name, String id, String lastName, String username,
                      String password, String emailAddress, String phoneNumber, String money) {
        super(name, id, lastName, username, password, emailAddress, phoneNumber);
        this.money = money;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public void addMoney(String money) {
        // Note: This is a string concatenation, might need to be changed to numeric operation
        this.money += money;
    }

    @Override
    public void show() {
        // Implementation for showing super admin information
        System.out.println("SuperAdmin: " + getName() + " " + getLastName() + " (ID: " + getId() + ")");
    }
}
