package com.excilys.librarymanager.impl.dao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import com.excilys.librarymanager.persistence.ConnectionManager;
import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.interfaces.dao.MembreDao;
import com.excilys.librarymanager.modele.Abonnement;
import com.excilys.librarymanager.modele.Membre;

public class MembreDaoImpl implements MembreDao {
    private static MembreDaoImpl instance;
    
    public static MembreDaoImpl getInstance(){
        if (instance == null) instance = new MembreDaoImpl();
        return instance;
    }

    @Override
    public List<Membre> getList() throws DaoException {
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.createStatement();
            stmt.executeQuery("SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre ORDER BY nom, prenom;");
            ResultSet result = stmt.getResultSet();

            ArrayList<Membre> listMembre = new ArrayList<>();
            while (result.next()) {
                int id = result.getInt("id");
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String adress = result.getString("adresse");
                String email = result.getString("email");
                String telephone = result.getString("telephone");
                String abo = result.getString("abonnement");
                Abonnement abonnement;
                if (abo.equals("VIP")) abonnement=Abonnement.VIP;
                else if (abo.equals("BASIC")) abonnement=Abonnement.BASIC;
                else abonnement=Abonnement.PREMIUM;
                Membre membre = new Membre(id,nom,prenom,adress,email,telephone,abonnement);

                listMembre.add(membre);
            }

            return(listMembre);

        } catch (Exception e) {
            throw new DaoException("Erreur dans Membre->getList()");
        }
    }

    @Override
    public Membre getById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement selectPreparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            selectPreparedStatement = connection.prepareStatement("SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre WHERE id = ?;");
            selectPreparedStatement.setInt(1,id);
            selectPreparedStatement.executeQuery();
            ResultSet result = selectPreparedStatement.getResultSet();
            
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            String adress = result.getString("adresse");
            String email = result.getString("email");
            String telephone = result.getString("telephone");
            String abo=result.getString("abonnement");
            Abonnement abonnement;
            if (abo.equals("VIP")) abonnement=Abonnement.VIP;
            else if (abo.equals("BASIC")) abonnement=Abonnement.BASIC;
            else abonnement=Abonnement.PREMIUM;
            Membre membre = new Membre(id,nom,prenom,adress,email,telephone,abonnement);

            return(membre);

        } catch (Exception e) {
            throw new DaoException("Erreur dans Membre->getById()");
        }
    }

    @Override
    public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException {
        Connection connection = null;
        PreparedStatement insertPreparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            insertPreparedStatement = connection.prepareStatement("INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            insertPreparedStatement.setString(1,nom);
            insertPreparedStatement.setString(2,prenom);
            insertPreparedStatement.setString(3,adresse);
            insertPreparedStatement.setString(4,email);
            insertPreparedStatement.setString(5,telephone);
            insertPreparedStatement.setString(6,"BASIC");
            insertPreparedStatement.executeUpdate();
            
            ResultSet result = insertPreparedStatement.getGeneratedKeys();
            if (result.next()) return result.getInt(1);
            return(-1);

        } catch (Exception e) {
            throw new DaoException("Erreur dans Membre->create()");
        }
    }

    @Override
    public void update(Membre membre) throws DaoException {
        Connection connection = null;
        PreparedStatement updatePreparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            updatePreparedStatement = connection.prepareStatement("UPDATE membre SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ?, abonnement = ? WHERE id = ?;");
            updatePreparedStatement.setString(1,membre.getNom());
            updatePreparedStatement.setString(2,membre.getPrenom());
            updatePreparedStatement.setString(3,membre.getAdresse());
            updatePreparedStatement.setString(4,membre.getEmail());
            updatePreparedStatement.setString(5,membre.getTelephone());
            updatePreparedStatement.setString(6, membre.getAbonnement().toString());
            updatePreparedStatement.setInt(7,membre.getId());
            updatePreparedStatement.executeUpdate();
            
        } catch (Exception e) {
            throw new DaoException("Erreur dans Membre->update()");
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement deletePreparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            deletePreparedStatement = connection.prepareStatement("DELETE FROM membre WHERE id = ?;");
            deletePreparedStatement.setInt(1,id);
            deletePreparedStatement.executeUpdate();
            
        } catch (Exception e) {
            throw new DaoException("Erreur dans Membre->delete()");
        }
    }

    @Override
    public int count() throws DaoException {
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.createStatement();
            stmt.executeQuery("SELECT COUNT(id) AS count FROM membre;");
            ResultSet result = stmt.getResultSet();
            if (result.next()) return result.getInt("count");
            return 0;
            
        } catch (Exception e) {
            throw new DaoException("Erreur dans Membre->count()");
        }
    }
}
