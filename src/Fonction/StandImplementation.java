/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fonction;

import Entity.Foire;
import Entity.Notification;
import Entity.Stand;
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
public class StandImplementation implements StandInterface {
Connection c= ConnexionSingleton.getInstance().getCnx();

private static StandImplementation instance;
    private Statement st;
    private ResultSet rs;
    
    public StandImplementation() {
        ConnexionSingleton cs=ConnexionSingleton.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static StandImplementation getInstance(){
        if(instance==null) 
            instance=new StandImplementation();
        return instance;
    }
    
    
    @Override
    public void DemandeDeLocationDeStand(Stand s) {
        try {
            /*Statement st = c.createStatement();
            String req = "insert into fos_user values("+u.getId()+",'"+u.getUsername()+"','"+u.getUsername_canonical()+"','"+u.getEmail()+"','"
                    +u.getEmail_canonical()+"',"+u.getEnabled()+",'"+u.getSalt()+"','"+u.getPassword()+"',"+u.getLast_login()+",'"
                    +u.getConfirmation_token()+"',"+u.getPassword_requested_at()+",'"+u.getRoles()+"')";
            st.executeUpdate(req);*/
            
            
            //PreparedStatement pt = c.prepareStatement("insert into Stand(user_id,foire_id,etatde_reponse values(?,?,?)");
         String req="INSERT INTO stand(user_id,foire_id,etatde_reponse) VALUES(?,?,?)";
         PreparedStatement pt=c.prepareStatement(req);
       
            pt.setInt(1, s.getUser_id());
            pt.setInt(2, s.getFoire_id());
            pt.setString(3,s.getEtat_De_reponse());
            
           
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFOSUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<String> AfficherNomPersonne(int a) {
        String nom;
        
         String req="select username from fos_user where id='"+a+"' ";
        
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
    public List<Stand> AfficherDemande(String a) {
        
      int b=0;
      List<Stand> list=new ArrayList<Stand>();
      //SELECT c.id, c.nom, i.item FROM catalog_has_items ci INNER JOIN catalog c ON c.id=ci.catalog_id INNER JOIN items i ON i.id=ci.item_id
        //String req="select id from categorie where etatde_reponse='"+a+"' ";
        String req="select f.username,a.nom_foire,f.email,s.id,a.nombre_de_stand from stand s inner join fos_user f on f.id=s.user_id inner join foire a on a.id=s.foire_id where s.etatde_reponse='"+a+"' ";
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Stand s=new Stand();
                s.setUtilisateur_nom(rs.getString(1));
                s.setFoire_nom(rs.getString(2));
                s.setEmail(rs.getString(3));
                s.setId(rs.getInt(4));
                s.setNombreDeStand(rs.getInt(5));
             list.add(s);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
      return list;
    }

    @Override
    public void AccepterLocation(Stand s) {
        
        try {
           String req = "UPDATE stand set etatde_reponse=? WHERE id=? ";
         
         PreparedStatement pt=c.prepareStatement(req);
       
            
            pt.setString(1,s.getEtat_De_reponse());
            pt.setInt(2,s.getId());
            
           
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFOSUser.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
        
        
        
        
        
    }

    @Override
    public void RefuserLocation(int a) {
        try {
           String req = "DELETE from stand WHERE id=? ";
         
         PreparedStatement pt=c.prepareStatement(req);
       
            
            pt.setInt(1,a);
            
           
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFOSUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Accepter1Location(Stand s) {
       try {
           String req = "UPDATE stand inner join foire on stand.foire_id=foire.id set foire.nombre_de_stand=?,stand.etatde_reponse=? WHERE stand.id=? ";
        
         PreparedStatement pt=c.prepareStatement(req);
       
         pt.setInt(1,s.getNombreDeStand());
         pt.setString(2,s.getEtat_De_reponse());
         pt.setInt(3,s.getId());
           
           
            
            
           
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceFOSUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<String> Verification(int a) {
        String nom;
         String etat="EnAttente";
        
         
        String req="select a.nom_foire from stand s inner join foire a on a.id=s.foire_id where s.user_id='"+a+"' AND s.etatde_reponse='"+etat+"' "  ;
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
    public List<String> Verification1(int a, String b) {
        String nom;
         String etat="Accepte";
        
         
        String req="select a.nom_foire from stand s inner join foire a on a.id=s.foire_id where s.user_id='"+a+"' AND s.etatde_reponse='"+etat+"' AND a.nom_foire='"+b+"' "  ;
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
    public List<String> ListeDesPersonne(int a) {
        String nom;
        List<String> list=new ArrayList<String>();
        
         String req="select f.username from stand s inner join fos_user f on f.id=s.user_id inner join foire a on a.id=s.foire_id where a.id='"+a+"' ";
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
    public void ajouterNotification(Notification n) {
         String req="insert into notification (title,description,icon,notification_date,seen) values ('"+n.getNom()+"','"+n.getDescription()+"','"+n.getB()+"','"+n.getDate()+"','"+n.getA()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int NombreDeDemand() {
         int a=0;
        String reponse="EnAttente";
        
         String req="select count(*) from stand where etatde_reponse='"+reponse+"' ";
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
              a=rs.getInt(1);
              
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
      return a;
    }

    @Override
    public int Nombre(int a) {
        int b=0;
        //String reponse="EnAttente";
        
         String req="select count(*) from stand where foire_id='"+a+"' ";
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
              b=rs.getInt(1);
              
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
      return b;
    }

    @Override
    public List<Stand> Rechercher(String a) {
                                                                                                                                                  
      int b=0;
      List<Stand> list=new ArrayList<Stand>();
      //SELECT c.id, c.nom, i.item FROM catalog_has_items ci INNER JOIN catalog c ON c.id=ci.catalog_id INNER JOIN items i ON i.id=ci.item_id
        //String req="select id from categorie where etatde_reponse='"+a+"' ";
        String req="select f.username,a.nom_foire,f.email,s.id,a.nombre_de_stand from stand s inner join fos_user f on f.id=s.user_id inner join foire a on a.id=s.foire_id where a.nom_foire LIKE '%"+a+"%' ";
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Stand s=new Stand();
                s.setUtilisateur_nom(rs.getString(1));
                s.setFoire_nom(rs.getString(2));
                s.setEmail(rs.getString(3));
                s.setId(rs.getInt(4));
                s.setNombreDeStand(rs.getInt(5));
             list.add(s);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategorieImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
      return list;
    }

    

    

  

    

   
    }
    






   
    

    
        


    

