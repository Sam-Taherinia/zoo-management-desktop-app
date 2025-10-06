package com.zoo.app.model.people;

import com.zoo.app.model.base.Showable;

public class ZooKeeper extends Person implements Showable {

    private String department;
    private String income;

    public ZooKeeper(String name, String id, String lastName, String username,
                     String password, String emailAddress, String phoneNumber,
                     String department, String income) {
        super(name, id, lastName, username, password, emailAddress, phoneNumber);
        this.department = department;
        this.income = income;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    @Override
    public void show() {
        // Implementation for showing zookeeper information
        System.out.println("ZooKeeper: " + getName() + " " + getLastName() + 
                          " (ID: " + getId() + ", Department: " + getDepartment() + ")");
    }
}
