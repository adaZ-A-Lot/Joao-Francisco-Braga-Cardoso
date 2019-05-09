package ch.makery.address.controllers;

import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import org.controlsfx.dialog.Dialogs;

import ch.makery.address.MainApp;
import ch.makery.address.model.Pessoa;
import ch.makery.address.path.CaminhoArquivo;
import ch.makery.address.path.ManipulaDadosArquivo;
import ch.makery.address.util.ShowDialogs;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class RootLayoutController {

    
    
   
    private MainApp mainApp;
    private CaminhoArquivo caminho;
    private ManipulaDadosArquivo manipuladorDados;
    private ShowDialogs showDialogs;
    private ObservableList<Pessoa> pessoa;
    
   
    
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    
    public void setCaminhoArquivo(CaminhoArquivo caminho) {
        this.caminho = caminho;
    }
    
    
    public void setShowDialogs(Stage palcoPrimario, 
            ObservableList<Pessoa> pessoa){
        
        showDialogs = new ShowDialogs(palcoPrimario);
        this.pessoa=pessoa;
    }
  
    
    public void setManipulaDadosArquivo(ManipulaDadosArquivo manipuladorDados) {
        this.manipuladorDados = manipuladorDados;
    }

    
    @FXML
    private void handleNew() {
        
        mainApp.getDadosPessoa().clear();
        caminho.setCaminhoArquivoPessoa(null);
    }

    
    
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        
        fileChooser.getExtensionFilters().add(extFilter);

        
        File file = fileChooser.showOpenDialog(mainApp.getPalcoPrimario());

        if (file != null) {
            manipuladorDados.carregaDadosPessoaDoArquivo(file);
        }
    }

    
    @FXML
    private void handleSave() {
        File pessoaFile = caminho.getCaminhoArquivoPessoa();
        if (pessoaFile != null) {
            manipuladorDados.salvaDadosPessoaParaArquivo(pessoaFile);
        } else {
            handleSaveAs();
        }
    }

    
    @FXML
    private void handleSaveAs() {
        FileChooser escolhaArquivo = new FileChooser();

       
        FileChooser.ExtensionFilter filtroExtencao = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        escolhaArquivo.getExtensionFilters().add(filtroExtencao);

        
        File arquivo = escolhaArquivo.showSaveDialog(mainApp.getPalcoPrimario());

        if (arquivo != null) {
            
            if (!arquivo.getPath().endsWith(".xml")) {
                arquivo = new File(arquivo.getPath() + ".xml");
            }
            manipuladorDados.salvaDadosPessoaParaArquivo(arquivo);
        }
    }

   
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("AddressApp");
        alert.setHeaderText("Sobre");
        alert.setContentText("Adaptação de Gabriel Cruz \n sobre o trabalho de Marco Jakob");

        alert.showAndWait();
    }
    
    
    @FXML
    private void handleShowBirthdayStatistics() {
        Dialogs.create().title("Certo")
                    .masthead("Chamou o handleShowBirthdayStatistics\n");
        showDialogs.showBirthdayStatistics(pessoa);
    }

   
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}