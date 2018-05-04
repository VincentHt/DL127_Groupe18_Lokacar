package lokacar.projet.dal.locations;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import lokacar.projet.bo.etatsDesLieux.EtatDesLieuxContract;
import lokacar.projet.bo.locations.LocationContract;
import lokacar.projet.bo.photos.PhotoEDLContract;
import lokacar.projet.bo.vehicules.VehiculeContract;

public class LocationBddHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "bdd_lokacar.db";
    private final static int DATABASE_VERSION = 1;

    public LocationBddHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LocationContract.SQL_CREATE_TABLE);
        db.execSQL(EtatDesLieuxContract.SQL_CREATE_TABLE);
        db.execSQL(PhotoEDLContract.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(LocationContract.SQL_DROP_TABLE);
        db.execSQL(EtatDesLieuxContract.SQL_DROP_TABLE);
        db.execSQL(PhotoEDLContract.SQL_DROP_TABLE);
        onCreate(db);
    }

}
