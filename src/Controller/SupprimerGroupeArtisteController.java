/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.SalleArt;
import Fonction.ServiceArtiste;
import Fonction.ServiceGroupeArtistes;
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
public class SupprimerGroupeArtisteController implements Initializable {
    @FXML
    private ComboBox ids;
    @FXML
    private Button groupeSupprimerBouton;
    
    @FXML
    public void loadGroup(ActionEvent event) throws IOException{
        groupeSupprimerBouton.setVisible(true);
    }
    @FXML
    public void  supprimerGroupe(ActionEvent event) throws IOException{
        ServiceGroupeArtistes srvGroupeArtistes = new ServiceGroupeArtistes();
        srvGroupeArtistes.supprimerGroupe((int)ids.getValue());
        ids.getItems().clear();
        ids.getItems().addAll(srvGroupeArtistes.getAllGroupeIds());
        ids.getSelectionModel().select(0);
        groupeSupprimerBouton.setVisible(false);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceGroupeArtistes srvGroupeArtistes = new ServiceGroupeArtistes();
        ids.getItems().addAll(srvGroupeArtistes.getAllGroupeIds());
        ids.getSelectionModel().select(0);
        groupeSupprimerBouton.setVisible(false);
    }    
    
}
