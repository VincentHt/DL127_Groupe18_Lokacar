package lokacar.projet.activities.vehicules;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import lokacar.projet.R;
import lokacar.projet.bo.vehicules.Vehicule;
import lokacar.projet.dal.helper.AppDbHelper;
import lokacar.projet.dal.vehicule.VehiculeContract;


public class DetailsVehiculeActivity extends AppCompatActivity {

    Context context = this;
    private SQLiteDatabase mDb;
    private Vehicule vehicule;

    private Button mLouerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_vehicule);

        mLouerButton = (Button)findViewById(R.id.louer_button);

        Intent intent = getIntent();
        String idVehiculeString = intent.getStringExtra("idVehicule");
        int idVehicule = Integer.parseInt(idVehiculeString);

        AppDbHelper dbHelper = new AppDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
        Log.i("TAG_VEH", ""+idVehicule);
        Cursor cursor = getVehicule(idVehicule);
        if(cursor.moveToFirst()) {
            vehicule = new Vehicule(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("marque")),
                    cursor.getString(cursor.getColumnIndex("modele")),
                    cursor.getString(cursor.getColumnIndex("immatriculation")),
                    cursor.getFloat(cursor.getColumnIndex("frais_location_jour")),
                    cursor.getString(cursor.getColumnIndex("type"))
            );
        }
    }


    private Cursor getVehicule(int id) {
        return mDb.query(
                VehiculeContract.VehiculeEntry.TABLE_NAME,
                null,
                "_id = " + id,
                null,
                null,
                null,
                null
        );
    }

    public void onClickLouerVehicule(View view) {
        Class destinationActivity = LocationEditOrCreateActivity.class;
        Intent myIntent = new Intent(context, destinationActivity);
        myIntent.putExtra("vehicule", vehicule);
        startActivity(myIntent);
    }
}
