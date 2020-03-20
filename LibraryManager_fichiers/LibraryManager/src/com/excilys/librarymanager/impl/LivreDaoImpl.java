package com.excilys.librarymanager.impl;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.interfaces.LivreDao;
import com.excilys.librarymanager.modele.Livre;
import com.excilys.librarymanager.persistence.ConnectionManager;


public class LivreDaoImpl implements LivreDao {

    @Override
    public List<Livre> getList() throws DaoException {
        // TODO Auto-generated method stub

        Connection connection = null;
        Statement stmt = null;
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.createStatement();

            stmt.executeQuery("SELECT id, titre, auteur, isbn FROM livre;");
            ResultSet result = stmt.getResultSet();
            ArrayList<Livre> listLivre = new ArrayList<>();
            while (result.next()) {
                int id=result.getInt("id");
                String titre = result.getString("titre");
                String auteur = result.getString("auteur");
                String isbn = result.getString("isbn");
                Livre livre = new Livre(id, titre, auteur, isbn);

                listLivre.add(livre);
            }

            return(listLivre);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erreur dans Livre getList");
        }
    }

    @Override
    public Livre getById(int id) throws DaoException {
        // TODO Auto-generated method stub

        Connection connection = null;
        PreparedStatement insertPreparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            
            insertPreparedStatement = connection.prepareStatement("SELECT id, titre, auteur, isbn FROM livre WHERE id = ?;");
            insertPreparedStatement.setInt(1,id);

            insertPreparedStatement.executeQuery();
            ResultSet result = insertPreparedStatement.getResultSet();
            
            String titre = result.getString("titre");
            String auteur = result.getString("auteur");
            String isbn = result.getString("isbn");
            Livre livre = new Livre(id, titre, auteur, isbn);

            return(livre);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erreur dans Livre getById");
        }
    }

    @Override
    public int create(String titre, String auteur, String isbn) throws DaoException {
        // TODO Auto-generated method stub

        Connection connection = null;
        PreparedStatement insertPreparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            
            insertPreparedStatement = connection.prepareStatement("INSERT INTO livre(titre, auteur, isbn) VALUES (?, ?, ?);");
            insertPreparedStatement.setString(1,titre);
            insertPreparedStatement.setString(2,auteur);
            insertPreparedStatement.setString(3,isbn);

            insertPreparedStatement.executeQuery();
            
            return(0);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erreur dans Livre create");
        }
        
    }

    @Override
    public void update(Livre livre) throws DaoException {
        // TODO Auto-generated method stub
        Connection connection = null;
        PreparedStatement insertPreparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            
            insertPreparedStatement = connection.prepareStatement("UPDATE livre SET titre = ?, auteur = ?, isbn = ? WHERE id = ?;");
            insertPreparedStatement.setString(1,livre.getTitre());
            insertPreparedStatement.setString(2,livre.getAuteur());
            insertPreparedStatement.setString(3,livre.getIsnb());
            insertPreparedStatement.setInt(4,livre.getId());

            insertPreparedStatement.executeQuery();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erreur dans Livre update");
        }


    }

    @Override
    public void delete(int id) throws DaoException {
        // TODO Auto-generated method stub

        Connection connection = null;
        PreparedStatement insertPreparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            
            insertPreparedStatement = connection.prepareStatement("DELETE FROM livre WHERE id = ?;");
            insertPreparedStatement.setInt(1,id);

            insertPreparedStatement.executeQuery();
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erreur dans Livre delete");
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

            stmt.executeQuery("SELECT COUNT(id) AS count FROM livre;");
            ResultSet result = stmt.getResultSet();
            int count=result.getInt("count");
            return count;
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erreur dans Livre count");
        }

    }

}