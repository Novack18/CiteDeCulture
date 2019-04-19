/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fonction;

import Entity.Categorie;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author MON ASUS
 */
public interface CategorieInterface {
    public void ajouterCategorie(Categorie c);
    public List<Categorie> AfficherCategorie();
   public boolean modifierCategorie(Categorie c);
   public void supprimer(Categorie c);
   public List<String> AfficherNomCategorie();
   public int RecupererIdCategorie(String a);
   
}
