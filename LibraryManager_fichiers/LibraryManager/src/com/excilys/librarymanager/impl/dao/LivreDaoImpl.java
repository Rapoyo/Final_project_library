package com.excilys.librarymanager.impl.dao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import com.excilys.librarymanager.persistence.ConnectionManager;
import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.interfaces.dao.LivreDao;
import com.excilys.librarymanager.modele.Livre;

public class LivreDaoImpl implements LivreDao {
    private static LivreDaoImpl instance;
    
    public static LivreDaoImpl getInstance(){
        if (instance == null) instance = new LivreDaoImpl();
        return instance;
    }

    @Override
    public List<Livre> getList() throws DaoException {
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
            throw new DaoException("Erreur dans Livre->getList()");
        }
    }

    @Override
    public Livre getById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement selectPreparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            selectPreparedStatement = connection.prepareStatement("SELECT id, titre, auteur, isbn FROM livre WHERE id = ?;");
            selectPreparedStatement.setInt(1,id);
            selectPreparedStatement.executeQuery();
            ResultSet result = selectPreparedStatement.getResultSet();
            
            String titre = result.getString("titre");
            String auteur = result.getString("auteur");
            String isbn = result.getString("isbn");
            Livre livre = new Livre(id, titre, auteur, isbn);

            return(livre);

        } catch (Exception e) {
            throw new DaoException("Erreur dans Livre->getById()");
        }
    }

    @Override
    public int create(String titre, String auteur, String isbn) throws DaoException {
        Connection connection = null;
        PreparedStatement insertPreparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            insertPreparedStatement = connection.prepareStatement("INSERT INTO livre(titre, auteur, isbn) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            insertPreparedStatement.setString(1,titre);
            insertPreparedStatement.setString(2,auteur);
            insertPreparedStatement.setString(3,isbn);
            insertPreparedStatement.executeUpdate();

            ResultSet result = insertPreparedStatement.getGeneratedKeys();
            if (result.next()) return result.getInt(1);
            return(-1);

        } catch (Exception e) {
            throw new DaoException("Erreur dans Livre->create()");
        } 
    }

    @Override
    public void update(Livre livre) throws DaoException {
        Connection connection = null;
        PreparedStatement updatePreparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            updatePreparedStatement = connection.prepareStatement("UPDATE livre SET titre = ?, auteur = ?, isbn = ? WHERE id = ?;");
            updatePreparedStatement.setString(1,livre.getTitre());
            updatePreparedStatement.setString(2,livre.getAuteur());
            updatePreparedStatement.setString(3,livre.getIsnb());
            updatePreparedStatement.setInt(4,livre.getId());
            updatePreparedStatement.executeUpdate();
            
        } catch (Exception e) {
            throw new DaoException("Erreur dans Livre->update()");
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement deletePreparedStatement = null;
        try {
            connection = ConnectionManager.getConnection();
            deletePreparedStatement = connection.prepareStatement("DELETE FROM livre WHERE id = ?;");
            deletePreparedStatement.setInt(1,id);
            deletePreparedStatement.executeUpdate();
            
        } catch (Exception e) {
            throw new DaoException("Erreur dans Livre->delete()");
        }
    }

    @Override
    public int count() throws DaoException {
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = ConnectionManager.getConnection();
            stmt = connection.createStatement();
            stmt.executeQuery("SELECT COUNT(id) AS count FROM livre;");
            ResultSet result = stmt.getResultSet();
            if (result.next()) return result.getInt("count");
            return 0;
            
        } catch (Exception e) {
            throw new DaoException("Erreur dans Livre->count()");
        }
    }
}
