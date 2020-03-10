package com.excilys.librarymanager.impl;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.interfaces.EmpruntDao;
import com.excilys.librarymanager.modele.Abonnement;
import com.excilys.librarymanager.modele.Emprunt;
import com.excilys.librarymanager.modele.Livre;
import com.excilys.librarymanager.modele.Membre;
import com.excilys.librarymanager.persistence.ConnectionManager;

public class EmpruntDaoImpl implements EmpruntDao {

    @Override
    public List<Emprunt> getList() throws DaoException {
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
            stmt.executeQuery("SELECT e.id AS id, idMembre, nom, prenom, adresse, email," +
            "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour" +
            "FROM emprunt AS e" +
            "INNER JOIN membre ON membre.id = e.idMembre" +
            "INNER JOIN livre ON livre.id = e.idLivre" +
            "ORDER BY dateRetour DESC;");
            
            ResultSet result = stmt.getResultSet();
            
            ArrayList<Emprunt> listEmprunt = new ArrayList<>();
            while (result.next()) {
                int idMembre = result.getInt("idMembre");
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String adresse = result.getString("adresse");
                String email = result.getString("email");
                String telephone = result.getString("telephone");
                Abonnement abonnement = result.getString("abonnement");
                Membre membre = new Membre(idMembre, nom, prenom, adresse, email, telephone, abonnement);


                int idLivre = result.getInt("idLivre");
                String titre = result.getString("titre");
                String auteur = result.getString("auteur");
                String isbn = result.getString("isbn");
                Livre livre = new Livre(idLivre, titre, auteur, isbn);

                int id = result.getInt("id");
                LocalDate dateEmprunt = result.getDate("dateEmprunt").toLocalDate();
                LocalDate dateRetour = result.getDate("dateRetour").toLocalDate();
                Emprunt emprunt = new Emprunt(id, membre, livre, dateEmprunt, dateRetour);
                listEmprunt.add(emprunt);
            }
        
        
        return null;
    }

    @Override
    public List<Emprunt> getListCurrent() throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Emprunt getById(int id) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Emprunt emprunt) throws DaoException {
        // TODO Auto-generated method stub

    }

    @Override
    public int count() throws DaoException {
        // TODO Auto-generated method stub
        return 0;
    }

}