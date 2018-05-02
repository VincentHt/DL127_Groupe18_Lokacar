package lokacar.projet.dal.agences;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import lokacar.projet.bo.agence.Agence;
import lokacar.projet.bo.agence.AgenceContract;
import lokacar.projet.dal.helper.AppDbHelper;

public class AgenceDAO {

    private AppDbHelper helper;
    private String nomAgence;
    private String codeAgence;

    public AgenceDAO(Context context){
        this.helper = new AppDbHelper(context);
    }

    public long insert(String nomAgence, String password){

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(AgenceContract.COLUMN_NOM_AGENCE, nomAgence);
        cv.put(AgenceContract.COLUMN_PASSE_AGENCE, password);

        long result = db.insert(AgenceContract.TABLE_NAME, null, cv);

        db.close();

        return result;
    }

    public Agence getLogins(){
        Agence agence = null;

        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(
                AgenceContract.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if(cursor.moveToFirst()){
            nomAgence = cursor.getString(cursor.getColumnIndex(AgenceContract.COLUMN_NOM_AGENCE));
            codeAgence = cursor.getString(cursor.getColumnIndex(AgenceContract.COLUMN_PASSE_AGENCE));
            agence = new Agence(nomAgence, codeAgence);
        }

        cursor.close();
        db.close();

        return agence;
    }

}
