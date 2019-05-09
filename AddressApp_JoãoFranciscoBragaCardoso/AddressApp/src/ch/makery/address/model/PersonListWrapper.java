package ch.makery.address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "pessoas")

public class PersonListWrapper {

    private List<Pessoa> pessoas;

    @XmlElement(name = "pessoa")

    
    public List<Pessoa> getPessoas() {
        
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        
        this.pessoas = pessoas;
    }
}