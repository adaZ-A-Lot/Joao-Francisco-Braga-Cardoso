package ch.makery.address.model.file;

import ch.makery.address.MainApp;
import java.io.File;
import java.util.prefs.Preferences;

public class FilePath {

    public File getFilePath() {
       
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
       String filePath = prefs.get("filePath", null);
      
       if (filePath != null) {
           return new File(filePath);
       } 
       
       else {
           return null;
       }
   }

    public void setFilePath(File file) {
       
        
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
       
        
        if (file != null) {
           prefs.put("filePath", file.getPath());

           MainApp.getInstance().getPrimaryStage().setTitle("AddressApp - " + file.getName());
       } 
        
        else {
        
            prefs.remove("filePath");

           MainApp.getInstance().getPrimaryStage().setTitle("AddressApp");
       }
   }
}
