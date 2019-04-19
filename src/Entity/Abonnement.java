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
public class Abonnement {
    private int id;
    private FOSUser user;
    private int userId;
    private String username;
    private AbonnementTemplate abonnementTemplate;
    private int abonnementTemplateId;
    private String range;
    private String categorie;

    public Abonnement(int id, FOSUser user, AbonnementTemplate abonnementTemplate, String categorie) {
        this.id = id;
        this.user = user;
        this.abonnementTemplate = abonnementTemplate;
        this.categorie = categorie;
    }
    public Abonnement(int id, int userId, int abonnementTemplateId, String categorie) {
        this.id = id;
        this.userId = userId;
        this.abonnementTemplateId = abonnementTemplateId;
        this.categorie = categorie;
    }

    public Abonnement(int id, String username, String range, String categorie) {
        this.id = id;
        this.username = username;
        this.range = range;
        this.categorie = categorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FOSUser getUser() {
        return user;
    }

    public void setUser(FOSUser user) {
        this.user = user;
    }

    public AbonnementTemplate getAbonnementTemplate() {
        return abonnementTemplate;
    }

    public void setAbonnementTemplate(AbonnementTemplate abonnementTemplate) {
        this.abonnementTemplate = abonnementTemplate;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAbonnementTemplateId() {
        return abonnementTemplateId;
    }

    public void setAbonnementTemplateId(int abonnementTemplateId) {
        this.abonnementTemplateId = abonnementTemplateId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "id=" + id + ", user=" + user + ", userId=" + userId + ", abonnementTemplate=" + abonnementTemplate + ", abonnementTemplateId=" + abonnementTemplateId + ", categorie=" + categorie + '}';
    }
    
}
