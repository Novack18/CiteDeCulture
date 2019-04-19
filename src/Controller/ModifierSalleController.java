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
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author I.O.I
 */
public class ModifierSalleController implements Initializable {
    @FXML
    private ComboBox salleIds;
    @FXML
    private Label salleTextNom;
    @FXML
    private TextField salleNom;
    @FXML
    private Label salleTextCapaciter;
    @FXML
    private Spinner<Integer> salleCapaciter;
    @FXML
    private Button salleModifierBouton;
    @FXML
    private Label erreurMessage;
    private SalleArt s;
    
    @FXML
    public void loadSalle(ActionEvent event) throws IOException{
        salleTextNom.setVisible(true);
        salleNom.setVisible(true);
        salleTextCapaciter.setVisible(true);
        salleCapaciter.setVisible(true);
        salleModifierBouton.setVisible(true);
        ServiceSalleArt srvSalleArt = new ServiceSalleArt();
        s = srvSalleArt.getSalleById((int)salleIds.getValue());
        salleNom.setText(s.getNom());
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 500, s.getCapaciter());
        salleCapaciter.setValueFactory(valueFactory);
    }
    @FXML
    public void  modifierSalle(ActionEvent event) throws IOException{
        System.out.println("Modification begun");
        erreurMessage.setVisible(false);
        ServiceSalleArt srvSalleArt = new ServiceSalleArt();
        if(srvSalleArt.checkSalleName(salleNom.getText()) || s.getNom().equalsIgnoreCase(salleNom.getText())){
            System.out.println("hihi");
            s = new SalleArt((int)salleIds.getValue(), salleNom.getText(), salleCapaciter.getValue());
            srvSalleArt.modifierSalle(s);
            s = new SalleArt();
            salleIds.getSelectionModel().select(0);
            salleNom.setText("");
            salleTextNom.setVisible(false);
            salleNom.setVisible(false);
            salleTextCapaciter.setVisible(false);
            salleCapaciter.setVisible(false);
            salleModifierBouton.setVisible(false);
            System.out.println("4");
        }else{
            erreurMessage.setVisible(true);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceSalleArt srvSalleArt = new ServiceSalleArt();
        salleIds.getItems().addAll(srvSalleArt.getAllSalleIds());
        salleIds.getSelectionModel().select(0);
        salleTextNom.setVisible(false);
        salleNom.setVisible(false);
        salleTextCapaciter.setVisible(false);
        salleCapaciter.setVisible(false);
        salleModifierBouton.setVisible(false);
        erreurMessage.setVisible(false);
    }    
    
}
