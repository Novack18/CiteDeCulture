/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Artiste;
import Fonction.ServiceArtiste;
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
import javafx.application.Platform;
import javafx.collections.ObservableList;
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
public class AfficherArtistesController implements Initializable {

    @FXML
    private TextField filterField;
    @FXML
    private TableView<Artiste> tableArtiste;
    @FXML
    private TableColumn<Artiste, String> cinArtiste;
    @FXML
    private TableColumn<Artiste, String> nomArtiste;
    @FXML
    private TableColumn<Artiste, String> prenomArtiste;
    @FXML
    private TableColumn<Artiste, Date> dateNaissanceArtiste;
    @FXML
    private TableColumn<Artiste, String> groupeArtiste;
    @FXML
    private Label gestionSalleMessage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("lalalla");
        ServiceArtiste srvArtiste = new ServiceArtiste();
        cinArtiste.setCellValueFactory(new PropertyValueFactory<>("cin"));
        nomArtiste.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomArtiste.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        dateNaissanceArtiste.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        groupeArtiste.setCellValueFactory(new PropertyValueFactory<>("groupeId"));
        //tableArtiste.setItems(srvArtiste.getAllSalles());
        FilteredList<Artiste> filteredData = new FilteredList<>(srvArtiste.getAllSalles(), p -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(artiste -> {
                // If filter text is empty, display all challenges.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                     String lowerCaseFilter = newValue.toLowerCase();
                
                if (artiste.getCin().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches  name.
                } else if (artiste.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches description.
                } else if (artiste.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches description.
                }
                return false;
                });
        });
        SortedList<Artiste> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableArtiste.comparatorProperty());
        tableArtiste.setItems(sortedData);

        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet("Fiche 1");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < tableArtiste.getColumns().size(); j++) {
            row.createCell(j).setCellValue(tableArtiste.getColumns().get(j).getText());
        }

        for (int i = 0; i < tableArtiste.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < tableArtiste.getColumns().size(); j++) {
                if(tableArtiste.getColumns().get(j).getCellData(i) != null) { 
                    row.createCell(j).setCellValue(tableArtiste.getColumns().get(j).getCellData(i).toString()); 
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
            fileOut = new FileOutputStream("Artistes "+dateFormat.format(date)+".xls");
            workbook.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AfficherArtistesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AfficherArtistesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
