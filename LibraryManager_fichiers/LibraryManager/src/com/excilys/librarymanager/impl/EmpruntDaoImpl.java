package com.excilys.librarymanager.impl;

import java.time.LocalDate;
import java.util.List;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.interfaces.EmpruntDao;
import com.excilys.librarymanager.modele.Emprunt;

public class EmpruntDaoImpl implements EmpruntDao {

    @Override
    public List<Emprunt> getList() throws DaoException {
        // TODO Auto-generated method stub
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