/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Categorie;
import Entity.Foire;
import Fonction.CategorieImplementation;
import Fonction.FoireImplementation;
import Fonction.StandImplementation;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.management.Notification;
import tray.notification.TrayNotification;

/**
 *
 * @author MON ASUS
 */
public class GestionFoireController implements Initializable {
    
    @FXML
    private TableView<Foire> TableFoire;
    @FXML
    private TableColumn<Foire,Integer> Foire_id;
    
    @FXML
    private TableColumn<Foire,String> Foire_nom;
    
    
    @FXML
    private TableColumn<Foire,Date> Foire_Dated;
    
    @FXML
    private TableColumn<Foire,Date> Foire_Datef;
    
    @FXML
    private TableColumn<Foire,Integer> Foire_nds;
    
    @FXML
    private TableColumn<Foire,Integer> Foire_pds;
    
    @FXML
    private TableColumn<Foire, Integer> Foire_ci;
    
    @FXML
    private TextField Nom_id2;
    @FXML
    private TextField nds_id3;
    
    @FXML
    private TextField pds_id2;
    
    private TextField ci_id3;
     @FXML
    private DatePicker dd_id2;
     
      @FXML
    private DatePicker df_id2;
      
      int q=0;
      String nomfoire;
    @FXML
    private ComboBox<String> cid3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
          
         java.util.List<String> liste;
         
        liste = new ArrayList<String>();
          
     CategorieImplementation CategorieImplementation=new CategorieImplementation();
     CategorieImplementation pdao = CategorieImplementation.getInstance();
        liste = (ArrayList) pdao.AfficherNomCategorie();
        
        
        for(String i : liste ){
            
            //System.out.print(i);
            cid3.getItems().add(i);
            
        }
        
        
     
        FoireImplementation FoireImplementation=new FoireImplementation();
        
     FoireImplementation pdao1 = FoireImplementation.getInstance();
        ArrayList arrayList= (ArrayList) pdao1.AfficherFoire();
        ObservableList observableList = FXCollections.observableArrayList(arrayList);
        
        
       
       
       Foire_id.setCellValueFactory(new PropertyValueFactory<>("id"));
      Foire_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
      Foire_Dated.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
      Foire_Datef.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
      Foire_nds.setCellValueFactory(new PropertyValueFactory<>("nombreDeStand"));
      Foire_pds.setCellValueFactory(new PropertyValueFactory<>("prixDuStand"));
      Foire_ci.setCellValueFactory(new PropertyValueFactory<>("categorie_id"));
       
       TableFoire.setItems(observableList);
        
       TableFoire.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
           nomfoire=newValue.getNom();
                Nom_id2.setText(newValue.getNom());
                //int l=String..parseInt(newValue.getNombreDeStand());
                int i=newValue.getNombreDeStand();
                int j=newValue.getPrixDuStand();
                int l=newValue.getCategorie_id();
                 q = newValue.getId();
                String a= Integer.toString(i);
                String b= Integer.toString(j);
                String c= Integer.toString(l);
                nds_id3.setText(a);
                pds_id2.setText(b);
                //ci_id3.setText(c);
           
LocalDate date = newValue.getDate_debut().toLocalDate();
//Instant instant = newValue.getDate_debut().toInstant();
//ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
//LocalDate dated = zdt.toLocalDate();
                dd_id2.setValue(date);
               LocalDate date1 = newValue.getDate_fin().toLocalDate(); 
                //Instant instant1 = newValue.getDate_fin().toInstant();
//ZonedDateTime zdt1 = instant1.atZone(ZoneId.systemDefault());
//LocalDate datef = zdt1.toLocalDate();
df_id2.setValue(date1);

                
             
