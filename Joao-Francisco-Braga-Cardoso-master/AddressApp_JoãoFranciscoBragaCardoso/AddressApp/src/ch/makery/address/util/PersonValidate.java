
package ch.makery.address.util;

import ch.makery.address.date.ValidaData;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class PersonValidate {
   
    
    public static boolean isInputValid(TextField campoNome, TextField campoSobrenome, TextField campoRua,TextField campoCEP, TextField campoCidade, TextField campoAniversario) {
        String errorMessage = "";
        

        if (campoNome.getText() == null || campoNome.getText().length() == 0) {
            errorMessage += "Nome inválido!\n"; 
        }
        
        
        if (campoSobrenome.getText() == null || campoSobrenome.getText().length() == 0) {
            errorMessage += "Sobrenome inválido!\n"; 
        }
        
        
        if (campoRua.getText() == null || campoRua.getText().length() == 0) {
            errorMessage += "Rua inválida!\n"; 
        }

        
        if (campoCEP.getText() == null || campoCEP.getText().length() == 0) {
            errorMessage += "Código Postal inválido!\n"; 
            
        } else {
           
            try {
                Integer.parseInt(campoCEP.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Código Postal inválido (deve ser um inteiro)!\n"; 
            }
        }

        if (campoCidade.getText() == null || campoCidade.getText().length() == 0) {
            errorMessage += "Cidade inválida!\n"; 
        }

        
        if (campoAniversario.getText() == null || campoAniversario.getText().length() == 0) {
            errorMessage += "Aniversário inválido!\n";
        } else {
            
            if (!ValidaData.validDate(campoAniversario.getText())) {
                errorMessage += "Aniversário inválido. Use o formato dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
            
        } else {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
                     
                      alert.setTitle("Campos Inválidos");
                      alert.setHeaderText("Por favor, corrija os campos inválidos");
                      alert.setContentText(errorMessage);
                alert.showAndWait();
                
            return false;
        }
    }
}
