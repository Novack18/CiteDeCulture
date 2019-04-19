/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Artiste;
import Entity.SalleArt;
import Fonction.ServiceArtiste;
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
public class AjouterArtisteController implements Initializable {
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField cin;
    @FXML
    private DatePicker dateNaissance;
    @FXML
    private ComboBox groupe;
    @FXML
    private Label erreurMessage;

    @FXML
    public void ajouterArtiste(ActionEvent event) throws IOException{
        erreurMessage.setText("");
        erreurMessage.setVisible(false);
        try{
            Artiste a;
            Integer cinNumber;
            ServiceArtiste srvArtiste = new ServiceArtiste();
            if(cin.getText().length() == 8){
                cinNumber = Integer.parseInt(cin.getText());
                if(srvArtiste.checkCIN(cin.getText())){
                    System.out.println("GOOD");

                    System.out.println(dateNaissance.getValue().getYear()+"  _____  "+(Year.now().getValue()-12));
                    if(dateNaissance.getValue().getYear()<Year.now().getValue()-12){
                        String datech = dateNaissance.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        System.out.println(datech);
                        LocalDate dateX = LocalDate.parse(datech, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        System.out.println(dateX);
                        Date date = java.sql.Date.valueOf(dateX);
                        System.out.println(date);
                        System.out.println(groupe.getValue());

                        int groupeIdX = 0;
                        if(groupe.getValue() != "Choisir un groupe")
                            groupeIdX = srvArtiste.getGroupIdByName(groupe.getValue().toString());
                        a = new Artiste(0, (int)groupeIdX, nom.getText(), prenom.getText(), date, cin.getText());
                        srvArtiste.ajouterArtiste(a);
                        System.out.println("cong");
                    }else{
                        erreurMessage.setText("Vous devez etre âgé d'au moins 12 ans");
                        erreurMessage.setVisible(true);
                    }
                }else{
                    erreurMessage.setText("CIN déjà existe déjà !");
                    erreurMessage.setVisible(true);
                }
            }else{
                erreurMessage.setText("CIN doit contenir 8 chiffres !");
                erreurMessage.setVisible(true);
            }
        }catch(NumberFormatException e){
            erreurMessage.setText("CIN doit contenir 8 chiffres !");
            erreurMessage.setVisible(true);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceArtiste srvArtiste = new ServiceArtiste();
        groupe.getItems().addAll(srvArtiste.getAllGroupsNames());
        groupe.getSelectionModel().select(0);
        erreurMessage.setText("");
        erreurMessage.setVisible(false);
    }
}
