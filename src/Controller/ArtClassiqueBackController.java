/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author I.O.I
 */
public class ArtClassiqueBackController implements Initializable {
    @FXML
    private Tab gestionSallesTab;
    @FXML
    private BorderPane gestionSalle;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gestionSalles(Event event) {
    }

    @FXML
    private void gestionArtistes(Event event) {
    }

    @FXML
    private void gestionGroupeArtistes(Event event) {
    }

    @FXML
    private void gestionAbonnements(Event event) {
    }

    @FXML
    private void gestionReservations(Event event) {
    }
    
}
