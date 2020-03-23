package com.excilys.librarymanager.impl.services;

import java.util.List;
import java.time.LocalDate;


import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.modele.Emprunt;
import com.excilys.librarymanager.interfaces.services.EmpruntService;
import com.excilys.librarymanager.impl.dao.EmpruntDaoImpl;
import com.excilys.librarymanager.modele.Membre;
import com.excilys.librarymanager.modele.Abonnement;;

public class EmpruntServiceImpl implements EmpruntService{

    private static EmpruntServiceImpl instance;
    
    public static EmpruntServiceImpl getInstance(){
        if (instance == null) instance = new EmpruntServiceImpl();
        return instance;
    }


    public List<Emprunt> getList() throws ServiceException{
        try {
            EmpruntDaoImpl instance=EmpruntDaoImpl.getInstance();
            List<Emprunt> listEmprunt=instance.getList();
            return(listEmprunt);
        } catch (Exception e) {
            throw new ServiceException("Error in Emprunt.getList");
        }
    }

    public List<Emprunt> getListCurrent() throws ServiceException{
        try {
            EmpruntDaoImpl instance=EmpruntDaoImpl.getInstance();
            List<Emprunt> listEmprunt=instance.getListCurrent();
            return(listEmprunt);
        } catch (Exception e) {
            throw new ServiceException("Error in Emprunt.getListCurrent");
        }
    }
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException{
        try {
            EmpruntDaoImpl instance=EmpruntDaoImpl.getInstance();
            List<Emprunt> listEmprunt=instance.getListCurrentByMembre(idMembre);
            return(listEmprunt);
        } catch (Exception e) {
            throw new ServiceException("Error in Emprunt.getListCurrentByMembre");
        }
    }
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException{
        try {
            EmpruntDaoImpl instance=EmpruntDaoImpl.getInstance();
            List<Emprunt> listEmprunt=instance.getListCurrentByLivre(idLivre);
            return(listEmprunt);
        } catch (Exception e) {
            throw new ServiceException("Error in Emprunt.getListCurrentByLivre");
        }
    }

	public Emprunt getById(int id) throws ServiceException{
        try {
            EmpruntDaoImpl instance=EmpruntDaoImpl.getInstance();
            Emprunt emprunt=instance.getById(id);
            return(emprunt);
        } catch (Exception e) {
            throw new ServiceException("Error in Emprunt.getById");
        }
    }

	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException{
        try {
            EmpruntDaoImpl instance=EmpruntDaoImpl.getInstance();
            instance.create(idMembre,idLivre,dateEmprunt);
        } catch (Exception e) {
            throw new ServiceException("Error in Emprunt.create");
        }
    }
	public void returnBook(int id) throws ServiceException{
        try {
            EmpruntDaoImpl instance=EmpruntDaoImpl.getInstance();
            Emprunt retour=instance.getById(id);
            retour.setDateRetour(LocalDate.now());
            instance.update(retour);
        } catch (Exception e) {
            throw new ServiceException("Error in Emprunt.returnBook");
        }
    }
	public int count() throws ServiceException{
        try {
            EmpruntDaoImpl instance=EmpruntDaoImpl.getInstance();
            return(instance.count());
        } catch (Exception e) {
            throw new ServiceException("Error in Emprunt.count");
        }
    }
	public boolean isLivreDispo(int idLivre) throws ServiceException{
        try {
            EmpruntDaoImpl instance=EmpruntDaoImpl.getInstance();
            return(instance.getListCurrentByLivre(idLivre).isEmpty());
        } catch (Exception e) {
            throw new ServiceException("Error in Emprunt.isLivreDispo");
        }
    }

	public boolean isEmpruntPossible(Membre membre) throws ServiceException{
        try {
            EmpruntDaoImpl instance=EmpruntDaoImpl.getInstance();
            int nb=instance.getListCurrentByMembre(membre.getId()).size();
            if ((membre.getAbonnement()==Abonnement.BASIC && nb<2) ||(membre.getAbonnement()==Abonnement.VIP && nb<5) || (membre.getAbonnement()==Abonnement.PREMIUM && nb<20)) return true;
            else return false;
        } catch (Exception e) {
            throw new ServiceException("Error in Emprunt.isEmpruntPossible");
        }
    }

}