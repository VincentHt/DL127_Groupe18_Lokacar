package lokacar.projet.bo.etatsDesLieux;

import java.util.List;

import lokacar.projet.bo.photos.PhotoEDL;

public class EtatDesLieux {

    private int id;
    private String description;
    private List<PhotoEDL> photosEDL;

    public EtatDesLieux(){}

    public EtatDesLieux(String description){
        this.description = description;
    }

    public EtatDesLieux(String description, List<PhotoEDL> photos){
        this.description = description;
        this.photosEDL = photos;
    }

    public EtatDesLieux(int id, String description, List<PhotoEDL> photos){
        this.id = id;
        this.description = description;
        this.photosEDL = photos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PhotoEDL> getPhotosEDL() {
        return photosEDL;
    }

    public void setPhotosEDL(List<PhotoEDL> photosEDL) {
        this.photosEDL = photosEDL;
    }

    public void addPhotoEDL(PhotoEDL photo){
        this.photosEDL.add(photo);
    }

    public void removePhotoEDL(PhotoEDL photo){
        this.photosEDL.remove(photo);
    }

    @Override
    public String toString() {
        return "EtatDesLieux{" +
                ", description='" + description + '\'' +
                ", photosEDL=" + photosEDL.size() + " photos" +
                '}';
    }
}
