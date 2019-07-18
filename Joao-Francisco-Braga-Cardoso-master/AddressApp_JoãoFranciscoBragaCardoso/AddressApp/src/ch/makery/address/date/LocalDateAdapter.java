package ch.makery.address.date;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;


public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String data) throws Exception {
        
        return LocalDate.parse(data);
    }

    
    @Override
    public String marshal(LocalDate data) throws Exception {
        
        return data.toString();
    }
}