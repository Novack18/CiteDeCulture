/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.FOSUser;
import Manager.enc;
import Fonction.ServiceFOSUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author I.O.I
 */
public class LoginController implements Initializable {
    @FXML
    private Label messageErreur;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button connection;
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    public void creatAccount(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            Parent root = null;

            root = FXMLLoader.load(getClass().getResource("/Views/Registration.fxml"));

            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
    }
    @FXML
    public void connect(ActionEvent e) throws IOException{
        System.out.println("Hai !");
        ServiceFOSUser srvUser = new ServiceFOSUser();
        FOSUser u = srvUser.getUserByUsername(username.getText());
        if(u != null){
            System.out.println(u.toString());
            System.out.println(enc.checkPassword(password.getText(), u.getPassword()));
            if(enc.checkPassword(password.getText(), u.getPassword())){
                System.out.println("Congratz");
                
                ServiceFOSUser.setCurrentUser(u);
                
                Stage stage = (Stage)((Node) e.getSource()).getScene().getWindow();
                Parent root = null;
                
                if(u.getRoles().toLowerCase().contains("admin"))
                    root = FXMLLoader.load(getClass().getResource("/Views/page3.fxml"));
                else
                    root = FXMLLoader.load(getClass().getResource("/Views/Page1.fxml"));
        
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
            }
            else
                messageErreur.setText("Login ou mot de passe incorrect !");
        }
        else
            messageErreur.setText("Login ou mot de passe incorrect !");
    }
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
