/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.SalleArt;
import Fonction.ServiceArtiste;
import Fonction.ServiceSalleArt;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author I.O.I
 */
public class SupprimerArtisteController implements Initializable {
    @FXML
    private ComboBox ids;
    @FXML
    private Button artisteSupprimerBouton;
    
    @FXML
    public void loadSalle(ActionEvent event) throws IOException{
        artisteSupprimerBouton.setVisible(true);
    }
    @FXML
    public void  supprimerSalle(ActionEvent event) throws IOException{
        ServiceArtiste srvArtiste = new ServiceArtiste();
        srvArtiste.supprimerArtiste((int)ids.getValue());
        ids.getItems().clear();
        ids.getItems().addAll(srvArtiste.getAllArtisteIds());
        ids.getSelectionModel().select(0);
        artisteSupprimerBouton.setVisible(false);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceArtiste srvArtiste = new ServiceArtiste();
        ids.getItems().addAll(srvArtiste.getAllArtisteIds());
        ids.getSelectionModel().select(0);
        artisteSupprimerBouton.setVisible(false);
    }    
    
}
