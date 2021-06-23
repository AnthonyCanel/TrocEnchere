package fr.eni.bo;

import lombok.Data;

@Data
public class Retrait {
    private int id;
    private String rue;
    private String codePostal;
    private String ville;

    public Retrait(){
    }

    public Retrait(int id, String rue, String codePostal, String ville) {
        this.id = id;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public Retrait(String rue, String codePostal, String ville) {
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }
}
