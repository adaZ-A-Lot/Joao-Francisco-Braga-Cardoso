package ch.makery.address.util;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adaptador (para JAXB) para converter entre LocalDate e representação String 
 * ISO 8601 da data como '2012-12-03'.
 * 
 * @author João Francisco, Marco Jakob
 * @version 2.0
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

   

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }

    
    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}