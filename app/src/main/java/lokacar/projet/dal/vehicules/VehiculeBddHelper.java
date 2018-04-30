package lokacar.projet.dal.vehicules;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import lokacar.projet.bo.vehicules.VehiculeContract;

public class VehiculeBddHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "bdd_lokacar.db";
    private final static int DATABASE_VERSION = 1;

    public VehiculeBddHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(VehiculeContract.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(VehiculeContract.SQL_DROP_TABLE);
        onCreate(db);
    }
}
