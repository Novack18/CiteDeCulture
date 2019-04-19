/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author MON ASUS
 */
public class Foire {
    
    private String nom;
    private int id;
    private Date date_debut;
    private Date date_fin;
    private int nombreDeStand;
    private int PrixDuStand;
    private int Categorie_id;
    
    
    public Foire (int id,String nom,Date date_debut,Date date_fin,int nombreDeStand,int PrixDuStand,int Categorie_id)
    {
        this.id=id;
        this.nom=nom;
        this.date_debut=date_debut;
        this.date_fin= date_fin;
        this.nombreDeStand=nombreDeStand;
        this.PrixDuStand= PrixDuStand;
        this.Categorie_id= Categorie_id;
        
       
    }
    
    
    public Foire (String nom,Date date_debut,Date date_fin,int nombreDeStand,int PrixDuStand,int Categorie_id)
    {
        this.nom=nom;
        this.date_debut=date_debut;
        this.date_fin= date_fin;
        this.nombreDeStand=nombreDeStand;
        this.PrixDuStand= PrixDuStand;
        this.Categorie_id= Categorie_id;
        
       
    }
    
    
    public Foire (int id,int nombreDeStand)
    {
        this.id=id;
       
        this.nombreDeStand=nombreDeStand;
        
        
       
    }
    
    public Foire()
    {
        
    }

    public String getNom() {
        return nom;
    }
    


    public int getId() {
        return id;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public int getNombreDeStand() {
        return nombreDeStand;
    }

    public int getPrixDuStand() {
        return PrixDuStand;
    }

    public int getCategorie_id() {
        return Categorie_id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setNombreDeStand(int nombreDeStand) {
        this.nombreDeStand = nombreDeStand;
    }

    public void setPrixDuStand(int PrixDuStand) {
        this.PrixDuStand = PrixDuStand;
    }

    public void setCategorie_id(int Categorie_id) {
        this.Categorie_id = Categorie_id;
    }
    
    
    
    
}
