/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author I.O.I
 */
public class ReservationSalle {
    private int id;
    private SalleArt id_salle;
    private GroupeArtistes id_groupe;
    private String type_spectacle;
    private Date date;
    private String titre;

    public ReservationSalle(int id, SalleArt id_salle, GroupeArtistes id_groupe, String type_spectacle, Date date, String titre) {
        this.id = id;
        this.id_salle = id_salle;
        this.id_groupe = id_groupe;
        this.type_spectacle = type_spectacle;
        this.date = date;
        this.titre = titre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SalleArt getId_salle() {
        return id_salle;
    }

    public void setId_salle(SalleArt id_salle) {
        this.id_salle = id_salle;
    }

    public GroupeArtistes getId_groupe() {
        return id_groupe;
    }

    public void setId_groupe(GroupeArtistes id_groupe) {
        this.id_groupe = id_groupe;
    }

    public String getType_spectacle() {
        return type_spectacle;
    }

    public void setType_spectacle(String type_spectacle) {
        this.type_spectacle = type_spectacle;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
    
}
