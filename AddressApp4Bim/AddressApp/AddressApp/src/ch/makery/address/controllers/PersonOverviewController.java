package ch.makery.address.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    private MainApp mainApp;

    public PersonOverviewController() {
    }

    /**
     * Inicializa a classe controller.
     */
    @FXML
    private void initialize() {
        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().lastNameProperty());

        showPersonDetails(null);

        personTable.getSelectionModel().selectedItemProperty().addListener(
               (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }


    public void setItemsOnTable(){
        
        personTable.setItems(MainApp.getInstance().getPersonData());
    }
    
    /**
    *Mostra os detalhes da pessoa
    */
    private void showPersonDetails(Person person) {
        if (person != null) {
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());

            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }
   public boolean showPersonEditDialog(Person person) {
       try {
           
           FXMLLoader loader = new FXMLLoader();
           loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
           AnchorPane page = (AnchorPane) loader.load();

           Stage dialogStage = new Stage();
           dialogStage.setTitle("Edita Pessoa");
           dialogStage.initModality(Modality.WINDOW_MODAL);
           dialogStage.initOwner(MainApp.getInstance().getPrimaryStage());
           Scene scene = new Scene(page);
           dialogStage.setScene(scene);

           PersonEditDialogController controller = loader.getController();
           controller.setDialogStage(dialogStage);
           controller.setPerson(person);

           dialogStage.showAndWait();

           return controller.isOkClicked();
       } 
       
       
       catch (IOException e) {
           e.printStackTrace();
           return false;
       }
   }
    /**
    * Chamado quando o usuário clica no botão delete.
    */
   @FXML
   private void handleDeletePerson() {
       int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
       if (selectedIndex >= 0) {
           personTable.getItems().remove(selectedIndex);
       } 
       
       else {
           // Nada selecionado.

       Alert alert = new Alert(AlertType.WARNING);
               alert.setTitle("Nenhuma seleção");
               alert.setHeaderText("Nenhuma Pessoa Selecionada");
               alert.setContentText("Por favor, selecione uma pessoa na tabela.");

               alert.showAndWait();
       }
   }
   @FXML
   private void handleNewPerson() {
       Person tempPerson = new Person();
       boolean okClicked = showPersonEditDialog(tempPerson);
      
       if (okClicked) {
           MainApp.getInstance().getPersonData().add(tempPerson);
       }
    }
   @FXML
   private void handleEditPerson() {
       Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
       
       if (selectedPerson != null) {
           boolean okClicked = showPersonEditDialog(selectedPerson);
           if (okClicked) {
               showPersonDetails(selectedPerson);
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