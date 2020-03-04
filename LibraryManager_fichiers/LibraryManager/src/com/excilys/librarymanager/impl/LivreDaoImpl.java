package com.excilys.librarymanager.impl;

import java.util.List;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.interfaces.LivreDao;
import com.excilys.librarymanager.modele.Livre;

public class LivreDaoImpl implements LivreDao {

    @Override
    public List<Livre> getList() throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Livre getById(int id) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int create(String titre, String auteur, String isbn) throws DaoException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void update(Livre livre) throws DaoException {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(int id) throws DaoException {
        // TODO Auto-generated method stub

    }

    @Override
    public int count() throws DaoException {
        // TODO Auto-generated method stub
        return 0;
    }

}