package lokacar.projet.dal.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import lokacar.projet.bo.agence.AgenceContract;
import lokacar.projet.bo.client.ClientContract;
import lokacar.projet.bo.etatsDesLieux.EtatDesLieuxContract;
import lokacar.projet.bo.locations.LocationContract;
import lokacar.projet.bo.photos.PhotoEDLContract;
import lokacar.projet.bo.vehicules.VehiculeContract;

public class AppDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bdd_lokacar.db";
    private static final int DATABASE_VERSION = 1;

    public AppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_VEHICULE_TABLE = "CREATE TABLE IF NOT EXISTS " + VehiculeContract.VehiculeEntry.TABLE_NAME + " (" +
                VehiculeContract.VehiculeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_MARQUE + " TEXT NOT NULL, " +
                VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_MODELE + " TEXT NOT NULL, " +
                VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_IMMATRICULATION + " TEXT NOT NULL, " +
                VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_FRAIS_LICATION_JOUR + " FLOAT NOT NULL, " +
                VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_TYPE + " STRING NOT NULL " +
                "); ";



        db.execSQL(SQL_CREATE_VEHICULE_TABLE);
        db.execSQL(AgenceContract.SQL_CREATE_TABLE);
        db.execSQL(ClientContract.SQL_CREATE_TABLE);
        db.execSQL(LocationContract.SQL_CREATE_TABLE);
        db.execSQL(EtatDesLieuxContract.SQL_CREATE_TABLE);
        db.execSQL(PhotoEDLContract.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + VehiculeContract.VehiculeEntry.TABLE_NAME);
        db.execSQL(AgenceContract.SQL_DROP_TABLE);
        db.execSQL(LocationContract.SQL_DROP_TABLE);
        db.execSQL(EtatDesLieuxContract.SQL_DROP_TABLE);
        db.execSQL(PhotoEDLContract.SQL_DROP_TABLE);
        db.execSQL(ClientContract.SQL_DROP_TABLE);
        onCreate(db);
    }



}
