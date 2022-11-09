/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package routinPackage;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author admin
 */
public class myConnection {
    public static Connection getConnection(){
        Connection con = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
                        con = DriverManager.getConnection("jdbc:mysql://192.168.1.2/routing_db", "sampleuser", "123123");
//                         con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/routing_db", "sampleuser", "123123");

//11/09/2022 
        } catch (Exception e){
            e.printStackTrace();
             System.out.println("couldn't connect!");
              throw new RuntimeException(e);
        }
        return con;
        
        
    }
}
