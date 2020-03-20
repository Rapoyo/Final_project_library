package com.excilys.librarymanager.modele;

public class Livre{
    private int id;
    private String titre;
    private String auteur;
    private String isbn;

    public Livre(int id, String titre, String auteur, String isbn){
        this.id=id;
        this.titre=titre;
        this.auteur=auteur;
        this.isbn=isbn;
    }
}