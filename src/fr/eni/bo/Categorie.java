package fr.eni.bo;

import lombok.Data;

@Data
public class Categorie {
    private int noCategorie;
    private String libelle;

    public Categorie(){
    }

    public Categorie(String libelle) {
        this.libelle = libelle;
    }
}
