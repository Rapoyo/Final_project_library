package com.excilys.librarymanager.modele;

public class Membre{
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String telephone;
    private Abonnement abonnement;

    public Membre(int id, String nom, String prenom, String adresse, String email, String telephone, Abonnement abonnement){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.email=email;
        this.telephone=telephone;
        this.abonnement=abonnement;
    }
}