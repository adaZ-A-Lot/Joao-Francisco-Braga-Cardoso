package ch.makery.address.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.MainApp;
import ch.makery.address.model.Pessoa;
import ch.makery.address.date.FormataData;
import ch.makery.address.util.ShowDialogs;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PersonOverviewController {
    
    @FXML
    private TableView<Pessoa> tabelaPessoa;
    @FXML
    private TableColumn<Pessoa, String> colunaNome;
    @FXML
    private TableColumn<Pessoa, String> colunaSobrenome;

    @FXML
    private Label campoNome;
    @FXML
    private Label campoSobrenome;
    @FXML
    private Label campoRua;
    @FXML
    private Label campoCEP;
    @FXML
    private Label campoCidade;
    @FXML
    private Label campoAniversario;

  
    private MainApp mainApp;
    
    
    private ShowDialogs showDialogs;

   
    public PersonOverviewController(){
    }

    
    private void showPessoaDetails(Pessoa pessoa) {
        
        if (pessoa != null) {
           
            campoNome.setText(pessoa.getNome());
            campoSobrenome.setText(pessoa.getSobrenome());
            campoRua.setText(pessoa.getRua());
            campoCEP.setText(Integer.toString(pessoa.getCEP()));
            campoCidade.setText(pessoa.getCidade());
            campoAniversario.setText(FormataData.format(pessoa.getAniversario()));
            
        } 
        
        else {
           
            campoNome.setText("");
            campoSobrenome.setText("");
            campoRua.setText("");
            campoCEP.setText("");
            campoCidade.setText("");
            campoAniversario.setText("");
        }
    }
    
  
    @FXML
    private void initialize() {
   
        colunaNome.setCellValueFactory(
                cellData -> cellData.getValue().propertyNome());
        colunaSobrenome.setCellValueFactory(
                cellData -> cellData.getValue().propertySobrenome());

       
        showPessoaDetails(null);

        
        tabelaPessoa.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPessoaDetails(newValue));
    }

  
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        
        tabelaPessoa.setItems(mainApp.getDadosPessoa());
    }
    
   
   @FXML
    private void handleDeletePessoa() {
        int selectedIndex = tabelaPessoa.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            tabelaPessoa.getItems().remove(selectedIndex);
        } else {
            

        Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Nenhuma seleção");
                alert.setHeaderText("Nenhuma Pessoa Selecionada");
                alert.setContentText("Por favor, selecione uma pessoa na tabela.");

                alert.showAndWait();
        }
    }
   
 
    @FXML
    private void handleNewPessoa() {
        showDialogs = new ShowDialogs(mainApp.getPalcoPrimario());
        Pessoa tempPessoa = new Pessoa();
        boolean okClicked = showDialogs.showPessoaEditDialog(tempPessoa);
        if (okClicked) {
            mainApp.getDadosPessoa().add(tempPessoa);
        }
    }

   
    @FXML
    private void handleEditPessoa() {
        showDialogs = new ShowDialogs(mainApp.getPalcoPrimario());
        Pessoa selectedPessoa = tabelaPessoa.getSelectionModel().getSelectedItem();
        if (selectedPessoa != null) {
            boolean okClicked = showDialogs.showPessoaEditDialog(selectedPessoa);
            if (okClicked) {
                showPessoaDetails(selectedPessoa);
            }

        } else {
            
            Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Nenhuma seleção");
                alert.setHeaderText("Nenhuma Pessoa Selecionada");
                alert.setContentText("Por favor, selecione uma pessoa na tabela.");
                alert.showAndWait();
        }
    }
}