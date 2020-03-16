/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {    
    
    public Connection connect(){
        Connection connection=null; 
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tur_acentes?user=root&password=123");  
        
        } catch( SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e){
            System.out.println(e.getMessage());
        }  
        
        return connection;
    }    
}
