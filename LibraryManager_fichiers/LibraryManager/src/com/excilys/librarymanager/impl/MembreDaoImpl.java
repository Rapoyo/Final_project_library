package com.excilys.librarymanager.impl;

import java.util.List;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.interfaces.MembreDao;
import com.excilys.librarymanager.modele.Membre;

public class MembreDaoImpl implements MembreDao {

    @Override
    public List<Membre> getList() throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Membre getById(int id) throws DaoException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void update(Membre membre) throws DaoException {
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