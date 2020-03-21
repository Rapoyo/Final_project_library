package com.excilys.librarymanager.impl;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.librarymanager.persistence.ConnectionManager;
import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.interfaces.EmpruntDao;
import com.excilys.librarymanager.modele.*;

public class EmpruntDaoImpl implements EmpruntDao {
    private static EmpruntDaoImpl instance;
    
    public static EmpruntDaoImpl getInstance(){
        if (instance == null) instance = new EmpruntDaoImpl();
        return instance;
    }

    @Override
    public List<Emprunt> getList() throws DaoException {
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.createStatement();
            stmt.executeQuery("SELECT e.id AS id, idMembre, nom, prenom, adresse, email, " +
            "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour " +
            "FROM emprunt AS e " +
            "INNER JOIN membre ON membre.id = e.idMembre " +
            "INNER JOIN livre ON livre.id = e.idLivre " +
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
                String abo = result.getString("abonnement");
                Abonnement abonnement;
                if (abo.equals("BASIC")) abonnement = Abonnement.BASIC;
                else if (abo.equals("PREMIUM")) abonnement = Abonnement.PREMIUM;
                else abonnement = Abonnement.VIP;
                Membre membre = new Membre(idMembre, nom, prenom, adresse, email, telephone, abonnement);

                int idLivre = result.getInt("idLivre");
                String titre = result.getString("titre");
                String auteur = result.getString("auteur");
                String isbn = result.getString("isbn");
                Livre livre = new Livre(idLivre, titre, auteur, isbn);

                int id = result.getInt("id");
                LocalDate dateEmprunt = result.getDate("dateEmprunt").toLocalDate();
                Date date = result.getDate("dateRetour");
                LocalDate dateRetour = date == null ? null : date.toLocalDate();
                Emprunt emprunt = new Emprunt(id, membre, livre, dateEmprunt, dateRetour);
                
                listEmprunt.add(emprunt);
            }

            return listEmprunt;

        } catch (Exception e) {
            throw new DaoException("Erreur dans Emprunt->getList()");
        }
    }

    @Override
    public List<Emprunt> getListCurrent() throws DaoException {
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.createStatement();
            stmt.executeQuery("SELECT e.id AS id, idMembre, nom, prenom, adresse, email, " +
            "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour " +
            "FROM emprunt AS e " + 
            "INNER JOIN membre ON membre.id = e.idMembre " +
            "INNER JOIN livre ON livre.id = e.idLivre " +
            "WHERE dateRetour IS NULL;");
            
            ResultSet result = stmt.getResultSet();
            ArrayList<Emprunt> listEmprunt = new ArrayList<>();
            while (result.next()) {
                int idMembre = result.getInt("idMembre");
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String adresse = result.getString("adresse");
                String email = result.getString("email");
                String telephone = result.getString("telephone");
                String abo = result.getString("abonnement");
                Abonnement abonnement;
                if (abo.equals("BASIC")) abonnement = Abonnement.BASIC;
                else if (abo.equals("PREMIUM")) abonnement = Abonnement.PREMIUM;
                else abonnement = Abonnement.VIP;
                Membre membre = new Membre(idMembre, nom, prenom, adresse, email, telephone, abonnement);

                int idLivre = result.getInt("idLivre");
                String titre = result.getString("titre");
                String auteur = result.getString("auteur");
                String isbn = result.getString("isbn");
                Livre livre = new Livre(idLivre, titre, auteur, isbn);

                int id = result.getInt("id");
                LocalDate dateEmprunt = result.getDate("dateEmprunt").toLocalDate();
                Date date = result.getDate("dateRetour");
                LocalDate dateRetour = date == null ? null : date.toLocalDate();
                Emprunt emprunt = new Emprunt(id, membre, livre, dateEmprunt, dateRetour);
                
                listEmprunt.add(emprunt);
            }

            return listEmprunt;

        } catch (Exception e) {
            throw new DaoException("Erreur dans Emprunt->getListCurrent()");
        }
    }

    @Override
    public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement("SELECT e.id AS id, idMembre, nom, prenom, adresse, email, " +
            "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour " +
            "FROM emprunt AS e " + 
            "INNER JOIN membre ON membre.id = e.idMembre " +
            "INNER JOIN livre ON livre.id = e.idLivre " +
            "WHERE dateRetour IS NULL AND membre.id = ?;");
            stmt.setInt(1, idMembre);
            stmt.executeQuery();
            ResultSet result = stmt.getResultSet();

            ArrayList<Emprunt> listEmprunt = new ArrayList<>();
            while (result.next()) {
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String adresse = result.getString("adresse");
                String email = result.getString("email");
                String telephone = result.getString("telephone");
                String abo = result.getString("abonnement");
                Abonnement abonnement;
                if (abo.equals("BASIC")) abonnement = Abonnement.BASIC;
                else if (abo.equals("PREMIUM")) abonnement = Abonnement.PREMIUM;
                else abonnement = Abonnement.VIP;
                Membre membre = new Membre(idMembre, nom, prenom, adresse, email, telephone, abonnement);

                int idLivre = result.getInt("idLivre");
                String titre = result.getString("titre");
                String auteur = result.getString("auteur");
                String isbn = result.getString("isbn");
                Livre livre = new Livre(idLivre, titre, auteur, isbn);

                int id = result.getInt("id");
                LocalDate dateEmprunt = result.getDate("dateEmprunt").toLocalDate();
                Date date = result.getDate("dateRetour");
                LocalDate dateRetour = date == null ? null : date.toLocalDate();
                Emprunt emprunt = new Emprunt(id, membre, livre, dateEmprunt, dateRetour);
                
                listEmprunt.add(emprunt);
            }

            return listEmprunt;

        } catch (Exception e) {
            throw new DaoException("Erreur dans Emprunt->getListCurrentByMembre()");
        }
    }

    @Override
    public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement("SELECT e.id AS id, idMembre, nom, prenom, adresse, email, " +
            "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour " +
            "FROM emprunt AS e " + 
            "INNER JOIN membre ON membre.id = e.idMembre " +
            "INNER JOIN livre ON livre.id = e.idLivre " +
            "WHERE dateRetour IS NULL AND livre.id = ?;");
            stmt.setInt(1, idLivre);
            stmt.executeQuery();
            ResultSet result = stmt.getResultSet();

            ArrayList<Emprunt> listEmprunt = new ArrayList<>();
            while (result.next()) {
                int idMembre = result.getInt("idMembre");
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String adresse = result.getString("adresse");
                String email = result.getString("email");
                String telephone = result.getString("telephone");
                String abo = result.getString("abonnement");
                Abonnement abonnement;
                if (abo.equals("BASIC")) abonnement = Abonnement.BASIC;
                else if (abo.equals("PREMIUM")) abonnement = Abonnement.PREMIUM;
                else abonnement = Abonnement.VIP;
                Membre membre = new Membre(idMembre, nom, prenom, adresse, email, telephone, abonnement);

                String titre = result.getString("titre");
                String auteur = result.getString("auteur");
                String isbn = result.getString("isbn");
                Livre livre = new Livre(idLivre, titre, auteur, isbn);

                int id = result.getInt("id");
                LocalDate dateEmprunt = result.getDate("dateEmprunt").toLocalDate();
                Date date = result.getDate("dateRetour");
                LocalDate dateRetour = date == null ? null : date.toLocalDate();
                Emprunt emprunt = new Emprunt(id, membre, livre, dateEmprunt, dateRetour);
                
                listEmprunt.add(emprunt);
            }

            return listEmprunt;

        } catch (Exception e) {
            throw new DaoException("Erreur dans Emprunt->getListCurrentByLivre()");
        }
    }

    @Override
    public Emprunt getById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.prepareStatement("SELECT e.id AS id, idMembre, nom, prenom, adresse, email, " +
            "telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour " +
            "FROM emprunt AS e " + 
            "INNER JOIN membre ON membre.id = e.idMembre " +
            "INNER JOIN livre ON livre.id = e.idLivre " +
            "WHERE e.id = ?;");
            stmt.setInt(1, id);
            stmt.executeQuery();
            ResultSet result = stmt.getResultSet();

            Emprunt emprunt = null;
            if (result.next()) {
                int idMembre = result.getInt("idMembre");
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String adresse = result.getString("adresse");
                String email = result.getString("email");
                String telephone = result.getString("telephone");
                String abo = result.getString("abonnement");
                Abonnement abonnement;
                if (abo.equals("BASIC")) abonnement = Abonnement.BASIC;
                else if (abo.equals("PREMIUM")) abonnement = Abonnement.PREMIUM;
                else abonnement = Abonnement.VIP;
                Membre membre = new Membre(idMembre, nom, prenom, adresse, email, telephone, abonnement);

                int idLivre = result.getInt("idLivre");
                String titre = result.getString("titre");
                String auteur = result.getString("auteur");
                String isbn = result.getString("isbn");
                Livre livre = new Livre(idLivre, titre, auteur, isbn);

                LocalDate dateEmprunt = result.getDate("dateEmprunt").toLocalDate();
                Date date = result.getDate("dateRetour");
                LocalDate dateRetour = date == null ? null : date.toLocalDate();
                emprunt = new Emprunt(id, membre, livre, dateEmprunt, dateRetour);
            }

            return emprunt;

        } catch (Exception e) {
            throw new DaoException("Erreur dans Emprunt->getById()");
        }
    }

    @Override
    public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException {
        Connection connection = null;
        PreparedStatement insertPreparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            insertPreparedStatement = connection.prepareStatement("INSERT INTO emprunt(idMembre, idLivre, dateEmprunt) VALUES (?, ?, ?);");
            insertPreparedStatement.setInt(1,idMembre);
            insertPreparedStatement.setInt(2, idLivre);
            insertPreparedStatement.setDate(3, Date.valueOf(dateEmprunt));
            insertPreparedStatement.executeQuery();

        } catch (Exception e) {
            throw new DaoException("Erreur dans Emprunt->create()");
        }
    }

    @Override
    public void update(Emprunt emprunt) throws DaoException {
        Connection connection = null;
        PreparedStatement updatePreparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            updatePreparedStatement = connection.prepareStatement("UPDATE emprunt SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ? WHERE id = ?;");
            updatePreparedStatement.setInt(1, emprunt.getIdMembre().getId());
            updatePreparedStatement.setInt(2, emprunt.getIdLivre().getId());
            updatePreparedStatement.setDate(3, Date.valueOf(emprunt.getDateEmprunt()));
            updatePreparedStatement.setDate(4, Date.valueOf(emprunt.getDateRetour()));
            updatePreparedStatement.setInt(5, emprunt.getId());
            updatePreparedStatement.executeQuery();
            
        } catch (Exception e) {
            throw new DaoException("Erreur dans Emprunt->update()");
        }
    }

    @Override
    public int count() throws DaoException {
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.createStatement();
            stmt.executeQuery("SELECT COUNT(id) AS count FROM emprunt;");
            ResultSet result = stmt.getResultSet();
            if (result.next()) return result.getInt("count");
            return 0;

        } catch (Exception e) {
            throw new DaoException("Erreur dans Emprunt->count()");
        }
    }
}
