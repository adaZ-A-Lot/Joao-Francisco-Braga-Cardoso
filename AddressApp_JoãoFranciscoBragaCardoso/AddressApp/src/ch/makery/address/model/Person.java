package ch.makery.address.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import ch.makery.address.date.AdaptadorDataLocal;



public class Person {

    private final StringProperty nome;
    private final StringProperty sobrenome;
    private final StringProperty rua;
    private final IntegerProperty CEP;
    private final StringProperty cidade;
    private final ObjectProperty<LocalDate> aniversario;

    
    
    public Person() {
        
        this(null, null);
    }
    
  
    public Person(String nome, String sobrenome) {
        
        this.nome = new SimpleStringProperty(nome);
        this.sobrenome = new SimpleStringProperty(sobrenome);
        this.rua = new SimpleStringProperty();
        this.CEP = new SimpleIntegerProperty();
        this.cidade = new SimpleStringProperty();
        this.aniversario = new SimpleObjectProperty<LocalDate>();
    }
    
    
    public String getNome() {
        
        return nome.get();
    }

    public void setNome(String nome) {
        
        this.nome.set(nome);
    }
    
    
    public StringProperty propertyNome() {
        
        return nome;
    }

    public String getSobrenome() {
        
        return sobrenome.get();
    }

    
    public void setSobrenome(String sobrenome) {
        
        this.sobrenome.set(sobrenome);
    }
    
    
    public StringProperty propertySobrenome() {
        
        return sobrenome;
    }

    
    public String getRua() {
        
        return rua.get();
    }

    
    public void setRua(String rua) {
        
        this.rua.set(rua);
    }
    
    
    public StringProperty propertyRua() {
        
        return rua;
    }

    
    public int getCEP() {
        
        return CEP.get();
    }

    
    public void setCEP(int CEP) {
        
        this.CEP.set(CEP);
    }
    
    
    public IntegerProperty propertyCEP() {
        
        return CEP;
    }

    
    public String getCidade() {
        
        return cidade.get();
    }

    
    public void setCidade(String cidade) {
        
        this.cidade.set(cidade);
    }
    
    
    public StringProperty propertyCidade() {
        
        return cidade;
    }

    
    @XmlJavaTypeAdapter(AdaptadorDataLocal.class)
    
    public LocalDate getAniversario() {
        
        return aniversario.get();
    }

    
    public void setAniversario(LocalDate aniversario) {
        
        this.aniversario.set(aniversario);
    }
    
    
    public ObjectProperty<LocalDate> propertyAniversario() {
        
        return aniversario;
    }
}