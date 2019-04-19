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
public class ModifierArtisteController implements Initializable {
    @FXML
    private ComboBox ids;
    @FXML
    private Label nomText;
    @FXML
    private TextField nom;
    @FXML
    private Label prenomText;
    @FXML
    private TextField prenom;
    @FXML
    private Label cinText;
    @FXML
    private TextField cin;
    @FXML
    private Label dateNaissanceText;
    @FXML
    private DatePicker dateNaissance;
    @FXML
    private Label groupeText;
    @FXML
    private ComboBox groupe;
    @FXML
    private Button modifier;
    @FXML
    private Label erreurMessage;
    private Artiste a;
    
    @FXML
    public void loadArtiste(ActionEvent event) throws IOException{
        nomText.setVisible(true);
        nom.setVisible(true);
        prenomText.setVisible(true);
        prenom.setVisible(true);
        cinText.setVisible(true);
        cin.setVisible(true);
        dateNaissanceText.setVisible(true);
        dateNaissance.setVisible(true);
        groupeText.setVisible(true);
        groupe.setVisible(true);
        modifier.setVisible(true);
        ServiceArtiste srvArtiste = new ServiceArtiste();
        a = srvArtiste.getArtisteById((int)ids.getValue());
        nom.setText(a.getNom());
        prenom.setText(a.getPrenom());
        groupe.getItems().addAll(srvArtiste.getAllGroupsNames());
        groupe.getSelectionModel().select(0);
        cin.setText(a.getCin());
        //dateNaissance.setValue(a.getDate_naissance().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }
    @FXML
    public void  modifierArtiste(ActionEvent event) throws IOException{
        erreurMessage.setText("");
        erreurMessage.setVisible(false);
        try{
            Integer cinNumber;
            ServiceArtiste srvArtiste = new ServiceArtiste();
            if(cin.getText().equals(a.getCin()) || cin.getText().length() == 8){
                cinNumber = Integer.parseInt(cin.getText());
                if(cin.getText().equals(a.getCin()) || srvArtiste.checkCIN(cin.getText())){
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
                        a = new Artiste(a.getId(), (int)groupeIdX, nom.getText(), prenom.getText(), date, cin.getText());
                        srvArtiste.modifierArtiste(a);
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
        System.out.println("eeeee");
        ServiceArtiste srvArtiste = new ServiceArtiste();
        ids.getItems().addAll(srvArtiste.getAllArtisteIds());
        ids.getSelectionModel().select(0);
        nomText.setVisible(false);
        nom.setVisible(false);
        prenomText.setVisible(false);
        prenom.setVisible(false);
        cinText.setVisible(false);
        cin.setVisible(false);
        dateNaissanceText.setVisible(false);
        dateNaissance.setVisible(false);
        groupeText.setVisible(false);
        groupe.setVisible(false);
        modifier.setVisible(false);
        erreurMessage.setText("");
        erreurMessage.setVisible(false);
    }    
    
}
