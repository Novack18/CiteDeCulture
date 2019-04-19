/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Categorie;
import Fonction.CategorieImplementation;
import java.util.ArrayList;
import static java.util.Collections.list;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MON ASUS
 */
public class List {
    
  private ObservableList<Categorie> list=FXCollections.observableArrayList();
   
        public List() {
        CategorieImplementation pdao=CategorieImplementation.getInstance();
        //list = pdao.AfficherCategorie();
        System.out.println(list);
    }
    
    public ObservableList<Categorie> getCategorie(){
        return list;
    }
}
