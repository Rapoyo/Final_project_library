package com.excilys.librarymanager.service_impl;

import java.util.List;
import java.util.ArrayList;

import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.modele.Livre;
import com.excilys.librarymanager.interfaces_service.LivreService;
import com.excilys.librarymanager.impl.LivreDaoImpl;

public class LivreServiceImpl implements LivreService{

    public List<Livre> getList() throws ServiceException{
        try {
            LivreDaoImpl instance=LivreDaoImpl.getInstance();
            List<Livre> listLivre=instance.getList();
            return(listLivre);
        } catch (Exception e) {
            throw new ServiceException("Error in Livre.getList");
        }
    }

    //public List<Livre> getListDispo() throws ServiceException; //Cf interface

    public Livre getById(int id) throws ServiceException{
        try {
            LivreDaoImpl instance=LivreDaoImpl.getInstance();
            Livre livre=instance.getById(id);
            return(livre);
        } catch (Exception e) {
            throw new ServiceException("Error in Livre.getById");
        }
    }
    

    public int create(String titre, String auteur, String isbn) throws ServiceException{
        try {
            LivreDaoImpl instance=LivreDaoImpl.getInstance();
            return(instance.create(titre, auteur, isbn));
        } catch (Exception e) {
            throw new ServiceException("Error in Livre.create");
        }
    }

    public void update(Livre livre) throws ServiceException{
        try {
            LivreDaoImpl instance=LivreDaoImpl.getInstance();
            instance.update(livre);
        } catch (Exception e) {
            throw new ServiceException("Error in Livre.update");
        }
    }
    
	public void delete(int id) throws ServiceException{
        try {
            LivreDaoImpl instance=LivreDaoImpl.getInstance();
            instance.delete(id);
        } catch (Exception e) {
            throw new ServiceException("Error in Livre.delete");
        }
    }
	public int count() throws ServiceException{
        try {
            LivreDaoImpl instance=LivreDaoImpl.getInstance();
            return(instance.count());
        } catch (Exception e) {
            throw new ServiceException("Error in Livre.count");
        }
    }
}