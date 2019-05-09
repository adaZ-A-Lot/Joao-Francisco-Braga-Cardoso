package ch.makery.address.util;

import ch.makery.address.model.Pessoa;
import java.util.List;


public class BirthdaysPerMonth {
    
  
    public static int[] caluculaAniversariosPorMes(List<Pessoa> pessoas, int[] contadorDeMes) {
        
        for (Pessoa pessoa : pessoas) {
            
            int mes = pessoa.getAniversario().getMonthValue() - 1;
            contadorDeMes[mes]++;
        }
        
        return contadorDeMes;
    }
}
