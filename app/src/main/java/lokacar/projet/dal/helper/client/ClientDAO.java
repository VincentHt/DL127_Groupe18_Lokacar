package lokacar.projet.dal.helper.client;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import lokacar.projet.bo.client.Client;
import lokacar.projet.bo.client.ClientContract;

import static lokacar.projet.bo.client.ClientContract.TABLE_NAME;

public class ClientDAO {

    private ClientBDDHelper clientBDDHelper;
    private SQLiteDatabase db;
    private ArrayList<Client> client;
    private  SQLiteOpenHelper helper;

    public ClientDAO(Context context) {
        this.clientBDDHelper = new ClientBDDHelper(context);
    }

    public long insert(Client item) {

        SQLiteDatabase db = clientBDDHelper.getWritableDatabase();

        ContentValues c = new ContentValues();
        c.put(ClientContract.COL_NOM, item.getNom());
        c.put(ClientContract.COL_PRENOM, item.getPrenom());
        c.put(ClientContract.COL_ADRESSE, item.getAdresse());
        c.put(ClientContract.COL_EMAIL, item.getEmail());
        c.put(ClientContract.COL_TEL, item.getTel());

        long id = db.insert(TABLE_NAME, null, c);

        if (db != null) {
            db.close();
        }

        return id;

    }

    public long addClient(Client client) {
        // met à jour la liste de client

        return db.insert(TABLE_NAME, null, constructClientDB(client));
    }

    private ContentValues constructClientDB(Client client) {
        ContentValues values = new ContentValues();
        values.put("NOM", client.getNom());
        values.put("PRENOM", client.getPrenom());
        values.put("ADRESSE", client.getAdresse());
        values.put("EMAIL", client.getEmail());
        values.put("TEL", client.getTel());

        return values;

    }

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