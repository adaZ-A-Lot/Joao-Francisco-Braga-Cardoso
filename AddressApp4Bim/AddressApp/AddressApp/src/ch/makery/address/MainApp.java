package ch.makery.address;

import ch.makery.address.model.*;
import ch.makery.address.controllers.*;
import ch.makery.address.model.file.DataFile;
import java.io.File;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/*
 * @author João Francisco
 * @version 1.0
 */

public class MainApp{

    Stage primaryStage;
    private BorderPane rootLayout;
    private DataFile dataFile;
    private static MainApp instance = null; 
    
    public static MainApp getInstance(){
        
        if(MainApp.instance == null){
            MainApp.instance = new MainApp();
        }
        
       return MainApp.instance;
    }
    
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    private MainApp() {
        personData.add(new Person("Alberto", "Júnior"));
        personData.add(new Person("Ana", "Pimenta"));
        personData.add(new Person("Arthur", "Marcolino"));
        personData.add(new Person("Sávio", "Cardoso"));
    }
    
    public ObservableList<Person> getPersonData() {
        return personData;
    }
    public void initRootLayout() {
       
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootLayoutController controller = loader.getController();


            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dataFile = new DataFile();
        File file = dataFile.getFilePath().getFilePath();
        
        if (file != null) {
            dataFile.loadPersonDataFromFile(file);
        }
    }

    public void showPersonOverview() {
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(personOverview);

            PersonOverviewController controller = loader.getController();
            controller.setItemsOnTable();

        } 
        
        catch (IOException e) {
            e.printStackTrace();
    }
}

    
    public DataFile getPersonDataFile(){
       return dataFile;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}