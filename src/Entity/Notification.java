/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;
import java.util.logging.Logger;

/**
 *
 * @author MON ASUS
 */
public class Notification {
    
     private String nom;
    private int id;
    private String description;
    private Date date;
    private int a;
    private String b;
    
    
    public Notification (String nom,String description,Date date,int a,String b)
    {
        this.nom=nom;
        
        this.description=description;
        this.date=date;
        this.a=a;
        this.b=b;
        
    }
    
    
    
    

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public int getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(String b) {
        this.b = b;
    }
  
    
    
    
    
    
    
    
    
    
    
}
