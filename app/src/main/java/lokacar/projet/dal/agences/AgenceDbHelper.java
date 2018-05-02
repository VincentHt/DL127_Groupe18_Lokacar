package lokacar.projet.dal.agences;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import lokacar.projet.bo.agence.AgenceContract;

public class AgenceDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "bdd_lokacar.db";
    private static final int DATABASE_VERSION = 1;

    public AgenceDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // Create a table to hold waitlist data
        final String SQL_CREATE_AGENCE_TABLE = "CREATE TABLE " + AgenceContract.AgenceEntry.TABLE_NAME + " (" +
                AgenceContract.AgenceEntry.COLUMN_NOM_AGENCE + " TEXT NOT NULL, " +
                AgenceContract.AgenceEntry.COLUMN_PASSE_AGENCE + " TEXT NOT NULL " +
                "); ";

        sqLiteDatabase.execSQL(SQL_CREATE_AGENCE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AgenceContract.AgenceEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
