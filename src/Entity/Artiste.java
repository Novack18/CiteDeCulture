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
public class Artiste {
    private int id;
    private GroupeArtistes id_groupe;
    private int groupeId;
    private String nom;
    private String prenom;
    private Date date_naissance;
    private String cin;

    public Artiste(int id, GroupeArtistes id_groupe, String nom, String prenom, Date date_naissance, String cin) {
        this.id = id;
        this.id_groupe = id_groupe;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.cin = cin;
    }
    
    public Artiste(int id, int groupeId, String nom, String prenom, Date date_naissance, String cin) {
        this.id = id;
        this.groupeId = groupeId;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.cin = cin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GroupeArtistes getId_groupe() {
        return id_groupe;
    }

    public void setId_groupe(GroupeArtistes id_groupe) {
        this.id_groupe = id_groupe;
    }

    public int getGroupeId() {
        return groupeId;
    }

    public void setGroupeId(int groupeId) {
        this.groupeId = groupeId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    @Override
    public String toString() {
        return "Artiste{" + "id=" + id + ", id_groupe=" + id_groupe + ", groupeId=" + groupeId + ", nom=" + nom + ", prenom=" + prenom + ", date_naissance=" + date_naissance + ", cin=" + cin + '}';
    }
    
}
