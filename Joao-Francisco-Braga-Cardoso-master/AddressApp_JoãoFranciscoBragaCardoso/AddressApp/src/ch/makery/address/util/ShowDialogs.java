
package ch.makery.address.util;

import ch.makery.address.MainApp;
import ch.makery.address.controllers.ControleDeEditarPessoa;
import ch.makery.address.controllers.ControleDeEstatisticasDeAniversarios;
import ch.makery.address.model.Pessoa;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;


public class ShowDialogs {
    
    private Stage palcoPrimario;
    
    public ShowDialogs(Stage palcoPrimario){
        
        this.palcoPrimario = palcoPrimario;
    }
   
    public boolean showPessoaEditDialog(Pessoa pessoa) {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EditarPessoa.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Pessoa");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(palcoPrimario);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

        
            ControleDeEditarPessoa controller = loader.getController();
            controller.setPalcoDialogo(dialogStage);
            controller.setPessoa(pessoa);

            
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
   
    
    public void showBirthdayStatistics(ObservableList<Pessoa> pessoa) {
       
        System.out.println("ANIVERSARIO");
        
        
        
        try {
        
            Dialogs.create().title("Certo")
                    .masthead("Chamou o showBirthdayStatistics no main app\n");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EstatisticasDeAniversarios.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Birthday Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(palcoPrimario);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

 
            
            ControleDeEstatisticasDeAniversarios controller = loader.getController();
            controller.adicionaDadosAoGrafico(pessoa);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
