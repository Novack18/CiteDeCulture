/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.SalleArt;
import Fonction.ServiceSalleArt;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * FXML Controller class
 *
 * @author I.O.I
 */
public class AfficherSallesController implements Initializable {

    @FXML
    private TextField filterField;
    @FXML
    private TableView<SalleArt> tableSalle;
    @FXML
    private TableColumn<SalleArt, String> IdSalle;
    @FXML
    private TableColumn<SalleArt, String> nomSalle;
    @FXML
    private TableColumn<SalleArt, String> capaciterSalle;
    @FXML
    private Label gestionSalleMessage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(SalleArt.message);
        gestionSalleMessage.setText(SalleArt.message);
        SalleArt.message = "";
        ServiceSalleArt srvSalleArt = new ServiceSalleArt();
        IdSalle.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomSalle.setCellValueFactory(new PropertyValueFactory<>("nom"));
        capaciterSalle.setCellValueFactory(new PropertyValueFactory<>("capaciter"));
        FilteredList<SalleArt> filteredData = new FilteredList<>(srvSalleArt.getAllSalles(), p -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(salle -> {
                // If filter text is empty, display all challenges.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                     String lowerCaseFilter = newValue.toLowerCase();
                
                if (salle.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches  name.
                }
                return false;
                });
        });
        SortedList<SalleArt> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableSalle.comparatorProperty());
        tableSalle.setItems(sortedData);
        
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("Fiche 1");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < tableSalle.getColumns().size(); j++) {
            row.createCell(j).setCellValue(tableSalle.getColumns().get(j).getText());
        }

        for (int i = 0; i < tableSalle.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < tableSalle.getColumns().size(); j++) {
                if(tableSalle.getColumns().get(j).getCellData(i) != null) { 
                    row.createCell(j).setCellValue(tableSalle.getColumns().get(j).getCellData(i).toString()); 
                }
                else {
                    row.createCell(j).setCellValue("");
                }   
            }
        }

        FileOutputStream fileOut;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
            java.util.Date date;
            date = new java.util.Date();
            fileOut = new FileOutputStream("Salles "+dateFormat.format(date)+".xls");
            workbook.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AfficherArtistesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AfficherArtistesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
