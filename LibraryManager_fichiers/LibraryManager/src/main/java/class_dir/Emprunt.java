package class_dir;
import java.util.Date;

public class Emprunt{
    int id;
    Membre idMembre;
    Livre idLivre;
    Date dateEmprunt;
    Date dateRetour;

    public Emprunt(int id, Membre idMembre, Livre idLivre, Date dateEmprunt, Date dateRetour){
        this.id=id;
        this.idMembre=idMembre;
        this.idLivre=idLivre;
        this.dateEmprunt=dateEmprunt;
        this.dateRetour=dateRetour;
    }

}