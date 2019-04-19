/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Artiste;
import Entity.GroupeArtistes;
import Fonction.ServiceArtiste;
import Fonction.ServiceGroupeArtistes;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * FXML Controller class
 *
 * @author I.O.I
 */
public class AfficherGroupeArtistesController implements Initializable {

    @FXML
    private TextField filterField;
    @FXML
    private TableView<GroupeArtistes> tableGroupeArtistes;
    @FXML
    private TableColumn<GroupeArtistes, String> idGroupeArtistes;
    @FXML
    private TableColumn<GroupeArtistes, String> idChefGroupeArtistes;
    @FXML
    private TableColumn<GroupeArtistes, String> nomGroupeArtistes;
    @FXML
    private TableColumn<GroupeArtistes, String> typeGroupeArtistes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("afffff");
        ServiceGroupeArtistes srvGroupeArtiste = new ServiceGroupeArtistes();
        idGroupeArtistes.setCellValueFactory(new PropertyValueFactory<>("id"));
        idChefGroupeArtistes.setCellValueFactory(new PropertyValueFactory<>("chefGroupeId"));
        nomGroupeArtistes.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeGroupeArtistes.setCellValueFactory(new PropertyValueFactory<>("type"));
        FilteredList<GroupeArtistes> filteredData = new FilteredList<>(srvGroupeArtiste.getAllGroupesArtistes(), p -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(groupeArtistes -> {
                // If filter text is empty, display all challenges.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                     String lowerCaseFilter = newValue.toLowerCase();
                
                if (groupeArtistes.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches  name.
                } else if (groupeArtistes.getType().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches description.
                }
                return false;
                });
        });
        SortedList<GroupeArtistes> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableGroupeArtistes.comparatorProperty());
        tableGroupeArtistes.setItems(sortedData);
        
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("Fiche 1");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < tableGroupeArtistes.getColumns().size(); j++) {
            row.createCell(j).setCellValue(tableGroupeArtistes.getColumns().get(j).getText());
        }

        for (int i = 0; i < tableGroupeArtistes.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < tableGroupeArtistes.getColumns().size(); j++) {
                if(tableGroupeArtistes.getColumns().get(j).getCellData(i) != null) { 
                    row.createCell(j).setCellValue(tableGroupeArtistes.getColumns().get(j).getCellData(i).toString()); 
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
            fileOut = new FileOutputStream("Groupe Artistes "+dateFormat.format(date)+".xls");
            workbook.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AfficherArtistesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AfficherArtistesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
