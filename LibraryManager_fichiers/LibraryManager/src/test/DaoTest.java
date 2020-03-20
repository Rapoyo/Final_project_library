package test;

import java.time.LocalDate;
import java.util.List;

import com.excilys.librarymanager.exception.DaoException;
import com.excilys.librarymanager.impl.MembreDaoImpl;
import com.excilys.librarymanager.modele.*;

public class DaoTest {
    public static void main(String[] args) {
        MembreDaoImpl object = MembreDaoImpl.getInstance();
        List<Membre> membres = null; 
        try {
            membres = object.getList();
            System.out.println(object.count());
       /* } catch (DaoException e) {
            e.printStackTrace();*/
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        for (Membre m : membres) {
            System.out.println(m.getId() + ", " + m.getNom() + ", " + m.getPrenom() + ", " + m.getAdresse() + ", " + m.getTelephone() + ", " + m.getEmail() + ", " + m.getAbonnement());
        }

        try {
            object.delete(6);
            membres = object.getList();
            System.out.println();
            System.out.println(object.count());
       /* } catch (DaoException e) {
            e.printStackTrace();*/
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        for (Membre m : membres) {
            System.out.println(m.getId() + ", " + m.getNom() + ", " + m.getPrenom() + ", " + m.getAdresse() + ", " + m.getTelephone() + ", " + m.getEmail() + ", " + m.getAbonnement());
        }
    }
}
