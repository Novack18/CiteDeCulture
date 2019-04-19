/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fonction;

import Entity.Categorie;
import Entity.Foire;
import Utils.ConnexionSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MON ASUS
 */
public class FoireImplementation implements FoireInterface {
    Connection c= ConnexionSingleton.getInstance().getCnx();
    private static FoireImplementation instance;
    private Statement st;
    private ResultSet rs;
    
    public FoireImplementation() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static FoireImplementation getInstance(){
        if(instance==null) 
            instance  =new FoireImplementation();
        return instance;
    }

    @Override
    public void ajouterFoire(Foire f) {
       
        try {
             String req="INSERT INTO foire (nom_foire,date_debut,date_fin,nombre_de_stand,prix_du_stand,categorie_id) VALUES(?,?,?,?,?,?)";
         PreparedStatement pt;
            pt = c.prepareStatement(req);
            pt.setString(1, f.getNom());
            pt.setDate(2,f.getDate_debut());
            pt.setDate(3,f.getDate_fin());
            pt.setInt(4,f.getNombreDeStand());
            pt.setInt(5, f.getPrixDuStand());
            pt.setInt(6,f.getCategorie_id());
           
            
           
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FoireImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
       
            
        } 

    @Override
    public boolean modifierFoire(Foire f) {
      
      try {
           String req = "UPDATE foire set nom_foire=?,date_debut=?,date_fin=?,nombre_de_stand=?,prix_du_stand=?,categorie_id=? WHERE id=? ";
         
         PreparedStatement pt=c.prepareStatement(req);
        pt.setString(1, f.getNom());
            pt.setDate(2,f.getDate_debut());
            pt.setDate(3,f.getDate_fin());
            pt.setInt(4,f.getNombreDeStand());
            pt.setInt(5, f.getPrixDuStand());
            pt.setInt(6,f.getCategorie_id());
            pt.setInt(7,f.getId());
            
            
            
           
            pt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFOSUser.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
        return false;
    }

    @Override
    public void supprimer(Foire F) {
        String req="delete from foire where id="+F.getId();
        
        
          
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Foire> AfficherFoire() {
         String req="select * from foire";
       List<Foire> list =new ArrayList<Foire>();  
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Foire f = new Foire();
                f.setId(rs.getInt(1));
                f.setNom(rs.getString(2));
                f.setDate_debut(rs.getDate(3));
                f.setDate_fin(rs.getDate(4));
                f.setNombreDeStand(rs.getInt(5));
                f.setPrixDuStand(rs.getInt(6));
                f.setCategorie_id(rs.getInt(7));
                list.add(f);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<String> rechercher(int a) {
        String nom;
        String req="select nom_foire from foire where id='"+a+"' "  ;
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
    public int categorie(String a) {
         //String nom;
        int b=0;
         
        String req="select id from categorie where nom_categorie='"+a+"'";
         
        
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
