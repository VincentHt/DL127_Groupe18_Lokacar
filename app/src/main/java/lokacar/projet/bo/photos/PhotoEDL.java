package lokacar.projet.bo.photos;

public class PhotoEDL {

    private int id;
    private String emplacementFichier;

    public PhotoEDL(){
    }

    public PhotoEDL(String chemin){
        this.emplacementFichier = chemin;
    }

    public PhotoEDL(int id, String chemin){
        this.id = id;
        this.emplacementFichier = chemin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmplacementFichier() {
        return emplacementFichier;
    }

    public void setEmplacementFichier(String emplacementFichier) {
        this.emplacementFichier = emplacementFichier;
    }

    @Override
    public String toString() {
        return "PhotoEDL{" +
                "id=" + id +
                ", emplacementFichier='" + emplacementFichier + '\'' +
                '}';
    }


}
