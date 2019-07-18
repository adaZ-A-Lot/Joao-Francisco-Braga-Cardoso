package ch.makery.address.path;

import ch.makery.address.MainApp;
import java.io.File;
import java.util.prefs.Preferences;
import javafx.stage.Stage;


public class FilePath {
   
    private Stage palcoPrimario;
    
    public FilePath(Stage palcoPrimario){
        this.palcoPrimario=palcoPrimario;
    }
    
   
    public File getCaminhoArquivoPessoa() {
        
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
       
        String caminhoArquivo = prefs.get("caminhoArquivo", null);
        
        if (caminhoArquivo != null) {
            return new File(caminhoArquivo);
        } 
        
        else {
            return null;
        }
    }

    
    public void setCaminhoArquivoPessoa(File arquivo) {
        
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        
        if (arquivo != null) {
            prefs.put("caminhoArquivo", arquivo.getPath());

           
            palcoPrimario.setTitle("AddressApp - " + arquivo.getName());
            
        } else {
            prefs.remove("caminhoArquivo");

            
            palcoPrimario.setTitle("AddressApp");
        }
    }
}
