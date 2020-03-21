package com.excilys.librarymanager.service_impl;

import java.util.List;
import java.util.ArrayList;

import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.modele.Membre;
import com.excilys.librarymanager.interfaces_service.MembreService;
import com.excilys.librarymanager.impl.MembreDaoImpl;

public class MembreServiceImpl implements MembreService{

    public List<Membre> getList() throws ServiceException
    {
        try {
            MembreDaoImpl instance=MembreDaoImpl.getInstance();
            List<Membre> listMembre=instance.getList();
            return(listMembre);
        } catch (Exception e) {
            throw new ServiceException("Error in Membre.getList");
        }
    }

    //public List<Membre> getListMembreEmpruntPossible() throws ServiceException;
    
	public Membre getById(int id) throws ServiceException{
        try {
            MembreDaoImpl instance=MembreDaoImpl.getInstance();
            Membre membre=instance.getById(id);
            return(membre);
        } catch (Exception e) {
            throw new ServiceException("Error in Membre.getById");
        }
    }

	public int create(String nom, String prenom, String adresse, String email, String telephone) throws ServiceException{
        try {
            if (nom==null || nom.equals("") || prenom==null || prenom.equals("")) throw new ServiceException("Nom ou prenom du membre vide");
            MembreDaoImpl instance=MembreDaoImpl.getInstance();
            return(instance.create(nom.toUpperCase(),prenom,adresse,email,telephone));
        } catch (Exception e) {
            throw new ServiceException("Error in Membre.create");
        }
    }

	public void update(Membre membre) throws ServiceException{
        try {
            if (membre.getNom()==null || membre.getNom().equals("") || membre.getPrenom()==null || membre.getPrenom().equals("")) throw new ServiceException("Nom ou prenom du membre vide");
            MembreDaoImpl instance=MembreDaoImpl.getInstance();
            membre.setNom(membre.getNom().toUpperCase());
            instance.update(membre);
        } catch (Exception e) {
            throw new ServiceException("Error in Membre.update");
        }
    }
	public void delete(int id) throws ServiceException{
        try {
            MembreDaoImpl instance=MembreDaoImpl.getInstance();
            instance.delete(id);
        } catch (Exception e) {
            throw new ServiceException("Error in Membre.delete");
        }
    }

    public int count() throws ServiceException{
        try {
            MembreDaoImpl instance=MembreDaoImpl.getInstance();
            return(instance.count());
        } catch (Exception e) {
            throw new ServiceException("Error in Membre.count");
        }
    }
    
}