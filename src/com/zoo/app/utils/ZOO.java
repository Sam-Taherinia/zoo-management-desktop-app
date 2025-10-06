package com.zoo.app.utils;

import com.zoo.app.model.animals.Aquatic;
import com.zoo.app.model.animals.Bird;
import com.zoo.app.model.animals.Wild;
import com.zoo.app.model.base.Ticket;
import com.zoo.app.model.people.Manager;
import com.zoo.app.model.people.SuperAdmin;
import com.zoo.app.model.people.Visitor;
import com.zoo.app.model.people.ZooKeeper;

import java.util.ArrayList;

public class ZOO {

    public static ArrayList<Manager> managersArrayList = new ArrayList<>();
    public static ArrayList<Visitor> visitorsArrayList = new ArrayList<>();
    public static ArrayList<ZooKeeper> zookeepersArrayList = new ArrayList<>();

    public static SuperAdmin superadmin = new SuperAdmin("meysam", "13", "taherinia", "Admin", 
                                                          "2018166324", "meysamtaherinia76@gmail.com",
                                                          "921 604 3785", "13000");

    public static ArrayList<Bird> birdsArrayList = new ArrayList<>();
    public static ArrayList<Aquatic> aquaticsArrayList = new ArrayList<>();
    public static ArrayList<Wild> wildsArrayList = new ArrayList<>();
    public static ArrayList<Ticket> tickets = new ArrayList<>();
    public static int ID = 14;

    public void loadFile() {
        // File loading functionality
    }

    public static String hashIt(String password) {
        return Integer.toString(password.hashCode());
    }
}
