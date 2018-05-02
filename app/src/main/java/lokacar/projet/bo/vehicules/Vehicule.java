package lokacar.projet.bo.vehicules;

import android.os.Parcel;
import android.os.Parcelable;

public class Vehicule implements Parcelable {
    private Integer id;
    private String marque, modele, immatriculation, type;
    private float prixJour;

    public Vehicule(String marque, String modele, String immatriculation, float prixJour, String type) {
        this.marque = marque;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.prixJour = prixJour;
        this.type = type;
    }

    public Vehicule(Integer id, String marque, String modele, String immatriculation, float prixJour, String type) {
        this.id = id;
        this.marque = marque;
        this.modele = modele;
        this.immatriculation = immatriculation;
        this.prixJour = prixJour;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrixJour() {
        return prixJour;
    }

    public void setPrixJour(float prixJour) {
        this.prixJour = prixJour;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
    protected Vehicule(Parcel in) {
        marque = in.readString();
        modele = in.readString();
        immatriculation = in.readString();
        prixJour = in.readInt();
    }
    public static final Creator<Vehicule> CREATOR = new Creator<Vehicule>() {
        @Override
        public Vehicule createFromParcel(Parcel in) {
            return new Vehicule(in);
        }

        @Override
        public Vehicule[] newArray(int size) {
            return new Vehicule[size];
        }
    };

}
