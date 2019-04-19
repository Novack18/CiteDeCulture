/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;


/**
 *
 * @author I.O.I
 */
public class AbonnementTemplate {
    private int id;
    private Date date_debut;
    private Date date_fin;
    private int prix;
    private int nbr_place_cat0;
    private int nbr_place_cat1;
    private int nbr_place_cat2;
    private int nbr_place_cat3;
    private int nbr_place_cat4;

    public AbonnementTemplate(int id, Date date_debut, Date date_fin, int prix, int nbr_place_cat0, int nbr_place_cat1, int nbr_place_cat2, int nbr_place_cat3, int nbr_place_cat4) {
        this.id = id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        this.nbr_place_cat0 = nbr_place_cat0;
        this.nbr_place_cat1 = nbr_place_cat1;
        this.nbr_place_cat2 = nbr_place_cat2;
        this.nbr_place_cat3 = nbr_place_cat3;
        this.nbr_place_cat4 = nbr_place_cat4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getNbr_place_cat0() {
        return nbr_place_cat0;
    }

    public void setNbr_place_cat0(int nbr_place_cat0) {
        this.nbr_place_cat0 = nbr_place_cat0;
    }

    public int getNbr_place_cat1() {
        return nbr_place_cat1;
    }

    public void setNbr_place_cat1(int nbr_place_cat1) {
        this.nbr_place_cat1 = nbr_place_cat1;
    }

    public int getNbr_place_cat2() {
        return nbr_place_cat2;
    }

    public void setNbr_place_cat2(int nbr_place_cat2) {
        this.nbr_place_cat2 = nbr_place_cat2;
    }

    public int getNbr_place_cat3() {
        return nbr_place_cat3;
    }

    public void setNbr_place_cat3(int nbr_place_cat3) {
        this.nbr_place_cat3 = nbr_place_cat3;
    }

    public int getNbr_place_cat4() {
        return nbr_place_cat4;
    }

    public void setNbr_place_cat4(int nbr_place_cat4) {
        this.nbr_place_cat4 = nbr_place_cat4;
    }
    
}
