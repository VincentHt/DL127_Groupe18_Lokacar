package lokacar.projet.dal.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import lokacar.projet.bo.agence.AgenceContract;
import lokacar.projet.bo.vehicules.VehiculeContract;

public class AppDbHelper extends SQLiteOpenHelper {
   public static final String DATABASE_NAME = "lokacar.db";
    public static final int DATABASE_VERSION = 1;

    public AppDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_AGENCE_TABLE = "CREATE TABLE IF NOT EXISTS " + AgenceContract.AgenceEntry.TABLE_NAME + " (" +
                AgenceContract.AgenceEntry.COLUMN_NOM_AGENCE + " TEXT NOT NULL, " +
                AgenceContract.AgenceEntry.COLUMN_PASSE_AGENCE + " TEXT NOT NULL " +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_AGENCE_TABLE);

        final String SQL_CREATE_VEHICULE_TABLE = "CREATE TABLE IF NOT EXISTS " + VehiculeContract.VehiculeEntry.TABLE_NAME + " (" +
                lokacar.projet.bo.vehicules.VehiculeContract.VehiculeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_MARQUE + " TEXT NOT NULL, " +
                VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_MODELE + " TEXT NOT NULL, " +
                VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_IMMATRICULATION + " TEXT NOT NULL, " +
                VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_FRAIS_LICATION_JOUR + " FLOAT NOT NULL, " +
                VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_TYPE + " STRING NOT NULL " +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_VEHICULE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AgenceContract.AgenceEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + VehiculeContract.VehiculeEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


}
