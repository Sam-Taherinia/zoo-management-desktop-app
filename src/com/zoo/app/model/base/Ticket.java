package com.zoo.app.model.base;

public class Ticket {

    private String price;
    private String owner;
    private String department;
    private String left;

    public Ticket(String price, String owner, String department, String left) {
        this.price = price;
        this.owner = owner;
        this.department = department;
        this.left = left;
    }

    public String getPrice() {
        return price;
    }

    public String getOwner() {
        return owner;
    }

    public String getDepartment() {
        return department;
    }

    public String getLeft() {
        return left;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setLeft(String left) {
        this.left = left;
    }
}