               StandImplementation stand=new StandImplementation();
              int nbr=stand.Nombre(q);
                
                  
               String title = "Bonjour";
        String message = "Vous avez"+ " " +nbr+"  personne dans cette foire";
        Notification notification;
        notification = Notifications.SUCCESS;
       
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
                tray.showAndWait();
                
                });
                
    }
    
    
    
      @FXML
   private void Supprimer(ActionEvent event)throws IOException, SQLException {
       
        if (q==0)
       {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Il y a rien à supprimer");
                alert.show();
       }
        
        
        else {
       java.util.Date date1 = java.util.Date.from(dd_id2.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.util.Date date2 = java.util.Date.from(df_id2.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date Date_debut = new java.sql.Date(date1.getTime());
            java.sql.Date Date_fin = new java.sql.Date(date2.getTime());
            int l=Integer.parseInt(nds_id3.getText());
            int k=Integer.parseInt(pds_id2.getText());
            int b=Integer.parseInt(ci_id3.getText());
Foire f = new Foire(q,Foire_nom.getText(),Date_debut,Date_fin,l,k,b);
                 FoireImplementation pdao = FoireImplementation.getInstance();
                pdao.supprimer(f);
           
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("La Foire a été supprimée");
                alert.show();
        }   
    
}
     @FXML
   private void Modifier(ActionEvent event)throws IOException, SQLException {
        FoireImplementation pdao = FoireImplementation.getInstance();
       if (q==0)
       {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Cette foire n existe pas");
                alert.show();
       }
       
       else if (dd_id2.getValue().compareTo(df_id2.getValue()) <0)
           {
               
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("La date fin doit être après la date debut");
                alert.show();
           }
       else 
       {
           
           if (nds_id3.getText().isEmpty() || pds_id2.getText().isEmpty() || Nom_id2.getText().isEmpty() )
           {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Les champs sont vide");
                alert.show();
           }
           
           else if (!(Nom_id2.getText().matches("^[a-zA-Z]+$")))
           {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Erreur dans le nom");
                alert.show();
               
           }
           
           else if (nds_id3.getText().matches("^[a-zA-Z]+$") || pds_id2.getText().matches("^[a-zA-Z]+$") )
           {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez inserer quelque chose de correct");
                alert.show();
               
           }
           
           else
               
           {
           int id=pdao.categorie(cid3.getValue());
                 java.util.Date date1 = java.util.Date.from(dd_id2.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.util.Date date2 = java.util.Date.from(df_id2.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            java.sql.Date Date_debut = new java.sql.Date(date1.getTime());
            java.sql.Date Date_fin = new java.sql.Date(date2.getTime());
            int l=Integer.parseInt(nds_id3.getText());
            int k=Integer.parseInt(pds_id2.getText());
            //int b=Integer.parseInt(ci_id3.getText());
            String test=Nom_id2.getText();   
Foire f = new Foire(q,test,Date_debut,Date_fin,l,k,id);
               
                 //FoireImplementation pdao = FoireImplementation.getInstance();
                 //java.util.List<String> liste15;
                 //liste15=pdao.rechercher(q);
                 
                 
                 
                 
                 
                boolean a= pdao.modifierFoire(f);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("La Foire a été modifié");
                alert.show();
               
           }          
    
       }
                 
   }
   
   @FXML
   private void Ajout(ActionEvent event)throws IOException, SQLException {
       try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/Views/Foire.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(PageController.class.getName()).log(Level.SEVERE, null, ex);
            }
               
    
}
   
   
   @FXML
    
    private void Retour(ActionEvent event)throws IOException, SQLException {
       
                
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
    private void pdf(ActionEvent event) throws FileNotFoundException, DocumentException {
        StandImplementation stand=new StandImplementation();
        int numero=0;
       java.util.List<String> liste;
       liste= stand.ListeDesPersonne(q);
        Document document =new Document();
        //PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\MON ASUS\\Desktop\\Code1.pdf"));
        PdfWriter.getInstance(document, new FileOutputStream("C:\\Java\\CiteDeCulture\\src\\code.pdf"));
        
        document.open();
        
        document.add(new Paragraph(" "));
        document.add(new Paragraph(""));
        //document.add(new Paragraph("Liste des clients possedant un stand : "));

               document.add(new Paragraph("Nom de la foire  :  "+ "   " +  nomfoire  )); 
       
        for(String nom : liste ){
            
           
            
             document.add(new Paragraph( "Client numéro" + numero ));
            document.add(new Paragraph( nom ));
            numero++;
        }

         document.close();
           System.out.println("pdf généreé"); 
    }
}