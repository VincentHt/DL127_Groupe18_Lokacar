package lokacar.projet.bo.locations;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import lokacar.projet.bo.client.Client;
import lokacar.projet.bo.etatsDesLieux.EtatDesLieux;
import lokacar.projet.bo.vehicules.Vehicule;

public class Location implements Parcelable{

    private int id;
    private Date dateDebut;
    private Date dateFin;
    private Vehicule vehicule;
    private EtatDesLieux edlDebut;
    private EtatDesLieux edlFin;
    private Client client;

    public Location() {
    }

    public Location(Date dateDebut, Date dateFin, Vehicule vehicule, Client client) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.vehicule = vehicule;
        this.client = client;
    }

    public Location(int id, Date dateDebut, Date dateFin, Vehicule vehicule, EtatDesLieux edlDebut, Client client) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.vehicule = vehicule;
        this.edlDebut = edlDebut;
        this.client = client;
    }

    public Location(int id, Date dateDebut, Date dateFin, Vehicule vehicule, EtatDesLieux edlDebut, EtatDesLieux edlFin, Client client) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.vehicule = vehicule;
        this.edlDebut = edlDebut;
        this.edlFin = edlFin;
        this.client = client;
    }

    protected Location(Parcel in) {
        id = in.readInt();
        vehicule = in.readParcelable(Vehicule.class.getClassLoader());
        client = in.readParcelable(Client.class.getClassLoader());

        String[] dateDebutTableau = in.readString().split("/");
        setDateDebut(new Date((Integer.parseInt(dateDebutTableau[2])-1900), Integer.parseInt(dateDebutTableau[1])-1, Integer.parseInt(dateDebutTableau[0])+0));

        String[] dateFinTableau = in.readString().split("/");
        setDateFin(new Date((Integer.parseInt(dateFinTableau[2])-1900), Integer.parseInt(dateFinTableau[1])-1, Integer.parseInt(dateFinTableau[0])+0));

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeParcelable(vehicule, flags);
        dest.writeParcelable(client, flags);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        dest.writeString(String.valueOf(sdf.format(dateDebut)));
        dest.writeString(String.valueOf(sdf.format(dateFin)));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public EtatDesLieux getEdlDebut() {
        return edlDebut;
    }

    public void setEdlDebut(EtatDesLieux edlDebut) {
        this.edlDebut = edlDebut;
    }

    public EtatDesLieux getEdlFin() {
        return edlFin;
    }

    public void setEdlFin(EtatDesLieux edlFin) {
        this.edlFin = edlFin;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", vehicule=" + vehicule +
                ", edlDebut=" + edlDebut +
                ", edlFin=" + edlFin +
                ", client=" + client +
                '}';
    }
}
