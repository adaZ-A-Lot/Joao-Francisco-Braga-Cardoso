package ch.makery.address.model.db;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.model.PersonListWrapper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import javafx.scene.control.Alert;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
/*
 * @author João Francisco
 * @version 1.0
 */
public class DataDB {

    public void savePersonDataToBD(PersonListWrapper wrapper) {
        
        
        try{
            List<Person> persons = wrapper.getPersons();
            Person person;
            DBConnection.deleteBD();
            DBConnection.createBD();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:"
                    + "3306/agenda","root","root");
            Statement stmt=con.createStatement();
            
            
            
            for (int i = 0; i < persons.size(); i++) {
                
                
                person=persons.get(i);
                stmt.executeQuery("INSERT INTO agenda (firstName, lastName,"
                        + " street, postalCode, city, birthday) VALUES ("+
                        person.getFirstName()+", "+person.getLastName()+", "+ 
                        person.getStreet()+", "+person.getPostalCode()+", " + 
                        person.getCity()+", "+person.getBirthday()+");"); 
            }
            
            con.close();
            
        } 
        
        catch (Exception e) { 
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Não foi possivel salvar os dados no BD\n");

                alert.showAndWait();
        }
    }
}
