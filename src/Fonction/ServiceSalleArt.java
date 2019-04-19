/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fonction;

import Entity.SalleArt;
import Utils.ConnexionSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author I.O.I
 */
public class ServiceSalleArt {
    Connection c = ConnexionSingleton.getInstance().getCnx();
    
    public void ajouterSalle(SalleArt s){
        try {
            PreparedStatement pt = c.prepareStatement("insert into salle_art (nom, capaciter) values(?, ?)");
            pt.setString(1, s.getNom());
            pt.setInt(2, s.getCapaciter());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSalleArt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifierSalle(SalleArt s){
        try {
            PreparedStatement pt = c.prepareStatement("update salle_art set nom = ?, capaciter = ? where id = ?");
            pt.setString(1, s.getNom());
            pt.setInt(2, s.getCapaciter());
            pt.setInt(3, s.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSalleArt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerSalle(int id){
        try {
            PreparedStatement pt = c.prepareStatement("delete from salle_art where id = ?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSalleArt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void afficherSalles(){
        try {
            PreparedStatement pt= c.prepareStatement("select * from salle_art");
            ResultSet rs = pt.executeQuery();
            
            while(rs.next()){
                System.out.println("Salle art: id = "+rs.getInt(1)+" | nom = "+rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSalleArt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ObservableList<SalleArt> getAllSalles(){
        try {
            PreparedStatement pt= c.prepareStatement("select * from salle_art");
            ResultSet rs = pt.executeQuery();
            ObservableList<SalleArt> oblist = FXCollections.observableArrayList();
            while(rs.next()){
                oblist.add(new SalleArt(rs.getInt(1), rs.getString(2), rs.getInt(3)));
                System.out.println("Salle art: id = "+rs.getInt(1)+" | nom = "+rs.getString(2));
            }
            return oblist;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSalleArt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ObservableList getAllSalleIds(){
        try {
            PreparedStatement pt= c.prepareStatement("select * from salle_art");
            ResultSet rs = pt.executeQuery();
            ObservableList salleNames = FXCollections.observableArrayList();
            salleNames.add("sélectionner un identifiant");
            while(rs.next()){
                salleNames.add(rs.getInt(1));
                System.out.println("Salle art: id = "+rs.getInt(1)+" | nom = "+rs.getString(2));
            }
            return salleNames;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSalleArt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public SalleArt getSalleById(int id){
        try {
            PreparedStatement pt= c.prepareStatement("select * from salle_art where id = ?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            rs.next();
            SalleArt s = new SalleArt(rs.getInt(1), rs.getString(2), rs.getInt(3));
            return s;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSalleArt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public boolean checkSalleName(String nom){
        try {
            PreparedStatement pt= c.prepareStatement("select * from salle_art where LOWER(nom) = LOWER(?)");
            pt.setString(1, nom);
            ResultSet rs = pt.executeQuery();
            if(rs.next())
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceSalleArt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
