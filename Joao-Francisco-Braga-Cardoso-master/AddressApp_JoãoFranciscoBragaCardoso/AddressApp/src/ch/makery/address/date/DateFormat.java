package ch.makery.address.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DateFormat {
    
  
    private static final String DATA_MODELO = "dd.MM.yyyy";
    
    
   
    private static final DateTimeFormatter DATAFORMATO = 
            DateTimeFormatter.ofPattern(DATA_MODELO);
    
   
    public static String format(LocalDate date) {
        
        if (date == null) {
            return null;
        }
        return DATAFORMATO.format(date);
    }


    public static LocalDate parse(String dateString) {
        
        try {
            return DATAFORMATO.parse(dateString, LocalDate::from);
        }
        
        catch (DateTimeParseException e) {
            return null;
        }
    }

}