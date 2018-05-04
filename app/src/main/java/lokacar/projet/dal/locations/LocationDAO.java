package lokacar.projet.dal.locations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lokacar.projet.bo.client.Client;
import lokacar.projet.bo.client.ClientContract;
import lokacar.projet.bo.etatsDesLieux.EtatDesLieux;
import lokacar.projet.bo.etatsDesLieux.EtatDesLieuxContract;
import lokacar.projet.bo.locations.Location;
import lokacar.projet.bo.locations.LocationContract;
import lokacar.projet.bo.vehicules.Vehicule;
import lokacar.projet.bo.vehicules.VehiculeContract;
import lokacar.projet.dal.clients.ClientDAO;
import lokacar.projet.dal.helper.AppDbHelper;

public class LocationDAO {

    private List<Location> listeLocations;
    private AppDbHelper helper;
    private SQLiteDatabase db;
    Context context;

    public LocationDAO(Context context){
        this.context = context;
        this.helper = new AppDbHelper(context);
    }

    /**
     * Méthode d'insertion d'une nouvelle location en BDD
     */
    public long insert(Location location){

        SQLiteDatabase db = helper.getWritableDatabase();

        long result = db.insert(LocationContract.TABLE_NAME, null, buildContentValues(location));

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
                null,
                null,
                null,
                null,
                LocationContract.COL_DATE_FIN);

        /* On parcourt la liste des locations obtenue plus haut */
        if(cursorLocation != null && cursorLocation.moveToFirst()){
            do{
                int idLocation = cursorLocation.getInt(cursorLocation.getColumnIndex(LocationContract.COL_ID));
                int idVehicule = cursorLocation.getInt(cursorLocation.getColumnIndex(LocationContract.COL_ID_VEHICULE));
                int idClient = cursorLocation.getInt(cursorLocation.getColumnIndex(LocationContract.COL_ID_CLIENT));
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
               /*Cursor cursorEdlFin = db.query(
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

                Log.i("TAG_VEH", ""+idVehicule);
                Cursor cursor = getVehicule(idVehicule);
                if(cursor.moveToFirst()) {
                    Vehicule vehicule = new Vehicule(
                            cursor.getInt(cursor.getColumnIndex("_id")),
                            cursor.getString(cursor.getColumnIndex("marque")),
                            cursor.getString(cursor.getColumnIndex("modele")),
                            cursor.getString(cursor.getColumnIndex("immatriculation")),
                            cursor.getFloat(cursor.getColumnIndex("frais_location_jour")),
                            cursor.getString(cursor.getColumnIndex("type")));

                    location.setVehicule(vehicule);
                }

                /* Récupération du client */

                ClientDAO clientDAO = new ClientDAO(context);

                Client client = clientDAO.getClientById(idClient);

                location.setClient(client);


                /* fermeture des différents cursors relatifs à la location courante */
                /*if(cursorEdlDebut != null){
                    cursorEdlDebut.close();
                }
                if(cursorEdlFin != null){
                    cursorEdlFin.close();
                }*/

                location.setId(idLocation);

                /* Gestion des dates */
                    /* Date de début de location */

                if(dateDebutString != null){
                    String[] dateDebutTableau = dateDebutString.split("/");
                    dateDebut = new Date((Integer.parseInt(dateDebutTableau[2])-1900), Integer.parseInt(dateDebutTableau[1])-1, Integer.parseInt(dateDebutTableau[0])+0);
                    location.setDateDebut(dateDebut);
                }

                    /* Date de fin de location */
                if(dateFinString != null) {
                    String[] dateFinTableau = dateFinString.split("/");
                    dateFin = new Date((Integer.parseInt(dateFinTableau[2]) - 1900), Integer.parseInt(dateFinTableau[1]) - 1, Integer.parseInt(dateFinTableau[0]) + 0);
                    location.setDateFin(dateFin);
                }

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

    private ContentValues buildContentValues(Location location){
        ContentValues cv = new ContentValues();
        cv.put(LocationContract.COL_ID_VEHICULE, location.getVehicule().getId());
        cv.put(LocationContract.COL_ID_CLIENT, location.getClient().getId());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        cv.put(LocationContract.COL_DATE_DEBUT, String.valueOf(sdf.format(location.getDateDebut())));
        Log.i("LectureDate" , String.valueOf(sdf.format(location.getDateDebut())));
        cv.put(LocationContract.COL_DATE_FIN, String.valueOf(sdf.format(location.getDateFin())));

        return cv;
    }

    public void update(Location location){

        SQLiteDatabase db = helper.getWritableDatabase();

        db.update(LocationContract.TABLE_NAME, buildContentValues(location), "ID=?", new String[]{String.valueOf(location.getId())});
    }

    private Cursor getVehicule(int id) {
        db = helper.getReadableDatabase();
        return db.query(
                VehiculeContract.VehiculeEntry.TABLE_NAME,
                null,
                "_id = " + id,
                null,
                null,
                null,
                null
        );
    }
}
