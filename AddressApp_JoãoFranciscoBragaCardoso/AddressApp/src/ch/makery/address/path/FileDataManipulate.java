
package ch.makery.address.path;

import ch.makery.address.model.Pessoa;
import ch.makery.address.model.PessoaOrganizadorLista;
import java.io.File;
import javafx.collections.ObservableList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.controlsfx.dialog.Dialogs;


public class FileDataManipulate {
    
    private CaminhoArquivo caminho;
    
    private ObservableList<Pessoa> pessoa;
    
    
    public FileDataManipulate(CaminhoArquivo caminho, ObservableList<Pessoa> pessoa){
        
        this.caminho=caminho;
        this.pessoa = pessoa;
    }
    
    public void salvaDadosPessoaParaArquivo(File arquivo) {
       
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PessoaOrganizadorLista.class);
            
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            
            PessoaOrganizadorLista wrapper = new PessoaOrganizadorLista();
            wrapper.setPessoas(pessoa);

           
            m.marshal(wrapper, arquivo);

            
            caminho.setCaminhoArquivoPessoa(arquivo);
            
        } catch (Exception e) { 
            
            Dialogs.create().title("Erro")
                    .masthead("Não foi possível salvar os dados do arquivo:\n" 
                              + arquivo.getPath()).showException(e);
        }
    }
   
    public void carregaDadosPessoaDoArquivo(File arquivo) {
        try {
            
            JAXBContext context = JAXBContext
                    .newInstance(PessoaOrganizadorLista.class);
            Unmarshaller um = context.createUnmarshaller();

            
            PessoaOrganizadorLista wrapper = (PessoaOrganizadorLista) um.unmarshal(arquivo);

            pessoa.clear();
            pessoa.addAll(wrapper.getPessoas());

            
            caminho.setCaminhoArquivoPessoa(arquivo);

        } catch (Exception e) { 
            
            Dialogs.create()
                    .title("Erro")
                    .masthead("Não foi possível carregar dados do arquivo:\n" 
                              + arquivo.getPath()).showException(e);
        }
    }
}
