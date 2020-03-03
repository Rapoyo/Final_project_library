package test;

import java.util.Date;
import class_dir.*;

public class ModeleTest {

    public static void main(String[] args){
        Membre Davy=new Membre(0,"Simeu","The real Davy","In Heaven","davytheboss@godmail.com","00 00 00 00 01",Abonnement.VIP);
        Livre Bible=new Livre(1,"The Davy Bible","Obviously Davy","000");
        Emprunt EmpruntDuPetitDavy=new Emprunt(0,Davy,Bible,Date(1998,2,10),Date(2020,12,31));
    }
}