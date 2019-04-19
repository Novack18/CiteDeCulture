/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author I.O.I
 */
public class GroupeArtistes {
    private int id;
    private Artiste id_chef_groupe;
    private int chefGroupeId;
    private String nom;
    private String type;

    public GroupeArtistes(int id, int chefGroupeId, String nom, String type) {
        this.id = id;
        this.chefGroupeId = chefGroupeId;
        this.nom = nom;
        this.type = type;
    }

    public GroupeArtistes(int id, Artiste id_chef_groupe, String nom, String type) {
        this.id = id;
        this.id_chef_groupe = id_chef_groupe;
        this.nom = nom;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Artiste getId_chef_groupe() {
        return id_chef_groupe;
    }

    public void setId_chef_groupe(Artiste id_chef_groupe) {
        this.id_chef_groupe = id_chef_groupe;
    }

    public int getChefGroupeId() {
        return chefGroupeId;
    }

    public void setChefGroupeId(int chefGroupeId) {
        this.chefGroupeId = chefGroupeId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
