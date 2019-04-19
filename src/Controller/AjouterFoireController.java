/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Categorie;
import Entity.Foire;
import Fonction.CategorieImplementation;
import Fonction.FoireImplementation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author MON ASUS
 */
public class AjouterFoireController implements Initializable {
    
    
    
    @FXML
    private TextField NomId;
    @FXML
    private TextField NdSId;
    
    @FXML
    private DatePicker DdId;
    @FXML
    private TextField PsId;
    
    @FXML
    private DatePicker DFId;
    @FXML
     
    private ComboBox<String> CId;
    
    boolean a;
    int x;
        @FXML
    private Button AjouterId;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
         List<String> liste;
         
        liste = new ArrayList<String>();
          
     CategorieImplementation CategorieImplementation=new CategorieImplementation();
     CategorieImplementation pdao = CategorieImplementation.getInstance();
        liste = (ArrayList) pdao.AfficherNomCategorie();
        
        
        for(String i : liste ){
            
            //System.out.print(i);
            CId.getItems().add(i);
            
        }
        
        
        
        AjouterId.setOnAction( event -> {
            
           
           
           if (NomId.getText().isEmpty() || NdSId.getText().isEmpty() || PsId.getText().isEmpty() )
           {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Les champs sont vide");
                alert.show();
           }
          /* 
           else if (DdId.getValue().compareTo(DFId.getValue()) < 0)
           {
               
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("La date debut doit être avant la date fin");
                alert.show();
           }
           */
           else if (DFId.getValue().compareTo(DdId.getValue()) <0)
           {
               
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("La date fin doit être après la date debut");
                alert.show();
           }
           
           else if (!(NomId.getText().matches("^[a-zA-Z]+$")))
           {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Erreur dans le nom");
                alert.show();
               
           }
           
           else if (NdSId.getText().matches("^[a-zA-Z]+$") || PsId.getText().matches("^[a-zA-Z]+$") )
           {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez inserer quelque chose de correct");
                alert.show();
               
           }
           
           else {
               
           
            
            x=pdao.RecupererIdCategorie(CId.getValue());
            System.out.print(x);
            Date date1 = Date.from(DdId.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date date2 = Date.from(DFId.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date Date_debut = new java.sql.Date(date1.getTime());
            java.sql.Date Date_fin = new java.sql.Date(date2.getTime());
            int l=Integer.parseInt(NdSId.getText());
            int k=Integer.parseInt(PsId.getText());
          

        //System.out.println("Date      = " + date1);
        
            Foire f = new Foire (NomId.getText(),Date_debut,Date_fin,l,k,x);
            
            
            
            FoireImplementation pdao1 = FoireImplementation.getInstance();
            pdao1.ajouterFoire(f);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Foire insérée avec succés!");
        alert.show();
           }
           
       
        });
        
        
    }

    @FXML
    private void Retour4(ActionEvent event) {
    
     try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/Views/GestionFoire.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
            }
     
    }
}

   
    

