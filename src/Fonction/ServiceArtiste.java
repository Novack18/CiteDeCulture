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
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author I.O.I
 */
public class ServiceArtiste {
    Connection c = ConnexionSingleton.getInstance().getCnx();
    public void ajouterArtiste(Artiste a){
        try {
            PreparedStatement pt;
            if(a.getGroupeId()==0){
                pt = c.prepareStatement("insert into artiste (nom, prenom, date_naissance, cin) values(?, ?, ?, ?)");
                pt.setString(1, a.getNom());
                pt.setString(2, a.getPrenom());
                pt.setDate(3, a.getDate_naissance());
                pt.setString(4, a.getCin());
            }else{
                pt = c.prepareStatement("insert into artiste (id_groupe, nom, prenom, date_naissance, cin) values(?, ?, ?, ?, ?)");
                pt.setInt(1, a.getGroupeId());
                pt.setString(2, a.getNom());
                pt.setString(3, a.getPrenom());
                pt.setDate(4, a.getDate_naissance());
                pt.setString(5, a.getCin());
            }
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceArtiste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void modifierArtiste(Artiste a){
        try {
            PreparedStatement pt;
            if(a.getGroupeId()==0){
                pt = c.prepareStatement("update artiste set nom = ?, prenom = ?, date_naissance = ?, cin = ? where id = ?");
                pt.setString(1, a.getNom());
                pt.setString(2, a.getPrenom());
                pt.setDate(3, a.getDate_naissance());
                pt.setString(4, a.getCin());
                pt.setInt(5, a.getId());
            }else{
                pt = c.prepareStatement("update artiste set id_groupe = ?,nom = ?, prenom = ?, date_naissance = ?, cin = ? where id = ?");
                pt.setInt(1, a.getGroupeId());
                pt.setString(2, a.getNom());
                pt.setString(3, a.getPrenom());
                pt.setDate(4, a.getDate_naissance());
                pt.setString(5, a.getCin());
                pt.setInt(6, a.getId());
            }
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceArtiste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void supprimerArtiste(int id){
        try {
            PreparedStatement pt = c.prepareStatement("delete from artiste where id = ?");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceArtiste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ObservableList<Artiste> getAllSalles(){
        try {
            PreparedStatement pt= c.prepareStatement("select * from artiste");
            ResultSet rs = pt.executeQuery();
            ObservableList<Artiste> oblist = FXCollections.observableArrayList();
            while(rs.next()){
                oblist.add(new Artiste(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6)));
                System.out.println("Atiste : id = "+rs.getInt(1)+" | nom = "+rs.getString(2)+" | date = "+rs.getDate(5));
            }
            return oblist;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceArtiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ObservableList getAllGroupsNames(){
        System.out.println("aaa");
        try {
            System.out.println("here");
            PreparedStatement pt= c.prepareStatement("select * from groupe_artistes");
            ResultSet rs = pt.executeQuery();
            ObservableList groupsNames = FXCollections.observableArrayList();
            groupsNames.add("Choisir un groupe");
            System.out.println("there");
            while(rs.next()){
                groupsNames.add(rs.getString(3));
                System.out.println(rs.getString(3));
            }
            return groupsNames;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceArtiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public boolean checkCIN(String cin){
        try {
            PreparedStatement pt= c.prepareStatement("select * from artiste where cin = ?");
            pt.setString(1, cin);
            ResultSet rs = pt.executeQuery();
            if(rs.next())
                return false;
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceArtiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    public int getGroupIdByName(String nom){
        try {
            PreparedStatement pt= c.prepareStatement("select * from groupe_artistes where nom = ?");
            pt.setString(1, nom);
            ResultSet rs = pt.executeQuery();
            if(rs.next())
                return rs.getInt(1);
            return 0;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceArtiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public ObservableList getAllArtisteIds(){
        try {
            PreparedStatement pt= c.prepareStatement("select * from artiste");
            ResultSet rs = pt.executeQuery();
            ObservableList salleNames = FXCollections.observableArrayList();
            salleNames.add("choisir un identifiant");
            while(rs.next()){
                salleNames.add(rs.getInt(1));
                System.out.println("Artiste: id = "+rs.getInt(1)+" | nom = "+rs.getString(3));
            }
            return salleNames;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceArtiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Artiste getArtisteById(int id){
        try {
            PreparedStatement pt= c.prepareStatement("select * from artiste where id = ?");
            pt.setInt(1, id);
            ResultSet rs = pt.executeQuery();
            rs.next();
            Artiste a = new Artiste(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6));
            return a;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceArtiste.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
