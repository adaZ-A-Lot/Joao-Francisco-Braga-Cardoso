package ch.makery.address.controllers;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import ch.makery.address.model.Pessoa;
import ch.makery.address.util.AniversariosPorMes;


public class BirthdayStatisticsController {

    @FXML
    private BarChart<String, Integer> graficoDeBarras;

    @FXML
    private CategoryAxis eixoX;

    private ObservableList<String> nomesMeses = FXCollections.observableArrayList();

   
    @FXML
    private void initialize() {
        
        String[] meses = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        
        nomesMeses.addAll(Arrays.asList(meses));
        
        
        eixoX.setCategories(nomesMeses);
    }

  
    public void adicionaDadosAoGrafico(List<Pessoa> pessoas) {
        
        int[] MesCont = new int[12];
        
        MesCont=AniversariosPorMes.caluculaAniversariosPorMes(pessoas,
                MesCont);
        
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        
       
        for (int i = 0; i < MesCont.length; i++) {
            
            series.getData().add(new XYChart.Data<>(nomesMeses.get(i), MesCont[i]));
        }
        
        graficoDeBarras.getData().add(series);
    }
}
