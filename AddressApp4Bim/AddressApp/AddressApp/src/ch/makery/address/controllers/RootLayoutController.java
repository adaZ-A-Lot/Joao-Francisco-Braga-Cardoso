package ch.makery.address.controllers;

import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import ch.makery.address.MainApp;
import ch.makery.address.model.PersonListWrapper;
import ch.makery.address.model.db.DataDB;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * @author João Francisco
 * @version 2.0
 */
public class RootLayoutController {

    /*
     * Abre uma janela para mostrar as estatísticas de aniversário.
     */
    public void showBirthdayStatistics() {
        
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Birthday Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(MainApp.getInstance().getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            BirthdayStatisticsController controller = loader.getController();
            controller.setPersonDataToBirthdayStatistics(MainApp.getInstance().getPersonData());

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    @FXML
    private void handleNew() {
        MainApp.getInstance().getPersonData().clear();
        MainApp.getInstance().getPersonDataFile().getFilePath().setFilePath(null);
    }

   
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(MainApp.getInstance().getPrimaryStage());

        if (file != null) {
            MainApp.getInstance().getPersonDataFile().loadPersonDataFromFile(file);
        }
    }
   
    @FXML
    private void handleSave() {
        File personFile =  MainApp.getInstance().getPersonDataFile().getFilePath().getFilePath();
        
        PersonListWrapper wrapper = new PersonListWrapper();
        wrapper.setPersons(MainApp.getInstance().getPersonData());
        
        if (personFile != null) {
            MainApp.getInstance().getPersonDataFile().savePersonDataToFile(personFile);
        } 
        
        else {
            handleSaveAs();
        }
        
        DataDB bd = new DataDB();
        bd.savePersonDataToBD(wrapper);
    }

    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(MainApp.getInstance().getPrimaryStage());

        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            MainApp.getInstance().getPersonDataFile().savePersonDataToFile(file);
        }
    }

    @FXML
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("AddressApp");
        alert.setHeaderText("About");
        alert.setContentText("Autor: Marco Jakob \nAdaptação: Alberto Júnior\nWebsite: http://code.makery.ch");

        alert.showAndWait();
    }
    /**
    * Abre as estatísticas de aniversário.
    */
    
   @FXML
   private void handleShowBirthdayStatistics() {
     showBirthdayStatistics();
   }

   
    @FXML
    private void handleExit() {
        System.exit(0);
    }
    
}