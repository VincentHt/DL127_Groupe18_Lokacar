package lokacar.projet.dal.locations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import lokacar.projet.bo.clients.Client;
import lokacar.projet.bo.etatsDesLieux.EtatDesLieux;
import lokacar.projet.bo.etatsDesLieux.EtatDesLieuxContract;
import lokacar.projet.bo.locations.Location;
import lokacar.projet.bo.locations.LocationContract;
import lokacar.projet.bo.vehicules.Vehicule;
import lokacar.projet.bo.vehicules.VehiculeContract;
import lokacar.projet.dal.helper.AppDbHelper;
import lokacar.projet.dal.vehicules.VehiculeDAO;

public class LocationDAO {

    private List<Location> listeLocations;
    private AppDbHelper helper;

    public LocationDAO(Context context){
        this.helper = new AppDbHelper(context);
    }

    /**
     * Méthode d'insertion d'une nouvelle location en BDD
     */
    public long insert(int idVehicule, int idClient, String dateDebut, String dateFin){

        SQLiteDatabase db = helper.getWritableDatabase();

        long result = db.insert(LocationContract.TABLE_NAME, null, buildContentValues(idVehicule, idClient, dateDebut, dateFin));

        if(db != null){
            db.close();
        }

        return result;
    }

    /**
     * Méthode d'obtention de l'ensemble des locations ayant cours à la date spécifiée
     */
    public List<Location> getAllByDate(Date today){
        listeLocations = new ArrayList<>();
        Location location = new Location();
        Date dateDebut;
        Date dateFin;

        SQLiteDatabase db = helper.getReadableDatabase();

        /* Requête d'obtention des locations en cours */
        Cursor cursorLocation = db.query(
                LocationContract.TABLE_NAME,
                null,
                LocationContract.COL_DATE_FIN+">=?",
                new String[]{today.toString()},
                null,
                null,
                LocationContract.COL_DATE_FIN);

        /* On parcourt la liste des locations obtenue plus haut */
        if(cursorLocation != null && cursorLocation.moveToFirst()){
            do{
                Integer idLocation = cursorLocation.getInt(cursorLocation.getColumnIndex(LocationContract.COL_ID));
                Integer idVehicule = cursorLocation.getInt(cursorLocation.getColumnIndex(LocationContract.COL_ID_VEHICULE));
                Integer idClient = cursorLocation.getInt(cursorLocation.getColumnIndex(LocationContract.COL_ID_CLIENT));
                String dateDebutString = cursorLocation.getString(cursorLocation.getColumnIndex(LocationContract.COL_DATE_DEBUT));
                String dateFinString = cursorLocation.getString(cursorLocation.getColumnIndex(LocationContract.COL_DATE_FIN));

                /* Récupération de l'état des lieux de début de location */
                /*Cursor cursorEdlDebut = db.query(
                        EtatDesLieuxContract.TABLE_NAME,
                        null,
                        EtatDesLieuxContract.COL_ID_LOCATION + "=? AND " + EtatDesLieuxContract.COL_DEBUT_OU_FIN + "=0",
                        new String[]{String.valueOf(idLocation)},
                        null,
                        null,
                        null
                );

                if(cursorEdlDebut != null && cursorEdlDebut.moveToFirst()){
                    Integer idEdl = cursorEdlDebut.getInt(cursorEdlDebut.getColumnIndex(EtatDesLieuxContract.COL_ID));
                    String descriptionEdl = cursorEdlDebut.getString(cursorEdlDebut.getColumnIndex(EtatDesLieuxContract.COL_COMPTE_RENDU));

                    EtatDesLieux etatDesLieuxDebut = new EtatDesLieux(idEdl, descriptionEdl, null);
                    location.setEdlDebut(etatDesLieuxDebut);
                }*/

                /* Récupération de l'état des lieux de fin de location */
               /* Cursor cursorEdlFin = db.query(
                        EtatDesLieuxContract.TABLE_NAME,
                        null,
                        EtatDesLieuxContract.COL_ID_LOCATION + "=? AND " + EtatDesLieuxContract.COL_DEBUT_OU_FIN + "=1",
                        new String[]{String.valueOf(idLocation)},
                        null,
                        null,
                        null
                );

                if(cursorEdlFin != null && cursorEdlFin.moveToFirst()){
                    Integer idEdl = cursorEdlFin.getInt(cursorEdlFin.getColumnIndex(EtatDesLieuxContract.COL_ID));
                    String descriptionEdl = cursorEdlFin.getString(cursorEdlFin.getColumnIndex(EtatDesLieuxContract.COL_COMPTE_RENDU));

                    EtatDesLieux etatDesLieuxFin = new EtatDesLieux(idEdl, descriptionEdl, null);
                    location.setEdlFin(etatDesLieuxFin);
                }*/

                /* Récupération du véhicule */
                /*Cursor cursorVehicule = db.query(
                        VehiculeContract.TABLE_NAME,
                        null,
                        VehiculeContract.COL_ID + "=?",
                        new String[]{String.valueOf(idVehicule)},
                        null,
                        null,
                        null
                );

                if(cursorVehicule != null && cursorVehicule.moveToFirst()){
                    String marque = cursorVehicule.getString(cursorVehicule.getColumnIndex(VehiculeContract.COL_MARQUE));
                    String modele = cursorVehicule.getString(cursorVehicule.getColumnIndex(VehiculeContract.COL_MODELE));

                    Vehicule vehicule = new Vehicule(idVehicule, marque, modele);
                    location.setVehicule(vehicule);
                }*/
                Vehicule vehicule = new Vehicule(1,"Citroen", "Xsara");
                location.setVehicule(vehicule);

                /* Récupération du client */
                /*Cursor cursorClient = db.query(
                        ClientContract.TABLE_NAME,
                        null,
                        ClientContract.COL_ID + "=?",
                        new String[]{String.valueOf(idClient)},
                        null,
                        null,
                        null
                );

                if(cursorClient != null && cursorClient.moveToFirst()){
                    String idClient = cursorClient.getString(cursorClient.getColumnIndex(ClientContract.COL_ID));
                    String nom = cursorCient.getString(cursorClient.getColumnIndex(ClientContract.COL_NOM));
                    String prenom = cursorClient.getString(cursorClient.getColumnIndex(ClientContract.COL_PRENOM));

                    Client client = new Client(idClient, nom, prenom);

                    location.setVehicule(vehicule);
                }*/

                //TODO Récuper le client associer à la location en BDD - Début partie à modifier
                Client client = new Client(1, "Edmond","BOSAPIN");
                location.setClient(client);
                // Fin de partie à modifier

                /* fermeture des différents cursors relatifs à la location courante */
                /*if(cursorEdlDebut != null){
                    cursorEdlDebut.close();
                }
                if(cursorEdlFin != null){
                    cursorEdlFin.close();
                }
                if(cursorVehicule != null){
                    cursorVehicule.close();
                }*/

                /*if(cursorClient != null){
                    cursorClient.close();
                }*/

                location.setId(idLocation);

                /* Gestion des dates */
                    /* Date de début de location */
                if(dateDebutString != null){
                    String[] dateDebutTableau = dateDebutString.split("-");
                    dateDebut = new Date(2000,1,1);
                } else {
                    dateDebut = new Date(0,0,0);
                }
                location.setDateDebut(dateDebut);

                    /* Date de fin de location */
                if( dateFinString != null){
                    //String[] dateFinTableau = dateFinString.split("-");
                    //dateFin = new Date(Integer.parseInt(dateFinTableau[0]), Integer.parseInt(dateFinTableau[1]), Integer.parseInt(dateFinTableau[2]));
                    dateFin = new Date(2000,1,1);
                } else {
                    dateFin = new Date(0,0,0);
                }
                location.setDateFin(dateFin);

                /* Ajout de la location à la liste demandée */
                listeLocations.add(location);

            }while (cursorLocation.moveToNext());
        }

        /* Fermuture du cursor associé à la requête sur la table LOCATIONS */
        if(cursorLocation != null){
            cursorLocation.close();
        }

        return listeLocations;
    }

