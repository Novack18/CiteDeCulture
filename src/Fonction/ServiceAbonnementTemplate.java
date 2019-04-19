/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fonction;

import Entity.AbonnementTemplate;
import Entity.FOSUser;
import Utils.ConnexionSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author I.O.I
 */
public class ServiceAbonnementTemplate {
    Connection c = ConnexionSingleton.getInstance().getCnx();
    
    public void ajouterAbonnementTemplate(AbonnementTemplate a){
        try {
            PreparedStatement pt = c.prepareStatement("insert into abonnementtemplate values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pt.setInt(1, a.getId());
            pt.setDate(2, a.getDate_debut());
            pt.setDate(3, a.getDate_fin());
            pt.setInt(4, a.getPrix());
            pt.setInt(5, a.getNbr_place_cat0());
            pt.setInt(6, a.getNbr_place_cat1());
            pt.setInt(7, a.getNbr_place_cat2());
            pt.setInt(8, a.getNbr_place_cat3());
            pt.setInt(9, a.getNbr_place_cat4());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonnementTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifierAbonnementTemplate(AbonnementTemplate a){
        try {
            PreparedStatement pt = c.prepareStatement("update abonnementtemplate set date_debut = ?, date_fin = ?, prix = ?, nbr_place_cat0 = ?, nbr_place_cat1 = ?, nbr_place_cat2 = ?, nbr_place_cat3 = ?, nbr_place_cat4 = ? where id = ?");
            pt.setDate(1, a.getDate_debut());
            pt.setDate(2, a.getDate_fin());
            pt.setInt(3, a.getPrix());
            pt.setInt(4, a.getNbr_place_cat0());
            pt.setInt(5, a.getNbr_place_cat1());
            pt.setInt(6, a.getNbr_place_cat2());
            pt.setInt(7, a.getNbr_place_cat3());
            pt.setInt(8, a.getNbr_place_cat4());
            pt.setInt(9, a.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonnementTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerAbonnementTemplate(int id){
        try {
            PreparedStatement pt = c.prepareStatement("delete from abonnementtemplate where id = ?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonnementTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void afficherAbonnementTemplate(){
        try {
            PreparedStatement pt= c.prepareStatement("select * from abonnementtemplate");
            ResultSet rs = pt.executeQuery();
            
            while(rs.next()){
                System.out.println("Abonnement Template: id = "+rs.getInt(1)+" | date début = "+rs.getDate(2)+" | date fin = "+rs.getDate(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonnementTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public LinkedHashMap<String, Integer> getAbonnementTemplateByDate(String date){
        try {
            PreparedStatement pt= c.prepareStatement("select * from abonnementtemplate where date_fin >= ?");
            pt.setString(1, date);
            ResultSet rs = pt.executeQuery();
            LinkedHashMap<String, Integer> dates = new LinkedHashMap<>();
            dates.put("Choisir une période", 0);
            //ArrayList<String> dates = new ArrayList<String>();
            //dates.add("Choisir une période");
            while(rs.next()){
                dates.put(rs.getDate(2)+" à "+rs.getDate(3), rs.getInt(1));
                //dates.add(rs.getDate(2)+" à "+rs.getDate(3));
                System.out.println("Abonnement Template: id = "+rs.getInt(1)+" | date début = "+rs.getDate(2)+" | date fin = "+rs.getDate(3));
            }
            return dates;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonnementTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public String getPeriodeById(int id){
        try {
            PreparedStatement pt= c.prepareStatement("select * from abonnementtemplate where id = ?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            rs.next();
            return rs.getDate(2)+" à "+rs.getDate(3);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonnementTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public AbonnementTemplate getAbonnementTemplateById(int id){
        try {
            PreparedStatement pt= c.prepareStatement("select * from abonnementtemplate where id = ?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            rs.next();
            return new AbonnementTemplate(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getInt(4),  rs.getInt(5),  rs.getInt(6),  rs.getInt(7),  rs.getInt(8),  rs.getInt(9));
        } catch (SQLException ex) {
            Logger.getLogger(ServiceAbonnementTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
