/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fonction;

import Entity.Foire;
import Entity.Notification;
import Entity.Stand;
import java.util.List;

/**
 *
 * @author MON ASUS
 */
public interface StandInterface {
     public void DemandeDeLocationDeStand(Stand s);
     public List<String> AfficherNomPersonne(int a);
     public List<Stand> AfficherDemande(String a);
     public void AccepterLocation(Stand s);
     public void Accepter1Location(Stand s);
     public void RefuserLocation(int a);
     public List<String> Verification(int a);
     public List<String> Verification1(int a,String b);
     public List<String> ListeDesPersonne(int a);
     public void ajouterNotification(Notification n);
     public int NombreDeDemand();
     public int Nombre(int a);
     public List<Stand> Rechercher(String a);
     
     
}
