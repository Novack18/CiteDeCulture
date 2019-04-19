/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author MON ASUS
 */
public class Stand {
    
    
   private int id;
   private int user_id;
   private int foire_id;
   private String Etat_De_reponse;
   private String utilisateur_nom;
   private String foire_nom;
   private String email;
   private int nombreDeStand;
   
   
   public Stand()
   {
       
   }
   
   
   public Stand(int id,int user_id,int foire_id,String Etat_De_reponse)
   {
       this.id=id;
       this.user_id=user_id;
       this.foire_id=foire_id;
       this.Etat_De_reponse=Etat_De_reponse;
       
   }
   
    public Stand(int id,int user_id,int foire_id,String Etat_De_reponse,int nombreDeStand)
   {
       this.id=id;
       this.user_id=user_id;
       this.foire_id=foire_id;
       this.Etat_De_reponse=Etat_De_reponse;
       this.nombreDeStand=nombreDeStand;
       
   }
   
   public Stand(int id,int user_id,int foire_id,String Etat_De_reponse,String email)
   {
       this.id=id;
       this.user_id=user_id;
       this.foire_id=foire_id;
       this.Etat_De_reponse=Etat_De_reponse;
       this.email=email;
   }
   
   public Stand(int user_id,int foire_id,String Etat_De_reponse)
   {
       
       this.user_id=user_id;
       this.foire_id=foire_id;
       this.Etat_De_reponse=Etat_De_reponse;
       
   }
   
    public Stand(int user_id,int foire_id,String Etat_De_reponse,String email)
   {
       
       this.user_id=user_id;
       this.foire_id=foire_id;
       this.Etat_De_reponse=Etat_De_reponse;
       this.email=email;
       
   }
    
    public Stand(int user_id,int foire_id,String Etat_De_reponse,String email,int nombreDeStand)
   {
       
       this.user_id=user_id;
       this.foire_id=foire_id;
       this.Etat_De_reponse=Etat_De_reponse;
       this.email=email;
       this.nombreDeStand=nombreDeStand;
       
   }
   
   public Stand(String utilisateur_nom,String foire_nom)
   {
       
       this.utilisateur_nom=utilisateur_nom;
       this.foire_nom=foire_nom;
   }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getFoire_id() {
        return foire_id;
    }

    public String getEmail() {
        return email;
    }

    public String getEtat_De_reponse() {
        return Etat_De_reponse;
    }

    public String getUtilisateur_nom() {
        return utilisateur_nom;
    }

    public int getNombreDeStand() {
        return nombreDeStand;
    }

    public String getFoire_nom() {
        return foire_nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setFoire_id(int foire_id) {
        this.foire_id = foire_id;
    }

    public void setEtat_De_reponse(String Etat_De_reponse) {
        this.Etat_De_reponse = Etat_De_reponse;
    }

    public void setUtilisateur_nom(String utilisateur_nom) {
        this.utilisateur_nom = utilisateur_nom;
    }

    public void setFoire_nom(String foire_nom) {
        this.foire_nom = foire_nom;
    }

    public void setNombreDeStand(int nombreDeStand) {
        this.nombreDeStand = nombreDeStand;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   
   
   
   
   
   
}




