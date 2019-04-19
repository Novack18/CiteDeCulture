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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class AjouterSalleController implements Initializable {
    @FXML
    private TextField nom;
    @FXML
    private Spinner<Integer> capaciter;
    @FXML
    private Label erreurMessage;

    @FXML
    public void ajouterSalle(ActionEvent event) throws IOException{
        erreurMessage.setVisible(false);
        System.out.println("Ajouter salle");
        ServiceSalleArt srvSalleArt = new ServiceSalleArt();
        if(srvSalleArt.checkSalleName(nom.getText())){
            System.out.println("capaciter "+capaciter.getValue());
            SalleArt s = new SalleArt(-1, nom.getText(), capaciter.getValue());
            System.out.println(nom.getText()+" "+s.toString());
            srvSalleArt.ajouterSalle(s);
            System.out.println("Salle ajouté !");
            SalleArt.message = "Salle ajouté avec succès";
        }else{
            erreurMessage.setVisible(true);
        }
        /*GestionSalleController g = new GestionSalleController();
        try {
            g.afficher(event);
        } catch (IOException ex) {
            Logger.getLogger(AjouterSalleController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 500, 20);
        capaciter.setValueFactory(valueFactory);
        erreurMessage.setVisible(false);
    }
}
