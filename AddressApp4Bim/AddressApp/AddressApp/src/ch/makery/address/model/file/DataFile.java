/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template arquivo, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.address.model.file;

import ch.makery.address.MainApp;
import ch.makery.address.model.PersonListWrapper;
import java.io.File;
import javafx.scene.control.Alert;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Classe para arquivos
 * @author João Francisco
 * @version 2.0
 */
public class DataFile {
    private FilePath filePath;
    
    public DataFile(){
        filePath = new FilePath();
    }
    
    
    public FilePath getFilePath(){
        return filePath;
    }
    
    /**
     * Carrega os dados de uma pessoa específica do arquivo
     * @param file
     */
    public void loadPersonDataFromFile(File file) {
       
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);
            MainApp.getInstance().getPersonData().clear();
            MainApp.getInstance().getPersonData().addAll(wrapper.getPersons());
            filePath.setFilePath(file);

        } 
        
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText("Could not save data");
             alert.setContentText("Could not save data to file:\n" + file.getPath());

             alert.showAndWait();
        }
    }

    public void savePersonDataToFile(File file) {
       
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(MainApp.getInstance().getPersonData());

            m.marshal(wrapper, file);

            filePath.setFilePath(file);
        } catch (Exception e) { 
            Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setHeaderText("Could not save data");
             alert.setContentText("Could not save data to file:\n" + file.getPath());

             alert.showAndWait();
        }
    }
}