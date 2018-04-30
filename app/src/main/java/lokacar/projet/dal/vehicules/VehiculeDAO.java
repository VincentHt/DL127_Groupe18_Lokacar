package lokacar.projet.dal.vehicules;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import lokacar.projet.bo.vehicules.Vehicule;

public class VehiculeDAO {

    private VehiculeBddHelper helper;

    public VehiculeDAO(Context context){
        this.helper = new VehiculeBddHelper(context);
    }

    public long insert(Vehicule item){

        SQLiteDatabase db = helper.getWritableDatabase();



        /*ContentValues c = new ContentValues();
        c.put(TrucContract.COL_VALEUR, item.getValueur());
        c.put(TrucContract.COL_NOM, item.getNom());

        long id = db.insert(TrucContract.TABLE_NAME,null,c);

        if(db != null){
            db.close();
        }*/

        return 1;
    }

}
