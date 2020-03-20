package test;

import java.time.LocalDate;
import com.excilys.librarymanager.modele.*;

public class ModeleTest {
    public static void main(String[] args){
        Membre davy=new Membre(0,"Simeu","The real Davy","In Heaven","davytheboss@godmail.com","00 00 00 00 01",Abonnement.VIP);
        Livre bible=new Livre(1,"The Davy Bible","Obviously Davy","000");
        Emprunt empruntDuPetitDavy=new Emprunt(0,davy,bible,LocalDate.of(1998,2,10),LocalDate.of(2020,12,31));
        System.out.println(bible);
        System.out.println(davy);
        System.out.println(empruntDuPetitDavy);
        System.out.println(Abonnement.BASIC);
    }
}