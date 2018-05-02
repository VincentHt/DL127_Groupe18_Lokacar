package lokacar.projet.bo.agence;

public class Agence {

    private String nom;
    private String password;

    public Agence() {
    }

    public Agence(String nom, String password) {
        this.nom = nom;
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Agence{" +
                "nom='" + nom + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
