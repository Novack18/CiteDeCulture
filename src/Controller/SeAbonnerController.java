/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Abonnement;
import Entity.AbonnementTemplate;
import Entity.FOSUser;
import Manager.EmailSend;
import Manager.SMSSender;
import Fonction.ServiceAbonnement;
import Fonction.ServiceAbonnementTemplate;
import Fonction.ServiceFOSUser;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author I.O.I
 */
public class SeAbonnerController implements Initializable {
    LinkedHashMap<String, Integer> dates;
    @FXML
    private ComboBox periode;
    @FXML
    private ComboBox categorie;
    @FXML
    private Label erreurMessage;
    @FXML
    private Label prix;
    @FXML
    private Button confirmer;
    private Boolean verif = false;
    
    @FXML
    public void userAction(ActionEvent event) throws IOException{
        if(verif){
            prix.setText("");
            prix.setVisible(false);
            confirmer.setVisible(false);
            verif = false;
        }
    }
    @FXML
    public void periodeAction(ActionEvent event) throws IOException{
        if(verif){
            prix.setText("");
            prix.setVisible(false);
            confirmer.setVisible(false);
            verif = false;
        }
    }
    @FXML
    public void categorieAction(ActionEvent event) throws IOException{
        if(verif){
            prix.setText("");
            prix.setVisible(false);
            confirmer.setVisible(false);
            verif = false;
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
    @FXML
    public void home(ActionEvent event) throws IOException{
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        Parent root = null;

        root = FXMLLoader.load(getClass().getResource("/Views/page3.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void ajouterAbonnement(ActionEvent event) throws IOException{
        prix.setText("");
        prix.setVisible(false);
        erreurMessage.setText("");
        erreurMessage.setVisible(false);
        if(!periode.getValue().equals("Choisir une période")){
            if(!categorie.getValue().equals("Choisir une catégorie")){
                switch(categorie.getValue().toString()){
                    case "Optima": prix.setText("Prix = 150 DT");break;
                    case "Catégorie 1": prix.setText("Prix = 110 DT");break;
                    case "Catégorie 2": prix.setText("Prix = 80 DT");break;
                    case "Catégorie 3": prix.setText("Prix = 60 DT");break;
                    case "Catégorie 4": prix.setText("Prix = 40 DT");break;
                    default:
                }
                System.out.println("1");
                ServiceAbonnement srvAbonnement = new ServiceAbonnement();
                ServiceFOSUser srvFOSUser = new ServiceFOSUser();
                if(!srvAbonnement.checkAbonnementByUserIdAndATId(ServiceFOSUser.getCurrentUser().getId(), (int)dates.get(periode.getValue()))){
                    ServiceAbonnementTemplate srvAbonnementTemplate = new ServiceAbonnementTemplate();
                    AbonnementTemplate aT = srvAbonnementTemplate.getAbonnementTemplateById((int)dates.get(periode.getValue()));
                    Boolean ok = true;
                    switch(categorie.getValue().toString()){
                        case "Optima": if(aT.getNbr_place_cat0()<1){erreurMessage.setText("Plus de place disponible !");ok=false;}break;
                        case "Catégorie 1": if(aT.getNbr_place_cat1()<1){erreurMessage.setText("Plus de place disponible !");ok=false;}break;
                        case "Catégorie 2": if(aT.getNbr_place_cat2()<1){erreurMessage.setText("Plus de place disponible !");ok=false;}break;
                        case "Catégorie 3": if(aT.getNbr_place_cat3()<1){erreurMessage.setText("Plus de place disponible !");ok=false;}break;
                        case "Catégorie 4": if(aT.getNbr_place_cat4()<1){erreurMessage.setText("Plus de place disponible !");ok=false;}break;
                        default:
                    }
                    if(ok){
                        System.out.println(dates.get(periode.getValue()));
                        System.out.println("cat = "+categorie.getValue());
                        confirmer.setVisible(true);
                        prix.setVisible(true);
                        verif = true;
                    }else{
                        erreurMessage.setVisible(true);
                    }
                }else{
                    erreurMessage.setText("Cet utilisateur déjà abonner à cette période !");
                    erreurMessage.setVisible(true);
                }
            }else{
                erreurMessage.setText("Choisie une catégorie !");
                erreurMessage.setVisible(true);
            }
        }else{
            erreurMessage.setText("Choisie une période !");
            erreurMessage.setVisible(true);
        }
    }
    @FXML
    public void confimationAchatAbonnement(ActionEvent event) throws IOException{
        ServiceFOSUser srvFOSUser = new ServiceFOSUser();
        ServiceAbonnement srvAbonnement = new ServiceAbonnement();
        Abonnement a = new Abonnement(0, ServiceFOSUser.getCurrentUser().getId(), (int)dates.get(periode.getValue()), (String)categorie.getValue());
        a.toString();
        srvAbonnement.ajouterAbonnement(a);
        ServiceAbonnementTemplate srvAbonnementTemplate = new ServiceAbonnementTemplate();
        AbonnementTemplate aT = srvAbonnementTemplate.getAbonnementTemplateById((int)dates.get(periode.getValue()));
        switch(categorie.getValue().toString()){
            case "Optima": aT.setNbr_place_cat0(aT.getNbr_place_cat0()-1);break;
            case "Catégorie 1": aT.setNbr_place_cat1(aT.getNbr_place_cat1()-1);break;
            case "Catégorie 2": aT.setNbr_place_cat2(aT.getNbr_place_cat2()-1);break;
            case "Catégorie 3": aT.setNbr_place_cat3(aT.getNbr_place_cat3()-1);break;
            case "Catégorie 4": aT.setNbr_place_cat4(aT.getNbr_place_cat4()-1);break;
            default:
        }
        srvAbonnementTemplate.modifierAbonnementTemplate(aT);
        
        SMSSender.SendSMS("+216"+ServiceFOSUser.getCurrentUser().getEmail_canonical(), "Bravo vous avez gagner un abo");
        EmailSend.SendMail(ServiceFOSUser.getCurrentUser().getEmail(), "Abonnement", "Votre abonnement es désormer actif, merci pour votre confiance !");
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Abonnement");
        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText("Votre demande a été traitée avec succès, vous recevrez un sms de notre part :)");
        alert.showAndWait();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        java.util.Date date = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(formatter.format(date));
        ServiceAbonnementTemplate srvAbonnementTemplate = new ServiceAbonnementTemplate();
        dates = srvAbonnementTemplate.getAbonnementTemplateByDate(formatter.format(date));
        Set<Map.Entry<String, Integer>> setLhm = dates.entrySet();
        Iterator<Map.Entry<String, Integer>> it2 = setLhm.iterator();
        while(it2.hasNext()){
            Map.Entry<String, Integer> e = it2.next();
            periode.getItems().add(e.getKey());
            System.out.println(e.getKey()+" --  "+e.getValue());
        }
        periode.getSelectionModel().select(0);
        categorie.getItems().addAll("Choisir une catégorie", "Optima", "Catégorie 1", "Catégorie 2", "Catégorie 3", "Catégorie 4");
        categorie.getSelectionModel().select(0);
        confirmer.setVisible(false);
        prix.setText("");
        prix.setVisible(false);
        erreurMessage.setText("");
        erreurMessage.setVisible(false);
    }
//    LinkedHashMap<String, Integer> dates;
//    private boolean check = true;
//    @FXML
//    private ComboBox abonnementPeriode;
//    @FXML
//    private Label abonnementCategorieText;
//    @FXML
//    private ComboBox abonnementCategorie;
//    @FXML
//    private Button subscribeButton;
//
//    @FXML
//    public void  loadCathegory(ActionEvent event) throws IOException{
//        abonnementCategorieText.setVisible(true);
//        abonnementCategorie.setVisible(true);
//    }
//    @FXML
//    public void  loadRest(ActionEvent event) throws IOException{
//        subscribeButton.setVisible(true);
//    }
//    @FXML
//    public void  subscribe(ActionEvent event) throws IOException{
//        ServiceAbonnement srvAbonnement = new ServiceAbonnement();
//        if(!srvAbonnement.checkAbonnementByUserIdAndATId(ServiceFOSUser.getCurrentUser().getId(), (int)dates.get(abonnementPeriode.getValue()))){
//            System.out.println(dates.get(abonnementPeriode.getValue()));
//            System.out.println(ServiceFOSUser.getCurrentUser().getId());
//            System.out.println(abonnementCategorie.getValue());
//            Abonnement a = new Abonnement(0, ServiceFOSUser.getCurrentUser().getId(), (int)dates.get(abonnementPeriode.getValue()), (String)abonnementCategorie.getValue());
//            a.toString();
//            srvAbonnement.ajouterAbonnement(a);
//        } else {
//            System.out.println("Error !");
//        }
//    }
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        System.out.println("Abonnement");
//        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(formatter.format(date));
//        ServiceAbonnementTemplate srvAbonnementTemplate = new ServiceAbonnementTemplate();
//        dates = srvAbonnementTemplate.getAbonnementTemplateByDate(formatter.format(date));
//        Set<Entry<String, Integer>> setLhm = dates.entrySet();
//        Iterator<Entry<String, Integer>> it2 = setLhm.iterator();
//        while(it2.hasNext()){
//            Entry<String, Integer> e = it2.next();
//            abonnementPeriode.getItems().add(e.getKey());
//            System.out.println(e.getKey()+" --  "+e.getValue());
//        }
//        abonnementPeriode.getSelectionModel().select(0);
//        abonnementCategorie.getItems().addAll("Choisir une catégorie", "Optima", "Catégorie 1", "Catégorie 2", "Catégorie 3", "Catégorie 4");
//        abonnementCategorie.getSelectionModel().select(0);
//        abonnementCategorieText.setVisible(false);
//        abonnementCategorie.setVisible(false);
//        subscribeButton.setVisible(false);
//    }    
    
}
