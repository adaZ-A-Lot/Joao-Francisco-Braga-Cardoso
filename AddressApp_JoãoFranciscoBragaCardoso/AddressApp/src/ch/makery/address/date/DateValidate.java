package ch.makery.address.date;


public class DateValidate {
    
   
    public static boolean validDate(String dateString) {
       
        return FormataData.parse(dateString) != null;
    }
}
