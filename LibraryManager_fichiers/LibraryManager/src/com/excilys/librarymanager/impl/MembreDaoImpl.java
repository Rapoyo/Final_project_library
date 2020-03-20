package com.excilys.librarymanager.impl;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;


import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.interfaces.MembreDao;
import com.excilys.librarymanager.modele.Abonnement;
import com.excilys.librarymanager.modele.Membre;
import com.excilys.librarymanager.persistence.ConnectionManager;

public class MembreDaoImpl implements MembreDao {

    @Override
    public List<Membre> getList() throws DaoException {
        // TODO Auto-generated method stub
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.createStatement();

            stmt.executeQuery("SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre ORDER BY nom, prenom;");
            ResultSet result = stmt.getResultSet();
            ArrayList<Membre> listMembre = new ArrayList<>();
            while (result.next()) {
                int id=result.getInt("id");
                String nom = result.getString("nom");
                String prenom = result.getString("prenom");
                String adress = result.getString("adresse");
                String email = result.getString("email");
                String telephone = result.getString("telephone");
                String abo=result.getString("abonnement");
                Abonnement abonnement;
                if (abo.equals("VIP")) abonnement=Abonnement.VIP;
                else if(abo.equals("BASIC")) abonnement=Abonnement.BASIC;
                else abonnement=Abonnement.PREMIUM;
                Membre membre = new Membre(id,nom,prenom,adress,email,telephone,abonnement);

                listMembre.add(membre);
            }

            return(listMembre);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erreur dans Membre getList");
        }
    }

    @Override
    public Membre getById(int id) throws DaoException {
        // TODO Auto-generated method stub
        Connection connection = null;
        PreparedStatement insertPreparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            
            insertPreparedStatement = connection.prepareStatement("SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre WHERE id = ?;");
            insertPreparedStatement.setInt(1,id);

            insertPreparedStatement.executeQuery();
            ResultSet result = insertPreparedStatement.getResultSet();
            
            String nom = result.getString("nom");
            String prenom = result.getString("prenom");
            String adress = result.getString("adresse");
            String email = result.getString("email");
            String telephone = result.getString("telephone");
            String abo=result.getString("abonnement");
            Abonnement abonnement;
            if (abo.equals("VIP")) abonnement=Abonnement.VIP;
            else if(abo.equals("BASIC")) abonnement=Abonnement.BASIC;
            else abonnement=Abonnement.PREMIUM;
            Membre membre = new Membre(id,nom,prenom,adress,email,telephone,abonnement);

            return(membre);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erreur dans Membre getById");
        }
    }

    @Override
    public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException {
        // TODO Auto-generated method stub
        Connection connection = null;
        PreparedStatement insertPreparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            
            insertPreparedStatement = connection.prepareStatement("INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, ?);");
            insertPreparedStatement.setString(1,nom);
            insertPreparedStatement.setString(2,prenom);
            insertPreparedStatement.setString(3,adresse);
            insertPreparedStatement.setString(4,email);
            insertPreparedStatement.setString(5,telephone);
            insertPreparedStatement.setString(6,"BASIC");

            insertPreparedStatement.executeQuery();
            
            return(0);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erreur dans Membre create");
        }
        
    }

    @Override
    public void update(Membre membre) throws DaoException {
        // TODO Auto-generated method stub
        Connection connection = null;
        PreparedStatement insertPreparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            
            insertPreparedStatement = connection.prepareStatement("UPDATE membre SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ?, abonnement = ? WHERE id = ?;");
            insertPreparedStatement.setString(1,membre.getNom());
            insertPreparedStatement.setString(2,membre.getPrenom());
            insertPreparedStatement.setString(3,membre.getAdresse());
            insertPreparedStatement.setString(4,membre.getEmail());
            insertPreparedStatement.setString(5,membre.getTelephone());
            String abo;
            if (membre.getAbonnement()==Abonnement.BASIC) abo="BASIC";
            else if(membre.getAbonnement()==Abonnement.VIP) abo="VIP";
            else abo="PREMIUM";
            insertPreparedStatement.setString(6, abo);
            insertPreparedStatement.setInt(7,membre.getId());

            insertPreparedStatement.executeQuery();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erreur dans Membre update");
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        // TODO Auto-generated method stub
        Connection connection = null;
        PreparedStatement insertPreparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            
            insertPreparedStatement = connection.prepareStatement("DELETE FROM membre WHERE id = ?;");
            insertPreparedStatement.setInt(1,id);

            insertPreparedStatement.executeQuery();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erreur dans Membre delete");
        }
    }

    @Override
    public int count() throws DaoException {
        // TODO Auto-generated method stub
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.createStatement();

            stmt.executeQuery("SELECT COUNT(id) AS count FROM membre;");
            ResultSet result = stmt.getResultSet();
            int count=result.getInt("count");
            return count;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erreur dans Membre count");
        }
    }
    
}