package fr.eni.bo;

import lombok.Data;

@Data
public class Retrait {
    private String rue;
    private String codePostal;
    private String ville;

    public Retrait(){
    }

    public Retrait(String rue, String codePostal, String ville) {
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }
}
