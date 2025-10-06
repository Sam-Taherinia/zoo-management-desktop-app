package Existings;


import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ZOO {

    public static ArrayList <Manager> managersArrayList = new ArrayList<>() ;
    public static ArrayList <Visitor> visitorsArrayList = new ArrayList<>() ;
    public static ArrayList <ZooKeeper> zookeepersArrayList = new ArrayList<>() ;

    public static SuperAdmin superadmin = new SuperAdmin("meysam","13","taherinia","Admin","2018166324","meysamtaherinia@yahoo.com","921 604 3785","13000") ;



    public static ArrayList <Bird> birdsArrayList = new ArrayList<>() ;
    public static ArrayList <Aquatic> aquaticsArrayList = new ArrayList<>() ;
    public static ArrayList <Wild> wildsArrayList = new ArrayList<>() ;
    public static ArrayList <Ticket> tickets = new ArrayList<>() ;
    public static int ID = 14 ;

    public void LoadFile(){

        //...

    }

    public static String HashIt(String Password) {

        return Integer.toString(Password.hashCode());

    }

}


