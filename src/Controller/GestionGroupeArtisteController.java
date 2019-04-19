/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.FOSUser;
import Fonction.ServiceFOSUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author I.O.I
 */
public class GestionGroupeArtisteController implements Initializable {
    @FXML
    private BorderPane gestionsalle;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clear(MouseEvent event) {
        gestionsalle.setCenter(null);
    }

    @FXML
    void afficher(MouseEvent event) throws IOException {
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("/Views/AfficherGroupeArtistes.fxml"));
        } catch (IOException e){
            
        }
        gestionsalle.setCenter(root);
    }

    @FXML
    private void ajouter(MouseEvent event) {
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("/Views/AjouterGroupeArtiste.fxml"));
        } catch (IOException e){
            
        }
        gestionsalle.setCenter(root);
    }

    @FXML
    private void modifier(MouseEvent event) {
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("/Views/ModifierGroupeArtiste.fxml"));
        } catch (IOException e){
            
        }
        gestionsalle.setCenter(root);
    }

    @FXML
    private void supprimer(MouseEvent event) {
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource("/Views/SupprimerGroupeArtiste.fxml"));
        } catch (IOException e){
            
        }
        gestionsalle.setCenter(root);
    }
    @FXML
    private void home(MouseEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Parent root = null;

        root = FXMLLoader.load(getClass().getResource("/Views/page3.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void deco(ActionEvent event) throws IOException{
        FOSUser u = new FOSUser();
        ServiceFOSUser.setCurrentUser(u);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Parent root = null;

        root = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
