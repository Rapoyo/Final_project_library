package com.excilys.librarymanager.service_impl;

import java.util.List;
import java.util.ArrayList;

import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.modele.Livre;
import com.excilys.librarymanager.interfaces_service.LivreService;
import com.excilys.librarymanager.impl.LivreDaoImpl;
import com.excilys.librarymanager.service_impl.EmpruntServiceImpl;

public class LivreServiceImpl implements LivreService{

    private static LivreServiceImpl instance;
    
    public static LivreServiceImpl getInstance(){
        if (instance == null) instance = new LivreServiceImpl();
        return instance;
    }

    public List<Livre> getList() throws ServiceException{
        try {
            LivreDaoImpl instance=LivreDaoImpl.getInstance();
            List<Livre> listLivre=instance.getList();
            return(listLivre);
        } catch (Exception e) {
            throw new ServiceException("Error in Livre.getList");
        }
    }

    public List<Livre> getListDispo() throws ServiceException{
        try {
            LivreDaoImpl instance=LivreDaoImpl.getInstance();
            List<Livre> listLivre=instance.getList();
            List<Livre> listPossible=new ArrayList<Livre>();
            for (int i=0; i<listLivre.size(); i++){
                Livre current=listLivre.get(i);
                EmpruntServiceImpl aux=EmpruntServiceImpl.getInstance();
                if (aux.isLivreDispo(current.getId())) listPossible.add(current);
            }
            return(listPossible);
        } catch (Exception e) {
            throw new ServiceException("Error in Livre.getListDispo");
        }
    }

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
            if (titre==null || titre.equals("")) throw new ServiceException("Titre du livre vide");
            LivreDaoImpl instance=LivreDaoImpl.getInstance();
            return(instance.create(titre, auteur, isbn));
        } catch (Exception e) {
            throw new ServiceException("Error in Livre.create");
        }
    }

    public void update(Livre livre) throws ServiceException{
        try {
            if (livre.getTitre()==null || livre.getTitre().equals("")) throw new ServiceException("Titre du livre vide");
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