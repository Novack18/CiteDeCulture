/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.FOSUser;
import Fonction.ServiceFOSUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author MON ASUS
 */
public class Page3Controller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
    }

    @FXML
    private void foire1(ActionEvent event) {
        
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/Views/Page.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @FXML
    private void artclassique1(ActionEvent event) {
        try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/Views/ArtClassiqueBack.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    @FXML
    public void deco(ActionEvent event) throws IOException{
        FOSUser u = new FOSUser();
        ServiceFOSUser.setCurrentUser(u);
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Parent root = null;

        root = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
