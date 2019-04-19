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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author I.O.I
 */
public class AjouterGroupeArtisteController implements Initializable {
    @FXML
    private TextField nom;
    @FXML
    private ComboBox type;
    @FXML
    private ComboBox chefGroupe;
    @FXML
    private Label erreurMessage;

    @FXML
    public void ajouterGroupeArtistes(ActionEvent event) throws IOException{
        erreurMessage.setText("");
        erreurMessage.setVisible(false);
        if(nom.getText().length()>0){
            ServiceGroupeArtistes srvGroupeArtistes = new ServiceGroupeArtistes();
            if(srvGroupeArtistes.checkName(nom.getText())){
                if(!type.getValue().equals("Choisir un type")){
                    if(!chefGroupe.getValue().equals("Choisir un artiste")){
                        if(srvGroupeArtistes.checkChef(srvGroupeArtistes.getArtisteIdByCIN(chefGroupe.getValue().toString()))){
                            GroupeArtistes g = new GroupeArtistes(0, srvGroupeArtistes.getArtisteIdByCIN(chefGroupe.getValue().toString()), nom.getText(), type.getValue().toString());
                            srvGroupeArtistes.ajouterGroupeArtistes(g);
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
        chefGroupe.getItems().addAll(srvGroupeArtistes.getAllArtistes());
        chefGroupe.getSelectionModel().select(0);
        type.getItems().addAll("Choisir un type", "Opera", "Synfony", "Ballet", "Autre");
        type.getSelectionModel().select(0);
        erreurMessage.setText("");
        erreurMessage.setVisible(false);
    }
}
