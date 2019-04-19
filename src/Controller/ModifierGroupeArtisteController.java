/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Artiste;
import Entity.GroupeArtistes;
import Entity.SalleArt;
import Fonction.ServiceArtiste;
import Fonction.ServiceGroupeArtistes;
import Fonction.ServiceSalleArt;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javax.swing.JSpinner;

/**
 * FXML Controller class
 *
 * @author I.O.I
 */
public class ModifierGroupeArtisteController implements Initializable {
    @FXML
    private ComboBox ids;
    @FXML
    private Label nomText;
    @FXML
    private TextField nom;
    @FXML
    private Label typeText;
    @FXML
    private ComboBox type;
    @FXML
    private Label chefGroupeText;
    @FXML
    private ComboBox chefGroupe;
    @FXML
    private Button modifier;
    @FXML
    private Label erreurMessage;
    private GroupeArtistes g;
    
    @FXML
    public void loadGroup(ActionEvent event) throws IOException{
        nomText.setVisible(true);
        nom.setVisible(true);
        typeText.setVisible(true);
        type.setVisible(true);
        chefGroupeText.setVisible(true);
        chefGroupe.setVisible(true);
        modifier.setVisible(true);
        ServiceGroupeArtistes srvGroupeArtistes = new ServiceGroupeArtistes();
        g = srvGroupeArtistes.getGroupeById((int)ids.getValue());
        nom.setText(g.getNom());
        chefGroupe.getItems().clear();
        chefGroupe.getItems().addAll(srvGroupeArtistes.getAllArtistes());
        chefGroupe.getSelectionModel().select(0);
        type.getItems().clear();
        type.getItems().addAll("Choisir un type", "Opera", "Synfony", "Ballet", "Autre");
        type.getSelectionModel().select(0);
    }
    @FXML
    public void  modifierArtiste(ActionEvent event) throws IOException{
        erreurMessage.setText("");
        erreurMessage.setVisible(false);
        if(nom.getText().length()>0){
            ServiceGroupeArtistes srvGroupeArtistes = new ServiceGroupeArtistes();
            if(nom.getText().equals(g.getNom()) || srvGroupeArtistes.checkName(nom.getText())){
                if(!type.getValue().equals("Choisir un type")){
                    System.out.println("22");
                    if(!chefGroupe.getValue().equals("Choisir un artiste")){
                        System.out.println("11");
                        if(srvGroupeArtistes.getArtisteIdByCIN(chefGroupe.getValue().toString()) == g.getChefGroupeId() || srvGroupeArtistes.checkChef(srvGroupeArtistes.getArtisteIdByCIN(chefGroupe.getValue().toString()))){
                            System.out.println("33");
                            GroupeArtistes g = new GroupeArtistes((int)ids.getValue(), srvGroupeArtistes.getArtisteIdByCIN(chefGroupe.getValue().toString()), nom.getText(), type.getValue().toString());
                            srvGroupeArtistes.modifierGroupeArtistes(g);
                        }else{
                            erreurMessage.setText("Chef déjà attribuer à un autre groupe !");
                            erreurMessage.setVisible(true);
                        }
                    }else{
                        erreurMessage.setText("Choisir un chef de groupe !");
                        erreurMessage.setVisible(true);
                    }
                }else{
                    erreurMessage.setText("Choisir un type !");
                    erreurMessage.setVisible(true);
                }
            }else{
                erreurMessage.setText("Nom déjà utiliser !");
                erreurMessage.setVisible(true);
            }
        }else{
            erreurMessage.setText("Saisir un nom de groupe !");
            erreurMessage.setVisible(true);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceGroupeArtistes srvGroupeArtistes = new ServiceGroupeArtistes();
        ids.getItems().addAll(srvGroupeArtistes.getAllGroupeIds());
        ids.getSelectionModel().select(0);
        nomText.setVisible(false);
        nom.setVisible(false);
        typeText.setVisible(false);
        type.setVisible(false);
        chefGroupeText.setVisible(false);
        chefGroupe.setVisible(false);
        modifier.setVisible(false);
        erreurMessage.setText("");
        erreurMessage.setVisible(false);
    }    
    
}
