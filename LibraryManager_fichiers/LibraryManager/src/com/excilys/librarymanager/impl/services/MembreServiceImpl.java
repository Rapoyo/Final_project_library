package com.excilys.librarymanager.impl.services;

import java.util.List;
import java.util.ArrayList;

import com.excilys.librarymanager.exception.ServiceException;
import com.excilys.librarymanager.modele.Membre;
import com.excilys.librarymanager.interfaces.services.MembreService;
import com.excilys.librarymanager.impl.dao.MembreDaoImpl;

public class MembreServiceImpl implements MembreService{

    private static MembreServiceImpl instance;
    
    public static MembreServiceImpl getInstance(){
        if (instance == null) instance = new MembreServiceImpl();
        return instance;
    }

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

    public List<Membre> getListMembreEmpruntPossible() throws ServiceException{
        try {
            MembreDaoImpl instance=MembreDaoImpl.getInstance();
            List<Membre> listMembre=instance.getList();
            List<Membre> listPossible=new ArrayList<Membre>();
            for(int i=0; i<listMembre.size(); i++){
                EmpruntServiceImpl aux=EmpruntServiceImpl.getInstance();
                Membre current=listMembre.get(i);
                if (aux.isEmpruntPossible(current)) listPossible.add(current);
            }
            return(listPossible);
        } catch (Exception e){
            throw new ServiceException("Error in Membre.getListMembreEmpruntPossible");
        }
    }
    
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