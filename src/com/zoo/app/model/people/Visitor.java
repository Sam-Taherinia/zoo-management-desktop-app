package com.zoo.app.model.people;

import com.zoo.app.model.base.Showable;

public class Visitor extends Person implements Showable {

    private String money;

    public Visitor(String name, String id, String lastName, String username,
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

    @Override
    public void show() {
        // Implementation for showing visitor information
        System.out.println("Visitor: " + getName() + " " + getLastName() + " (ID: " + getId() + ")");
    }
}
