package lokacar.projet.dal.helper.client;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import lokacar.projet.bo.client.ClientContract;

public class ClientBDDHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "bdd_lokacar.db";
    private final static int DATABASE_VERSION = 1;

    public ClientBDDHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ClientContract.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ClientContract.SQL_DROP_TABLE);
        onCreate(db);
    }


}
