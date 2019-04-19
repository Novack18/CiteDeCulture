/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fonction;

import Entity.Abonnement;
import Entity.FOSUser;
import Utils.ConnexionSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author I.O.I
 */
public class ServiceAbonnement {
    Connection c = ConnexionSingleton.getInstance().getCnx();
    
    public void ajouterAbonnement(Abonnement a){
        try {
            PreparedStatement pt = c.prepareStatement("insert into abonnement (user_id, abonnementTemplate_id, categorie) values(?, ?, ?)");
            pt.setInt(1, a.getUserId());
            pt.setInt(2, a.getAbonnementTemplateId());
            pt.setString(3, a.getCategorie());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifierAbonnement(Abonnement a){
        try {
            PreparedStatement pt = c.prepareStatement("update abonnement set user_id = ?, abonnementTemplate_id = ?, categorie = ? where id = ?");
            pt.setInt(1, a.getUser().getId());
            pt.setInt(2, a.getAbonnementTemplate().getId());
            pt.setString(3, a.getCategorie());
            pt.setInt(4, a.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerAbonnement(int id){
        try {
            PreparedStatement pt = c.prepareStatement("delete from abonnement where id = ?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void afficherAbonnement(){
        try {
            PreparedStatement pt= c.prepareStatement("select * from abonnement");
            ResultSet rs = pt.executeQuery();
            
            while(rs.next()){
                System.out.println("Abonnement: id = "+rs.getInt(1)+" | user id = "+rs.getInt(2)+" | categorie = "+rs.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ObservableList<Abonnement> getAllAbonnements(){
        try {
            PreparedStatement pt= c.prepareStatement("select * from abonnement");
            ResultSet rs = pt.executeQuery();
            ObservableList<Abonnement> oblist = FXCollections.observableArrayList();
            ServiceFOSUser srvFOSUser = new ServiceFOSUser();
            ServiceAbonnementTemplate srvAbonnementTemplate = new ServiceAbonnementTemplate();
            while(rs.next()){
                oblist.add(new Abonnement(rs.getInt(1), srvFOSUser.getUsernameById(rs.getInt(2)), srvAbonnementTemplate.getPeriodeById(rs.getInt(3)), rs.getString(4)));
                System.out.println("Abonnement : id = "+rs.getInt(1)+" | catégorie = "+rs.getString(4));
            }
            return oblist;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ObservableList getAllAbonnementsIds(){
        try {
            PreparedStatement pt= c.prepareStatement("select * from abonnement");
            ResultSet rs = pt.executeQuery();
            ObservableList abonnementsIds = FXCollections.observableArrayList();
            abonnementsIds.add("Choisir une abonnement");
            while(rs.next()){
                abonnementsIds.add(rs.getInt(1));
                System.out.println("Abonnement id = "+rs.getInt(1));
            }
            return abonnementsIds;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Abonnement getAbonnementById(int id){
        try {
            PreparedStatement pt= c.prepareStatement("select * from abonnement where id = ?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            rs.next();
            Abonnement a = new Abonnement(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
            return a;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public boolean checkAbonnementByUserIdAndATId(int userId, int aTId){
        try {
            PreparedStatement pt = c.prepareStatement("select * from abonnement where user_id = ? and abonnementTemplate_id = ?");
            pt.setInt(1, userId);
            pt.setInt(2, aTId);
            ResultSet rs = pt.executeQuery();
            if(!rs.next())
                return false;
            else
                return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
