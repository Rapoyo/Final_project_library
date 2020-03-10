package com.excilys.librarymanager.modele;

import java.time.LocalDate;

public class Emprunt{
    int id;
    Membre idMembre;
    Livre idLivre;
    LocalDate dateEmprunt;
    LocalDate dateRetour;

    public Emprunt(int id, Membre idMembre, Livre idLivre, LocalDate dateEmprunt, LocalDate dateRetour){
        this.id=id;
        this.idMembre=idMembre;
        this.idLivre=idLivre;
        this.dateEmprunt=dateEmprunt;
        this.dateRetour=dateRetour;
    }

}