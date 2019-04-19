/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Abonnement;
import Entity.AbonnementTemplate;
import Entity.SalleArt;
import Fonction.ServiceAbonnement;
import Fonction.ServiceAbonnementTemplate;
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
public class SupprimerAbonnementController implements Initializable {
    @FXML
    private ComboBox ids;
    @FXML
    private Button abonnementSupprimerBouton;
    
    @FXML
    public void loadAbonnement(ActionEvent event) throws IOException{
        abonnementSupprimerBouton.setVisible(true);
    }
    @FXML
    public void supprimerAbonnement(ActionEvent event) throws IOException{
        ServiceAbonnement srvAbonnement = new ServiceAbonnement();
        ServiceAbonnementTemplate srvAbonnementTemplate = new ServiceAbonnementTemplate();
        Abonnement a = srvAbonnement.getAbonnementById((int)ids.getValue());
        srvAbonnement.supprimerAbonnement((int)ids.getValue());
        AbonnementTemplate aT = srvAbonnementTemplate.getAbonnementTemplateById(a.getAbonnementTemplateId());
        switch(a.getCategorie()){
            case "Optima": aT.setNbr_place_cat0(aT.getNbr_place_cat0()+1);break;
            case "Catégorie 1": aT.setNbr_place_cat1(aT.getNbr_place_cat1()+1);break;
            case "Catégorie 2": aT.setNbr_place_cat2(aT.getNbr_place_cat2()+1);break;
            case "Catégorie 3": aT.setNbr_place_cat3(aT.getNbr_place_cat3()+1);break;
            case "Catégorie 4": aT.setNbr_place_cat4(aT.getNbr_place_cat4()+1);break;
            default:
        }
        srvAbonnementTemplate.modifierAbonnementTemplate(aT);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceAbonnement srvAbonnement = new ServiceAbonnement();
        ids.getItems().addAll(srvAbonnement.getAllAbonnementsIds());
        ids.getSelectionModel().select(0);
        abonnementSupprimerBouton.setVisible(false);
    }    
    
}
