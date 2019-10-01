package ch.makery.address;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Class Aplication.
 * Tem como responsabilidade iniciar a aplicação.
 *
 * @author João Francisco Braga Cardoso
 * @version 1.0
 */

public class Aplication extends Application {

    /**
     * Define o stage deste dialog.
     * 
     * @param primaryStage
     */
    
    
    @Override
    
    
    public void start(Stage primaryStage) {
        MainApp agenda = MainApp.getInstance();
        agenda.primaryStage = primaryStage;
        agenda.primaryStage.setTitle("AddressApp");
        agenda.primaryStage.getIcons().
                add(new Image("file:resources/images/agenda.png"));
        agenda.initRootLayout();
        agenda.showPersonOverview();
    }
    
    
    
    public static void main(String[] args) {
        launch(args);
    }
}
