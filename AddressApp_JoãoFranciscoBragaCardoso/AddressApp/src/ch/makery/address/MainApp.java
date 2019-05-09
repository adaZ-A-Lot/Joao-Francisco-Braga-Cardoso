package ch.makery.address;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ch.makery.address.model.*;
import ch.makery.address.controllers.*;
import ch.makery.address.path.CaminhoArquivo;
import ch.makery.address.path.ManipulaDadosArquivo;
import java.io.File;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class MainApp extends Application {

    private Stage palcoPrimario;
    private BorderPane layoutBase;
    private CaminhoArquivo caminhoArquivo;
    private ManipulaDadosArquivo manipuladorDados;

    
    private ObservableList<Pessoa> dadosPessoa = FXCollections.observableArrayList();
    
   
    public ObservableList<Pessoa> getDadosPessoa() {
        return dadosPessoa;
    }
    
    @Override
    public void start(Stage palcoPrimario) {
        
        this.palcoPrimario = palcoPrimario;
        this.palcoPrimario.setTitle("AddressApp");
        
       
        this.palcoPrimario.getIcons().add(new
                Image("file:resources/images/address_book_32.png"));
        
        caminhoArquivo = new CaminhoArquivo(palcoPrimario);
        manipuladorDados = new ManipulaDadosArquivo(caminhoArquivo, dadosPessoa);
        
        initLayoutBase();

        showVisualizarPessoa();
    }
    
   
    public void initLayoutBase() {
       
        try {
            
            FXMLLoader loader = new FXMLLoader();
            
            loader.setLocation(MainApp.class
                    .getResource("view/LayoutBase.fxml"));
            layoutBase = (BorderPane) loader.load();

            
            Scene scene = new Scene(layoutBase);
            palcoPrimario.setScene(scene);

            
            ControleDeLayoutBase controle = loader.getController();
            controle.setMainApp(this);
            controle.setCaminhoArquivo(caminhoArquivo);
            controle.setManipulaDadosArquivo(manipuladorDados);
            controle.setShowDialogs(palcoPrimario,  dadosPessoa);

            palcoPrimario.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

       
        File file = caminhoArquivo.getCaminhoArquivoPessoa();
        
        if (file != null) {
            manipuladorDados.carregaDadosPessoaDoArquivo(file);
        }
        
    }
    
   
    public void showVisualizarPessoa() {
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/VisualizarPessoa.fxml"));
            AnchorPane visaoPessoa = (AnchorPane) loader.load();

            
            layoutBase.setCenter(visaoPessoa);

            
            ControleDeVizualizarPessoa controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
   
    public Stage getPalcoPrimario() {
        return palcoPrimario;
    }

    public static void main(String[] args) {
        launch(args);
    }
}