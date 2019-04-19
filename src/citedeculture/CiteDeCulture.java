/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package citedeculture;
import Utils.ConnexionSingleton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import Utils.ConnexionSingleton;
import java.io.IOException;
import java.sql.Connection;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;

/**
 *
 * @author MON ASUS
 */
public class CiteDeCulture extends Application {
    
     private Stage primaryStage;
    private Parent parentPage;
  
    
    @Override
    public void start(Stage primaryStage) throws IOException {
         Connection cnx;
        cnx = ConnexionSingleton.getInstance().getCnx();
    
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Cité de la culture");
        
        parentPage = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
        Scene scene = new Scene(parentPage);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        
    
   
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
