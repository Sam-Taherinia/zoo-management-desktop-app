//package sample;
//
//import javax.swing.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//public class dbConnection {
//
//    private static final String conn = "jdbc:sqlite:info.sqlite";
//
//    public static Connection getCon() throws Exception{
//
//        try{
//
//            Class.forName("org.sqlite.JDBC");
//            Connection con = DriverManager.getConnection("jdbc:sqlite:info.sqlite") ;
//            //JOptionPane.showMessageDialog(null , "connecting done") ;
//            return con ;
//
//        }catch (ClassNotFoundException e){
//
//            e.printStackTrace();
//
//        }
//
//        return null ;
//    }
//
//}
