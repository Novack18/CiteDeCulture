/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fonction;

import Entity.Artiste;
import Entity.GroupeArtistes;
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
public class ServiceGroupeArtistes {
    Connection c = ConnexionSingleton.getInstance().getCnx();
    public void ajouterGroupeArtistes(GroupeArtistes g){
        try {
            PreparedStatement pt;
            pt = c.prepareStatement("insert into groupe_artistes (id_chef_groupe, nom, type) values(?, ?, ?)");
            pt.setInt(1, g.getChefGroupeId());
            pt.setString(2, g.getNom());
            pt.setString(3, g.getType());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGroupeArtistes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifierGroupeArtistes(GroupeArtistes g){
        try {
            PreparedStatement pt;
            pt = c.prepareStatement("update groupe_artistes set id_chef_groupe = ?, nom = ?, type = ? where id = ?");
            pt.setInt(1, g.getChefGroupeId());
            pt.setString(2, g.getNom());
            pt.setString(3, g.getType());
            pt.setInt(4, g.getId());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGroupeArtistes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerGroupe(int id){
        try {
            PreparedStatement pt = c.prepareStatement("delete from groupe_artistes where id = ?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGroupeArtistes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ObservableList<GroupeArtistes> getAllGroupesArtistes(){
        try {
            PreparedStatement pt= c.prepareStatement("select * from groupe_artistes");
            ResultSet rs = pt.executeQuery();
            ObservableList<GroupeArtistes> oblist = FXCollections.observableArrayList();
            while(rs.next()){
                oblist.add(new GroupeArtistes(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
                System.out.println("Groupe atistes : id = "+rs.getInt(1)+" | nom = "+rs.getString(3)+" | type = "+rs.getString(4));
            }
            return oblist;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGroupeArtistes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ObservableList getAllArtistes(){
        try {
            PreparedStatement pt= c.prepareStatement("select * from artiste");
            ResultSet rs = pt.executeQuery();
            ObservableList artistesCIN = FXCollections.observableArrayList();
            artistesCIN.add("Choisir un artiste");
            while(rs.next()){
                artistesCIN.add(rs.getString(6));
                System.out.println("CIN = "+rs.getString(6));
            }
            return artistesCIN;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGroupeArtistes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public int getArtisteIdByCIN(String cin){
        try {
            PreparedStatement pt= c.prepareStatement("select * from artiste where cin = ?");
            pt.setString(1, cin);
            ResultSet rs = pt.executeQuery();
            if(rs.next()){
                System.out.println("id cin = "+cin+" id "+rs.getInt(1));
                return rs.getInt(1);}
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGroupeArtistes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public boolean checkName(String nom){
        try {
            PreparedStatement pt= c.prepareStatement("select * from groupe_artistes where nom = ?");
            pt.setString(1, nom);
            ResultSet rs = pt.executeQuery();
            if(rs.next())
                return false;
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGroupeArtistes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    public boolean checkChef(int id){
        try {
            System.out.println("1");
            PreparedStatement pt= c.prepareStatement("select * from groupe_artistes where id_chef_groupe = ?");
            pt.setInt(1, id);
            System.out.println("2");
            ResultSet rs = pt.executeQuery();
            System.out.println("3");
            if(rs.next()){
                System.out.println("4");
                return false;}
            System.out.println("5");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGroupeArtistes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    public ObservableList getAllGroupeIds(){
        try {
            PreparedStatement pt= c.prepareStatement("select * from groupe_artistes");
            ResultSet rs = pt.executeQuery();
            ObservableList groupeIds = FXCollections.observableArrayList();
            groupeIds.add("choisir un groupe");
            while(rs.next()){
                groupeIds.add(rs.getInt(1));
                System.out.println("Groupe : id = "+rs.getInt(1)+" | nom = "+rs.getString(3));
            }
            return groupeIds;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGroupeArtistes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public GroupeArtistes getGroupeById(int id){
        try {
            PreparedStatement pt= c.prepareStatement("select * from groupe_artistes where id = ?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            rs.next();
            GroupeArtistes g = new GroupeArtistes(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));
            return g;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceGroupeArtistes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
