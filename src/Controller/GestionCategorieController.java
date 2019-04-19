/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Categorie;
import Fonction.CategorieImplementation;
import Utils.ConnexionSingleton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author MON ASUS
 */
public class GestionCategorieController implements Initializable {
    
    
    @FXML
    private TableView<Categorie> CategorieTable;
    @FXML
    private TableColumn<Categorie, Integer> Categorie_id;
    @FXML
    private TableColumn<Categorie, String> Categorie_Nom;
    
    @FXML
    private TableColumn<Categorie, String> Categorie_Type;
    
    @FXML
    private TextField Nom_id1;
    @FXML
    private TextField Type_id1;
    
    @FXML
    private Button Bouton_modifier;
    
     @FXML
    private Button Bouton_supprimer;
     
     @FXML
    private Button Button_ajout;
    
    private int i=0;
    private boolean a;
    private int b;
   
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        Button_ajout.setOnAction( event -> {

            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/Views/Categorie.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
   
    
     CategorieImplementation CategorieImplementation=new CategorieImplementation();
     CategorieImplementation pdao = CategorieImplementation.getInstance();
        ArrayList arrayList= (ArrayList) pdao.AfficherCategorie();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        
        
       
       
       Categorie_id.setCellValueFactory(new PropertyValueFactory<>("id"));
      Categorie_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
      Categorie_Type.setCellValueFactory(new PropertyValueFactory<>("type"));
       CategorieTable.setItems(observableList);
       
       
       
        CategorieTable.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
                Nom_id1.setText(newValue.getNom());
                Type_id1.setText(newValue.getType());
       i= newValue.getId();
       
             
       Bouton_modifier.setOnAction(e -> {
         if (i==0)
                {
                    
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Cette Categorie n existe pas");
                alert.show();
                    
                }
           
                   
         else  if (Type_id1.getText().isEmpty()  )
           {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Les champs sont vide");
                alert.show();
           }
           
           else if (!(Type_id1.getText().matches("^[a-zA-Z]+$")) || (( !Nom_id1.getText().matches("^[a-zA-Z]+$"))))
           {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Le nom doit etre en lettre");
                alert.show();
               
           }
             
           else {
          
               
                       
                        
                        
               
                Categorie c = new Categorie(i,Nom_id1.getText(),Type_id1.getText());
                
                a=pdao.modifierCategorie(c);
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("La categorie a été modifié");
                alert.show();
                Nom_id1.setText("");
                Type_id1.setText("");
                
     
       
        
        
       
       
       Categorie_id.setCellValueFactory(new PropertyValueFactory<>("id"));
      Categorie_Nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
      Categorie_Type.setCellValueFactory(new PropertyValueFactory<>("type"));
       CategorieTable.setItems(observableList);
                
           }
         
         
        });
       
       
       Bouton_supprimer.setOnAction(e -> {
          if (i==0)
                {
                    
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Cette Categorie n existe pas");
                alert.show();
                    
                }
          
          else {
                  Categorie c = new Categorie(i,Nom_id1.getText(),Type_id1.getText());
                
                pdao.supprimer(c);
           
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("La categorie a été supprimée");
                alert.show();
                Nom_id1.setText("");
                Type_id1.setText("");
           
          }
         
        });
               
        });
        
        
     
        
        
    
    
}
    
    @FXML
    
    private void Retour(ActionEvent event)throws IOException, SQLException {
       
                
             try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/Views/Page.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            

               
               
               
    
}
    
}
