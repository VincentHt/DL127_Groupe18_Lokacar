package lokacar.projet.dal.vehicule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

import lokacar.projet.bo.vehicules.Vehicule;
import lokacar.projet.bo.vehicules.VehiculeContract;
import lokacar.projet.dal.helper.AppDbHelper;

public class VehiculeDAO {
    private AppDbHelper helper;
    private SQLiteDatabase db;
    private ArrayList<Vehicule> client;

    public VehiculeDAO(Context context){
        this.helper = new AppDbHelper(context);
    }

    public long insert(Vehicule vehicule){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(lokacar.projet.bo.vehicules.VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_MARQUE, vehicule.getMarque());
        cv.put(VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_MODELE, vehicule.getModele());
        cv.put(VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_IMMATRICULATION, vehicule.getImmatriculation());
        cv.put(VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_FRAIS_LICATION_JOUR, vehicule.getPrixJour());
        cv.put(VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_TYPE, vehicule.getType());

        long id = db.insert(VehiculeContract.VehiculeEntry.TABLE_NAME, null, cv);
        if (db != null) {
            db.close();
        }
        return id;
    }

}

/*




    public ArrayList<Client> getClient() {

        Cursor cursor = db.query(
                TABLE_NAME, null, null, null, null, null, null);
        client = new ArrayList<Client>();

        while (cursor.moveToNext()) {
            Integer id = cursor.getInt(cursor.getColumnIndex("_ID"));
            String nom = cursor.getString(cursor.getColumnIndex("NOM"));
            String prenom = cursor.getString(cursor.getColumnIndex("PRENOM"));
            String adresse = cursor.getString(cursor.getColumnIndex("ADRESSE"));
            String email = cursor.getString(cursor.getColumnIndex("EMAIL"));
            String tel = cursor.getString(cursor.getColumnIndex("TEL"));
           // Boolean existant = (cursor.getInt(cursor.getColumnIndex("EXISTANT")) == 1);
            client.add(new Client(id, nom, prenom, adresse, email, tel));
        }
        cursor.close();

        return client;
    }

    public Client getClient(Integer id) {
        Cursor cursor = db.query(
                TABLE_NAME, null, "_ID=" + String.valueOf(id), null, null, null, "_ID");
        Client client= null;
        if (cursor.moveToFirst()) {
            client = new Client(cursor.getInt(cursor.getColumnIndex("_ID")),
                    cursor.getString(cursor.getColumnIndex("NOM")),
                    cursor.getString(cursor.getColumnIndex("PRENOM")),
                    cursor.getString(cursor.getColumnIndex("ADRESSE")),
                    cursor.getString(cursor.getColumnIndex("EMAIL")),
                    cursor.getString(cursor.getColumnIndex("TEL")));

        }
        cursor.close();

        return client;
    }

    public void setClient(Integer clientId, Client client) {
        db.update(TABLE_NAME, constructClientDB(client), "_ID=" + clientId, null);
    }


    public void deleteArticle(int articleId) {

        db.delete(TABLE_NAME, "_ID=" + articleId, null);

    }

    public void finalize() throws Throwable {
        if (this.helper != null)
            this.helper.close();
        if (db != null)
            db.close();
        super.finalize();
    }
}
 */