/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fonction;

import Entity.Categorie;
import Entity.Foire;
import java.util.List;

/**
 *
 * @author MON ASUS
 */
public interface FoireInterface {
      public void ajouterFoire(Foire f);
      public boolean modifierFoire(Foire f);
      public void supprimer(Foire F);
      public List<Foire> AfficherFoire();
      public List<String> rechercher(int a);
      public int categorie(String a);
  
    
}
