package ch.makery.address.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import ch.makery.address.model.Pessoa;
import ch.makery.address.date.FormataData;
import ch.makery.address.util.ValidaPessoa;


public class PersonEditController {

    @FXML
    private TextField campoNome;
    @FXML
    private TextField campoSobrenome;
    @FXML
    private TextField campoRua;
    @FXML
    private TextField campoCEP;
    @FXML
    private TextField campoCidade;
    @FXML
    private TextField campoAniversario;


    private Stage palcoDialogo;
    private Pessoa pessoa;
    private boolean okClicked = false;

    
    @FXML
    private void inicialize() {
    }

    public void setPalcoDialogo(Stage palcoDialogo) {
        
        this.palcoDialogo = palcoDialogo;
    }

    
    
    public void setPessoa(Pessoa pessoa) {
       
        this.pessoa = pessoa;

        campoNome.setText(pessoa.getNome());
        campoSobrenome.setText(pessoa.getSobrenome());
        campoRua.setText(pessoa.getRua());
        campoCEP.setText(Integer.toString(pessoa.getCEP()));
        campoCidade.setText(pessoa.getCidade());
        campoAniversario.setText(FormataData.format(pessoa.getAniversario()));
        campoAniversario.setPromptText("dd.mm.yyyy");
    }

   
    public boolean isOkClicked() {
        return okClicked;
    }

    
    
    @FXML
    private void handleOk() {
       
        if (ValidaPessoa.isInputValid(campoNome, campoSobrenome, campoRua, 
                campoCEP, campoCidade, campoAniversario)) {
            pessoa.setNome(campoNome.getText());
            pessoa.setSobrenome(campoSobrenome.getText());
            pessoa.setRua(campoRua.getText());
            pessoa.setCEP(Integer.parseInt(campoCEP.getText()));
            pessoa.setCidade(campoCidade.getText());
            pessoa.setAniversario(FormataData.parse(campoAniversario.getText()));

            okClicked = true;
            palcoDialogo.close();
        }
    }

   
    @FXML
    private void handleCancel() {
        palcoDialogo.close();
    }
}