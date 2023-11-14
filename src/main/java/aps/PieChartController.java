package aps;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

public class PieChartController implements Initializable {
    
    @FXML
    private PieChart pieChart;


    public void connectDb(){
        try{
            Class.forName("null");//Enter the db you want to link to
            
            


        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        pieChart = new PieChart();
        loadDataFromFile();
        pieChart.setTitle("Results for questionare");
        throw new UnsupportedOperationException("Unimplemented method 'initialize'");
    }

    private void loadDataFromFile() {
        try{
            File file = new File("Null");//add the file you want to read
            Scanner scanner = new Scanner(file);
            
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                String label = parts[0];
                double value = Double.parseDouble(parts[1]);
                pieChart.getData().add(new PieChart.Data(label, value));
            }

            scanner.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
