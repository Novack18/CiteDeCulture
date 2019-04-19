/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Categorie;
import Entity.Foire;
import Entity.Stand;
import Fonction.FoireImplementation;
import Fonction.ServiceFOSUser;
import Fonction.StandImplementation;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
public class GestionDemandeController implements Initializable {
    
    
    @FXML
    private TableView<Stand> Table;
    @FXML
    private TableColumn<Stand, String> nomid;
    @FXML
    private TableColumn<Stand, String> nomfid;
    @FXML
    private TableColumn<Stand, String> nomfid1;
    @FXML
    private TableColumn<Stand, String> nomfid11;
    
     @FXML
    private Label labelid1;
    @FXML
    private Label labelid2;
    
    @FXML
    private Label labelid3;
    @FXML
    private Label labelid4;
    int i,d,f,g,l;
    @FXML
    private Button pdfid;
    @FXML
    private TextField chercher;
    @FXML
    private ComboBox<String> abc;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     
      
      
    String reponse;
              reponse="EnAttente";
              int nb;

               StandImplementation stand=new StandImplementation();
               ArrayList arrayList= (ArrayList) stand.AfficherDemande(reponse);
               ObservableList observableList = FXCollections.observableArrayList(arrayList);
               nb=stand.NombreDeDemand();
               
               String title = "Bonjour";
        String message = "Vous avez"+ " " +nb+" demande à repondre";
        Notification notification;
        notification = Notifications.SUCCESS;
       
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
                tray.showAndWait();
               nomid.setCellValueFactory(new PropertyValueFactory<>("utilisateur_nom"));
      nomfid.setCellValueFactory(new PropertyValueFactory<>("foire_nom"));
      nomfid1.setCellValueFactory(new PropertyValueFactory<>("email"));
      nomfid11.setCellValueFactory(new PropertyValueFactory<>("id"));
      
       Table.setItems(observableList);
     
       Table.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
                
                d=newValue.getUser_id();
                f=newValue.getFoire_id();
                
               labelid1.setText(newValue.getUtilisateur_nom());
               labelid2.setText(newValue.getFoire_nom());
               labelid3.setText(newValue.getEmail());
                 i=newValue.getId();
              l=newValue.getNombreDeStand();
              
                String a= Integer.toString(i);
                labelid4.setText(a);
               
           
       

                
             
                
                
                
                });
                
                   
      
    
    }
    @FXML
    
    private void Accepter(ActionEvent event)throws IOException, SQLException {
       
                int a;
                a=l-1;
            String reponse="Accepte";
            Stand s= new Stand(i,d,f,reponse,a);
            Foire foire=new Foire(f,a);
StandImplementation stand=new StandImplementation();


stand.Accepter1Location(s);

                 
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("La location du stand a été acceptée");
                alert.show();
                
                
                 
               
               int nb=stand.NombreDeDemand();
               
               String title = "Bonjour";
        String message = "Il reste"+ " " +nb+" demande à repondre";
        Notification notification;
        notification = Notifications.SUCCESS;
       
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
                tray.showAndWait();
               
               
    
}
    
    @FXML
    
    private void Refuser(ActionEvent event)throws IOException, SQLException {
       
                
            
            
StandImplementation stand=new StandImplementation();

stand.RefuserLocation(i);
                 
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("La location du stand n a pas été accepté");
                alert.show();
                
                
                
                int nb=stand.NombreDeDemand();
               
               String title = "Bonjour";
        String message = "Il reste"+ " " +nb+" demande à repondre";
        Notification notification;
        notification = Notifications.SUCCESS;
       
        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
                tray.showAndWait();
               
               
    
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
        /*
         Document document =new Document();
        PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\MON ASUS\\Desktop\\Code.pdf"));
        document.open();
        
        document.add(new Paragraph(" "));
        document.add(new Paragraph("Congrats,"));
        document.add(new Paragraph("Coupon generated by: " + "Pepiniere Society , " ));

            
            
            
            document.add(new Paragraph());
            document.add(new Paragraph(" " ));
            document.add(new Paragraph(" " ));
*/
            /*paragraph = new Paragraph("Your code coupon :   "+cd+"                                                                 Or you can just scan this QRCode " , smallBold);
            paragraph.setAlignment(Element.ALIGN_LEFT);
            document.add(paragraph);*/
            /*
            document.add(new Paragraph(" " ));
*/
            /*Image img=Image.getInstance(QR_CODE_IMAGE_PATH+cd+"QRCode.png");
            img.setAlignment(Element.ALIGN_RIGHT);
            document.add(img);*/
            
            
            /*Image img2=Image.getInstance(QR_CODE_IMAGE_PATH+"signature.png");
            img2.setAlignment(Element.ALIGN_RIGHT);
            document.add(img2);*/
            /*
            document.close();

        
           System.out.println("pdf généreé"); 
           */
    }
   
    
   
}
