/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Categorie;
import Fonction.CategorieImplementation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author MON ASUS
 */
public class AjouterCategorieController implements Initializable {
    
    @FXML
    private Button btn_ajout_categorie;
    @FXML
    private TextField Nom_id;
    @FXML
    private TextField Type_id;
    @FXML
    private Button retour;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
               btn_ajout_categorie.setOnAction(event -> {
                   
                   
                   
                    
           if (Type_id.getText().isEmpty()  )
           {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Les champs sont vide");
                alert.show();
           }
           
           else if (!(Nom_id.getText().matches("^[a-zA-Z]+$")))
           {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Le nom doit etre en lettre");
                alert.show();
               
           }
           
           else if ( Type_id.getText().matches("^[a-zA-Z]+$") )
           {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Le type doit etre en lettre");
                alert.show();
               
           }
           
           else 
           {
            
            Categorie c = new Categorie(Nom_id.getText(),Type_id.getText());
            CategorieImplementation pdao = CategorieImplementation.getInstance();
            pdao.ajouterCategorie(c);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Categorie insérée avec succés!");
        alert.show();
         Nom_id.setText("");
        Type_id.setText("");
           }
        });
        
        
    }

    @FXML
    private void retour3(ActionEvent event) {
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
