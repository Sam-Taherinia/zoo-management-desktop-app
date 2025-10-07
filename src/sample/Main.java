package sample;


import Existings.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

import static Existings.ZOO.*;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Zoo Management App");
        primaryStage.setScene(new Scene(root, 1000, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) throws Exception {

//        writeSuperAdmin();
//
//        writeVisitors();
//        writeManagers();
//        writeZookeepers();
//
//        writeTickets();
//
//        writeAquatics();
//        writeWilds();
//        writeBirds();



        readSuperAdminInfo();

        readVisitors();
        readManager();
        readZookeepers();

        readAquatics();
        readBirds();
        readWilds();

        readTickets();

        launch(args);

    }

    public static void writeVisitors() throws IOException {
        String line;
        FileWriter fw = new FileWriter("src/IO files/Visitors.txt"); // TODO
        BufferedWriter bw = new BufferedWriter(fw);
        for (Visitor v : visitorsArrayList) {
            line = v.getName() + "     " + v.getID() + "     " + v.getLastName() + "     " + v.getUsername() + "     " + v.getPassword() + "     " + v.getEmailAddress()
                    + "     " + v.getPhoneNumber() + "     " + v.getMoney();
            bw.write(line);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        //System.out.println("All files Of Visitors have been Writen!!!");
    }

    public static void writeSuperAdmin() throws IOException {
        String line;
        FileWriter fw = new FileWriter("src/IO files/SuperAdmin.txt"); // TODO
        BufferedWriter bw = new BufferedWriter(fw);
            line = superadmin.getName() +
                    "     " +
                    superadmin.getID() +
                    "     " +
                    superadmin.getLastName() +
                    "     " +
                    superadmin.getUsername() +
                    "     " +
                    superadmin.getPassword() +
                    "     " +
                    superadmin.getEmailAddress() +
                    "     " +
                    superadmin.getPhoneNumber() +
                    "     " +
                    superadmin.getMoney();

            bw.write(line);
            bw.newLine();

        bw.flush();
        bw.close();
        //System.out.println("All files Of Visitors have been Writen!!!");
    }

    public static void writeManagers() throws IOException {
        String line;
        FileWriter fw = new FileWriter("src/IO files/Managers.txt"); // TODO
        BufferedWriter bw = new BufferedWriter(fw);
        for (Manager m : managersArrayList) {
            line = m.getName() +
                    "     " +
                    m.getID() +
                    "     " +
                    m.getLastName() +
                    "     " +
                    m.getUsername() +
                    "     " +
                    m.getPassword() +
                    "     " +
                    m.getEmailAddress() +
                    "     " +
                    m.getPhoneNumber();

            bw.write(line);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        //System.out.println("All files Of Managers have been Writen!!!");
    }

    public static void writeZookeepers() throws IOException {
        String line;
        FileWriter fw = new FileWriter("src/IO files/Zookeepers.txt"); // TODO
        BufferedWriter bw = new BufferedWriter(fw);
        for (ZooKeeper z : zookeepersArrayList) {
            line = z.getName() +
                    "     " +
                    z.getID() +
                    "     " +
                    z.getLastName() +
                    "     " +
                    z.getUsername() +
                    "     " +
                    z.getPassword() +
                    "     " +
                    z.getEmailAddress() +
                    "     " +
                    z.getPhoneNumber()+
                    "     " +
                    z.getDepartment()+
                    "     " +
                    z.getIncome();

            bw.write(line);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        //System.out.println("All files Of Visitors have been Writen!!!");
    }

    public static void writeTickets() throws IOException {
        String line;
        FileWriter fw = new FileWriter("src/IO files/Tickets.txt"); // TODO
        BufferedWriter bw = new BufferedWriter(fw);
        for (Ticket t : tickets) {

            line = t.getPrice() +
                    "     " +
                    t.getOwner() +
                    "     " +
                    t.getDepartment() +
                    "     " +
                    t.getLeft() ;

            bw.write(line);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        //System.out.println("All files Of Visitors have been Writen!!!");
    }

    public static void writeBirds() throws IOException {
        String line;
        FileWriter fw = new FileWriter("src/IO files/Birds.txt"); // TODO
        BufferedWriter bw = new BufferedWriter(fw);
        for (Bird b : birdsArrayList) {
            line = b.getName() +
                    "     " +
                    b.getID() +
                    "     " +
                    b.getGender() +
                    "     " +
                    b.getNationality() +
                    "     " +
                    b.getNationalNumber() +
                    "     " +
                    b.getInsuranceSituation() +
                    "     " +
                    b.getFoodType() +
                    "     " +
                    b.getDepartment();

            bw.write(line);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        //System.out.println("All files Of Birds have been Writen!!!");
    }

    public static void writeWilds() throws IOException {
        String line;
        FileWriter fw = new FileWriter("src/IO files/Wilds.txt"); // TODO
        BufferedWriter bw = new BufferedWriter(fw);
        for (Wild w : wildsArrayList) {
            line = w.getName() +
                    "     " +
                    w.getID() +
                    "     " +
                    w.getGender() +
                    "     " +
                    w.getNationality() +
                    "     " +
                    w.getNationalNumber() +
                    "     " +
                    w.getInsuranceSituation() +
                    "     " +
                    w.getFoodType() +
                    "     " +
                    w.getDepartment();

            bw.write(line);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        //System.out.println("All files Of Visitors have been Writen!!!");
    }

    public static void writeAquatics() throws IOException {
        String line;
        FileWriter fw = new FileWriter("src/IO files/Aquatics.txt"); // TODO
        BufferedWriter bw = new BufferedWriter(fw);
        for (Aquatic a : aquaticsArrayList) {

            line = a.getName() +
                    "     " +
                    a.getID() +
                    "     " +
                    a.getGender() +
                    "     " +
                    a.getNationality() +
                    "     " +
                    a.getNationalNumber() +
                    "     " +
                    a.getInsuranceSituation() +
                    "     " +
                    a.getFoodType() +
                    "     " +
                    a.getDepartment();

            bw.write(line);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        //System.out.println("All files Of Visitors have been Writen!!!");
    }








    public static void readVisitors() throws Exception {
        FileReader fr = new FileReader("src/IO files/Visitors.txt"); // TODO
        BufferedReader br = new BufferedReader(fr);
        String line, str[];

        while ((line = br.readLine()) != null) {

            str = line.split("     ");
            Visitor v = new Visitor(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7]);
            visitorsArrayList.add(v);
        }

    }

    public static void readManager() throws Exception {
        FileReader fr = new FileReader("src/IO files/Managers.txt"); // TODO
        BufferedReader br = new BufferedReader(fr);
        String line, str[];

        while ((line = br.readLine()) != null) {

            str = line.split("     ");
            Manager m = new Manager(str[0], str[1], str[2], str[3], str[4], str[5], str[6]);
            managersArrayList.add(m);
        }

    }

    public static void readZookeepers() throws Exception {
        FileReader fr = new FileReader("src/IO files/Zookeepers.txt"); // TODO
        BufferedReader br = new BufferedReader(fr);
        String line, str[];

        while ((line = br.readLine()) != null) {

            str = line.split("     ");
            ZooKeeper z = new ZooKeeper(str[0], str[1], str[2], str[3], str[4], str[5], str[6] , str[7] , str[8]);
            zookeepersArrayList.add(z);
        }

    }

    public static void readTickets() throws Exception {
        FileReader fr = new FileReader("src/IO files/Tickets.txt"); // TODO
        BufferedReader br = new BufferedReader(fr);
        String line, str[];

        while ((line = br.readLine()) != null) {

            str = line.split("     ");
            Ticket t = new Ticket(str[0], str[1], str[2] , str[4]);
            tickets.add(t);
        }

    }

    public static void readBirds() throws Exception {
        FileReader fr = new FileReader("src/IO files/Birds.txt"); // TODO
        BufferedReader br = new BufferedReader(fr);
        String line, str[];

        while ((line = br.readLine()) != null) {

            str = line.split("     ");
            Bird b = new Bird(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7]);
            birdsArrayList.add(b);
        }

    }

    public static void readAquatics() throws Exception {

        FileReader fr = new FileReader("src/IO files/Aquatics.txt"); // TODO
        BufferedReader br = new BufferedReader(fr);
        String line, str[];

        while ((line = br.readLine()) != null) {

            str = line.split("     ");
            Aquatic a = new Aquatic(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7]);
            aquaticsArrayList.add(a);
        }

    }

    public static void readWilds() throws Exception {
        FileReader fr = new FileReader("src/IO files/Wilds.txt"); // TODO
        BufferedReader br = new BufferedReader(fr);
        String line, str[];

        while ((line = br.readLine()) != null) {

            str = line.split("     ");
            Wild w = new Wild(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7]);
            wildsArrayList.add(w);
        }

    }

    public static void readSuperAdminInfo() throws Exception {
        FileReader fr = new FileReader("src/IO files/SuperAdmin.txt"); // TODO
        BufferedReader br = new BufferedReader(fr);
        String line, str[];

        while ((line = br.readLine()) != null) {

            str = line.split("     ");
            //SuperAdmin s = new SuperAdmin(str[0], str[1], str[2], str[3], str[4], str[5], str[6]);
            superadmin.setName(str[0]);
            superadmin.setID(str[1]);
            superadmin.setLastName(str[2]);
            superadmin.setUsername(str[3]);
            superadmin.setPassword(str[4]);
            superadmin.setEmailAddress(str[5]);
            superadmin.setPhoneNumber(str[6]);
            superadmin.setMoney(str[7]);

        }

    }

}
