package lokacar.projet.bo.locations;

import java.util.Date;

import lokacar.projet.bo.clients.Client;
import lokacar.projet.bo.etatsDesLieux.EtatDesLieux;
import lokacar.projet.bo.vehicules.Vehicule;

public class Location {

    private int id;
    private Date dateDebut;
    private Date dateFin;
    private Vehicule vehicule;
    private EtatDesLieux edlDebut;
    private EtatDesLieux edlFin;
    private Client client;

    public Location() {
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
