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
public class SalleArt {
    private int id;
    private String nom;
    private int capaciter;
    public static String message = "";

    public SalleArt() {
    }

    public SalleArt(int id, String nom, int capaciter) {
        this.id = id;
        this.nom = nom;
        this.capaciter = capaciter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCapaciter() {
        return capaciter;
    }

    public void setCapaciter(int capaciter) {
        this.capaciter = capaciter;
    }
    
}
