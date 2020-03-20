package com.excilys.librarymanager.modele;

import java.time.LocalDate;

public class Emprunt{
    private int id;
    private Membre idMembre;
    private Livre idLivre;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    public Emprunt(int id, Membre idMembre, Livre idLivre, LocalDate dateEmprunt, LocalDate dateRetour){
        this.id=id;
        this.idMembre=idMembre;
        this.idLivre=idLivre;
        this.dateEmprunt=dateEmprunt;
        this.dateRetour=dateRetour;
    }

}