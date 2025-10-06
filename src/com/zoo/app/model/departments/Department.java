package com.zoo.app.model.departments;

public abstract class Department {

    private String id;
    private String name;
    private String ticketPrice;
    private String zooKeepers;

    public Department(String id, String name, String ticketPrice, String zooKeepers) {
        this.id = id;
        this.name = name;
        this.ticketPrice = ticketPrice;
        this.zooKeepers = zooKeepers;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTicketPrice() {
        return ticketPrice;
    }

    public String getZooKeepers() {
        return zooKeepers;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTicketPrice(String ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setZooKeepers(String zooKeepers) {
        this.zooKeepers = zooKeepers;
    }
}
