package ch.makery.address.model.db;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.control.Alert;

/*
 * @author João Francisco
 * @version 1.0
 */
public class DBConnection {
    
    
    public static void createBD() {
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:"
                    + "3306","root","root");
            Statement stmt=con.createStatement();
            stmt.executeQuery("CREATE DATABASE agenda;\nUSE agenda;\n"
                    + "CREATE TABLE pessoa(id int, firstName varchar(20), "
                    + "lastName varchar(40), street varchar(50), "
                    + "postalCode int(8), city varchar(20), birthday date;");   
            con.close();
            
        }
        
        catch(Exception e){ 
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Não foi possível criar o BD");

                alert.showAndWait();
        }
    }
    
    public static void deleteBD() {
       
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:"
                    + "3306/agenda","root","root");
            Statement stmt=con.createStatement();
            stmt.executeQuery("DROP DATABASE agenda;");   
            con.close();
            
            
        }
        
        
        catch(Exception e){ 
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Não foi possível deletar o BD");

                alert.showAndWait();
        }
    }
}
