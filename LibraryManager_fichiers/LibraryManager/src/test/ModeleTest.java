package test;

import java.util.Date;
import com.excilys.librarymanager.modele.*;

public class ModeleTest {

    public static void main(String[] args){
        Membre davy=new Membre(0,"Simeu","The real Davy","In Heaven","davytheboss@godmail.com","00 00 00 00 01",Abonnement.VIP);
        Livre bible=new Livre(1,"The Davy Bible","Obviously Davy","000");
        Emprunt empruntDuPetitDavy=new Emprunt(0,davy,bible,new Date(1998,2,10),new Date(2020,12,31));
        System.out.println(davy);
    }
}