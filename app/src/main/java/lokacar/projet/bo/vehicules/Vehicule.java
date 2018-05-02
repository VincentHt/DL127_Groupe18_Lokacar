package lokacar.projet.bo.vehicules;

import android.os.Parcel;
import android.os.Parcelable;

public class Vehicule implements Parcelable {

    private int id;
    private String marque;
    private String modele;
    private String cnit;
    private int idPhoto;
    private int idAgence;
    private boolean estLouable;
    private String description;

    public Vehicule(String marque, String modele, String cnit, int idPhoto, int idAgence, boolean estLouable, String description) {
        this.marque = marque;
        this.modele = modele;
        this.cnit = cnit;
        this.idPhoto = idPhoto;
        this.idAgence = idAgence;
        this.estLouable = estLouable;
        this.description = description;
    }

    public Vehicule(int id, String marque, String modele){
        this.id = id;
        this.marque = marque;
        this.modele = modele;
    }

    public Vehicule(){
    }

    protected Vehicule(Parcel in) {
        id = in.readInt();
        marque = in.readString();
        modele = in.readString();
        cnit = in.readString();
        idAgence = in.readInt();
        idPhoto = in.readInt();
        estLouable = in.readInt() == 1;
        description = in.readString();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getCnit() {
        return cnit;
    }

    public void setCnit(String cnit) {
        this.cnit = cnit;
    }

    public int getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(int idPhoto) {
        this.idPhoto = idPhoto;
    }

    public int getIdAgence() {
        return idAgence;
    }

    public void setIdAgence(int idAgence) {
        this.idAgence = idAgence;
    }

    public boolean isEstLouable() {
        return estLouable;
    }

    public void setEstLouable(boolean estLouable) {
        this.estLouable = estLouable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "id=" + id +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", cnit='" + cnit + '\'' +
                ", idPhoto=" + idPhoto +
                ", idAgence=" + idAgence +
                ", estLouable=" + estLouable +
                ", description='" + description + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(marque);
        dest.writeString(modele);
        dest.writeString(cnit);
        dest.writeInt(idAgence);
        dest.writeInt(idPhoto);
        if(estLouable){
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
        }
        dest.writeString(description);
    }
}