    public void insertTest(){
        Client client = new Client(1, "","");
        Vehicule vehicule = new Vehicule(1,"","");

        Location location1 = new Location(new Date("01/01/18"), new Date("01/31/18"),vehicule,client);
        Location location2 = new Location(new Date("01/02/18"), new Date("01/30/18"),vehicule,client);
        Location location3 = new Location(new Date("01/03/18"), new Date("01/29/18"),vehicule,client);
        Location location4 = new Location(new Date("01/04/18"), new Date("01/28/18"),vehicule,client);

        Log.i("test2", String.valueOf(new Date("01/01/18")));

        insert(vehicule.getId(), client.getId(), String.valueOf(new Date("01/01/2018")), String.valueOf(new Date("01/31/2018")));
        insert(vehicule.getId(), client.getId(), String.valueOf(new Date("01/02/2018")), String.valueOf(new Date("01/30/2018")));
        insert(vehicule.getId(), client.getId(), String.valueOf(new Date("01/03/2018")), String.valueOf(new Date("01/29/2018")));
        insert(vehicule.getId(), client.getId(), String.valueOf(new Date("01/04/2018")), String.valueOf(new Date("01/28/2018")));

    }

    private ContentValues buildContentValues(int idVehicule, int idClient, String dateDebut, String dateFin){
        ContentValues cv = new ContentValues();
        cv.put(LocationContract.COL_ID_VEHICULE, idVehicule);
        cv.put(LocationContract.COL_ID_CLIENT, idClient);
        cv.put(LocationContract.COL_DATE_DEBUT, dateDebut);
        cv.put(LocationContract.COL_DATE_FIN, dateFin);
        return cv;
    }

    public void update(int id, int idVehicule, int idClient, String dateDebut, String dateFin){

        SQLiteDatabase db = helper.getWritableDatabase();

        db.update(LocationContract.TABLE_NAME, buildContentValues(idVehicule, idClient, dateDebut, dateFin), "ID=?", new String[]{String.valueOf(id)});
    }
}
