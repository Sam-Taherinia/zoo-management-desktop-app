//package sample;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class LoginAuth {
//
//    Connection conn ;
//
//    public LoginAuth(){
//
//        try{
//
//            this.conn = dbConnection.getCon();
//
//        } catch (SQLException e){
//
//            e.printStackTrace();
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//        }
//
//        if (this.conn == null){
//
//            System.exit(1);
//
//        }
//
//    }
//
//    public boolean checkConnection(){
//
//        return this.conn != null ;
//
//    }
//
//    public boolean checkUser (String username , String password) throws Exception {
//
//        PreparedStatement ps = null ;
//        ResultSet rs = null ;
//
//        String query = " select * from users where username = ? And password = ? ;" ;
//
//        try {
//
//            ps = conn.prepareStatement(query);
//            ps.setString(1,username);
//            ps.setString(2,password);
//
//            rs = ps.executeQuery();
//
//            if (rs.next()){
//
//                //JOptionPane.showMessageDialog(null , "connecting done");
//                return true;
//
//            }
//
//            return false ;
//
//
//        }catch (SQLException e){
//
//            e.printStackTrace();
//
//        }finally {
//
//            ps.close();
//            rs.close();
//
//        }
//
//        return false;
//
//    }
//
//}
