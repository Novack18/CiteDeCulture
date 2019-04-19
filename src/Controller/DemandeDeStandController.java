/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Foire;
import Entity.Notification;
import Entity.Stand;
import Fonction.FoireImplementation;

import Fonction.ServiceFOSUser;
import Fonction.StandImplementation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author MON ASUS
 */
public class DemandeDeStandController implements Initializable {
    
    
     @FXML
    private TableView<Foire> TableFoiree;
    @FXML
    private TableColumn<Foire,Integer> Foire_id;
    
    @FXML
    private TableColumn<Foire,String> Foire_nom;
    
    
    @FXML
    private TableColumn<Foire,Date> Foire_Dated;
    
    @FXML
    private TableColumn<Foire,Date> Foire_Datef;
    
   
    
    @FXML
    private TableColumn<Foire,Integer> Foire_pds;
    
    @FXML
    private TableColumn<Foire, Integer> Foire_ci;
    
     @FXML
    private Label idlabel;
    @FXML
    private Label nomlabel;
    
    @FXML
    private Label ddlabel;
    
    @FXML
    private Label dflabel;
     @FXML
    private Label Pslabel;
     
     @FXML
    private Label ctlabel;
    String nom;
    int q,i;
    
     
      
      
     

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      FoireImplementation FoireImplementation=new FoireImplementation();
     FoireImplementation pdao = FoireImplementation.getInstance();
        ArrayList arrayList= (ArrayList) pdao.AfficherFoire();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        
        
       
       
       Foire_id.setCellValueFactory(new PropertyValueFactory<>("id"));
      Foire_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
      Foire_Dated.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
      Foire_Datef.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
      //Foire_nds.setCellValueFactory(new PropertyValueFactory<>("nombreDeStand"));
      Foire_pds.setCellValueFactory(new PropertyValueFactory<>("prixDuStand"));
      Foire_ci.setCellValueFactory(new PropertyValueFactory<>("categorie_id"));
       
       TableFoiree.setItems(observableList);
    
    
    TableFoiree.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
              
                        i=newValue.getNombreDeStand();
                int j=newValue.getPrixDuStand();
                int l=newValue.getCategorie_id();
                 q = newValue.getId();
                String a= Integer.toString(i);
                String b= Integer.toString(j);
                String c= Integer.toString(l);
                String d=Integer.toString(q);
                nom=newValue.getNom();
                
idlabel.setText(d);
nomlabel.setText(newValue.getNom());
 DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
 
        //to convert Date to String, use format method of SimpleDateFormat class.
        String strDated = dateFormat.format(newValue.getDate_debut());
        String strDatef = dateFormat.format(newValue.getDate_fin());
ddlabel.setText(strDated);
dflabel.setText(strDatef);
Pslabel.setText(b);
ctlabel.setText(c);


                
             
                
                
                
                });
       
    
    
    }
    
    @FXML
   private void Louer(ActionEvent event)throws IOException, SQLException {
       StandImplementation stand=new StandImplementation();
      
       java.util.List<String> liste,liste2;
       
       
       liste=stand.Verification(ServiceFOSUser.getCurrentUser().getId());
      liste2=stand.Verification1(ServiceFOSUser.getCurrentUser().getId(),nom);
      
      if (i==0)
          
          
      {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("testtt");
                alert.show();
          
          
      }
      
      else if (!liste2.isEmpty())
           {
               
                
                 
               
                 
           
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Vous pouvez pas faire cette action");
                alert.show();
          
                
           
           }
     
       
       else   if ( liste.isEmpty())
           
       {
           
          
              String reponse;
              reponse="EnAttente";
Stand s = new Stand(ServiceFOSUser.getCurrentUser().getId(),q,reponse);
               
               stand.DemandeDeLocationDeStand(s);
               
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Une demande de location a été reçu , Vous receverez notre réponse dans les plus proche delai possible");
                alert.show();   
       }
       
       else
           
       {
           
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Le traitement de la demande est en cours");
                alert.show();
       }
       
               
    
}
   
   @FXML
    
    private void Retour(ActionEvent event)throws IOException, SQLException {
       
                
             try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/Views/Page1.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
            }
            

               
               
               
    
}
}
