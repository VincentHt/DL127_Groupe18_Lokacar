package lokacar.projet.dal.clients;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import lokacar.projet.bo.client.Client;
import lokacar.projet.bo.client.ClientContract;
import lokacar.projet.dal.helper.AppDbHelper;

import static lokacar.projet.bo.client.ClientContract.TABLE_NAME;

public class ClientDAO {

    private AppDbHelper helper;
    private SQLiteDatabase db;
    private ArrayList<Client> client;

    public ClientDAO(Context context) {
        this.helper = new AppDbHelper(context);
    }

    public long insert(Client item) {

        SQLiteDatabase db = helper.getWritableDatabase();

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


    private ContentValues constructClientDB(Client client) {
        ContentValues values = new ContentValues();
        values.put("NOM", client.getNom());
        values.put("PRENOM", client.getPrenom());
        values.put("ADRESSE", client.getAdresse());
        values.put("EMAIL", client.getEmail());
        values.put("TEL", client.getTel());

        return values;

    }

    public ArrayList<Client> getAllClients() {

        db = helper.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME, null, null, null, null, null, null);
        client = new ArrayList<Client>();

        while (cursor.moveToNext()) {
            Integer id = cursor.getInt(cursor.getColumnIndex(ClientContract.COL_ID));
            String nom = cursor.getString(cursor.getColumnIndex(ClientContract.COL_NOM));
            String prenom = cursor.getString(cursor.getColumnIndex(ClientContract.COL_PRENOM));
            String adresse = cursor.getString(cursor.getColumnIndex(ClientContract.COL_ADRESSE));
            String email = cursor.getString(cursor.getColumnIndex(ClientContract.COL_EMAIL));
            String tel = cursor.getString(cursor.getColumnIndex(ClientContract.COL_TEL));
           // Boolean existant = (cursor.getInt(cursor.getColumnIndex("EXISTANT")) == 1);
            client.add(new Client(id, nom, prenom, adresse, email, tel));
        }
        cursor.close();

        return client;
    }

    public Client getClientById(Integer id) {
        db = helper.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME, null, "ID=" + String.valueOf(id), null, null, null, "ID");
        Client client= null;
        if (cursor.moveToFirst()) {
            client = new Client(cursor.getInt(cursor.getColumnIndex(ClientContract.COL_ID)),
                    cursor.getString(cursor.getColumnIndex(ClientContract.COL_NOM)),
                    cursor.getString(cursor.getColumnIndex(ClientContract.COL_PRENOM)),
                    cursor.getString(cursor.getColumnIndex(ClientContract.COL_ADRESSE)),
                    cursor.getString(cursor.getColumnIndex(ClientContract.COL_EMAIL)),
                    cursor.getString(cursor.getColumnIndex(ClientContract.COL_TEL)));

        }
        cursor.close();

        return client;
    }

    public void setClient(Integer clientId, Client client) {
        db.update(TABLE_NAME, constructClientDB(client), "ID=" + clientId, null);
    }


    public void deleteClient(int articleId) {

        db.delete(TABLE_NAME, "ID=" + articleId, null);

    }

    public void finalize() throws Throwable {
        if (this.helper != null)
            this.helper.close();
        if (db != null)
            db.close();
        super.finalize();
    }
}