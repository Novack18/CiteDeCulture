/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Abonnement;
import Entity.Artiste;
import Entity.GroupeArtistes;
import Fonction.ServiceAbonnement;
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
public class AfficherAbonnementsController implements Initializable {

    @FXML
    private TextField filterField;
    @FXML
    private TableView<Abonnement> tableAbonnements;
    @FXML
    private TableColumn<GroupeArtistes, String> idAbonnement;
    @FXML
    private TableColumn<GroupeArtistes, String> userAbonnement;
    @FXML
    private TableColumn<GroupeArtistes, String> validiterAbonnement;
    @FXML
    private TableColumn<GroupeArtistes, String> categorieAbonnement;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceAbonnement srvAbonnement = new ServiceAbonnement();
        idAbonnement.setCellValueFactory(new PropertyValueFactory<>("id"));
        userAbonnement.setCellValueFactory(new PropertyValueFactory<>("username"));
        validiterAbonnement.setCellValueFactory(new PropertyValueFactory<>("range"));
        categorieAbonnement.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        FilteredList<Abonnement> filteredData = new FilteredList<>(srvAbonnement.getAllAbonnements(), p -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(abonnement -> {
                // If filter text is empty, display all challenges.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                     String lowerCaseFilter = newValue.toLowerCase();
                
                if (abonnement.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches  name.
                } else if (abonnement.getCategorie().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches description.
                }  else if (abonnement.getRange().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches description.
                }
                return false;
                });
        });
        SortedList<Abonnement> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableAbonnements.comparatorProperty());
        tableAbonnements.setItems(sortedData);
        
        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("Fiche 1");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < tableAbonnements.getColumns().size(); j++) {
            row.createCell(j).setCellValue(tableAbonnements.getColumns().get(j).getText());
        }

        for (int i = 0; i < tableAbonnements.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < tableAbonnements.getColumns().size(); j++) {
                if(tableAbonnements.getColumns().get(j).getCellData(i) != null) { 
                    row.createCell(j).setCellValue(tableAbonnements.getColumns().get(j).getCellData(i).toString()); 
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
            fileOut = new FileOutputStream("Abonnements "+dateFormat.format(date)+".xls");
            workbook.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AfficherArtistesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AfficherArtistesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
