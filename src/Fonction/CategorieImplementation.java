/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fonction;

import Entity.Categorie;
import Utils.ConnexionSingleton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MON ASUS
 */
public class CategorieImplementation implements CategorieInterface {
    
    
    private static CategorieImplementation instance;
    private Statement st;
    private ResultSet rs;
    
    public CategorieImplementation() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static CategorieImplementation getInstance(){
        if(instance==null) 
            instance=new CategorieImplementation();
        return instance;
    }

    @Override
    public void ajouterCategorie(Categorie c) {
        String req="insert into categorie (nom_categorie,type) values ('"+c.getNom()+"','"+c.getType()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Categorie> AfficherCategorie() {
        
        
        
        String req="select * from categorie";
       List<Categorie> list =new ArrayList<Categorie>();  
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Categorie c = new Categorie();
                c.setId(rs.getInt(1));
                c.setNom(rs.getString(2));
                c.setType(rs.getString(3));
                list.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public boolean modifierCategorie(Categorie c) {
        String qry = "UPDATE categorie SET nom_categorie = '"+c.getNom()+"', type = '"+c.getType()+"' WHERE id = "+c.getId();
        
       try {
            st.executeUpdate(qry);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void supprimer(Categorie c) {
        String req="delete from categorie where id="+c.getId();
        
        
          
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<String> AfficherNomCategorie() {
        
        String nom;
        
         
        String req="select nom_categorie from categorie";
       List<String> list =new ArrayList<>();  
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                nom= rs.getString(1);
                list.add(nom);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
        

    
    }

    @Override
    public int RecupererIdCategorie(String a) {
        int b=0;
        String req="select id from categorie where nom_categorie='"+a+"' ";
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                b= rs.getInt(1);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return b;
    }
    
    
    }