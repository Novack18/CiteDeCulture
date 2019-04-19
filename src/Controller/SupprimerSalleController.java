/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.SalleArt;
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
public class SupprimerSalleController implements Initializable {
    @FXML
    private ComboBox salleIds;
    @FXML
    private Button salleSupprimerBouton;
    
    @FXML
    public void loadSalle(ActionEvent event) throws IOException{
        salleSupprimerBouton.setVisible(true);
    }
    @FXML
    public void  supprimerSalle(ActionEvent event) throws IOException{
        ServiceSalleArt srvSalleArt = new ServiceSalleArt();
        srvSalleArt.supprimerSalle((int)salleIds.getValue());
        salleIds.getItems().clear();
        salleIds.getItems().addAll(srvSalleArt.getAllSalleIds());
        salleIds.getSelectionModel().select(0);
        salleSupprimerBouton.setVisible(false);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceSalleArt srvSalleArt = new ServiceSalleArt();
        salleIds.getItems().addAll(srvSalleArt.getAllSalleIds());
        salleIds.getSelectionModel().select(0);
        salleSupprimerBouton.setVisible(false);
    }    
    
}
