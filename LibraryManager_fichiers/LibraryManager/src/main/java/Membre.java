public class Membre{
    int id;
    String nom;
    String prenom;
    String adresse;
    String email;
    String telephone;
    Abonnement abonnement;

    Membre(int id, String nom, String prenom, String adresse, String email, String telephone, Abonnement abonnement){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.adresse=adresse;
        this.email=email;
        this.telephone=telephone;
        this.abonnement=abonnement;
    }
}