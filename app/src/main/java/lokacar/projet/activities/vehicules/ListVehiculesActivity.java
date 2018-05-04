package lokacar.projet.activities.vehicules;

import lokacar.projet.bo.vehicules.Vehicule;
import lokacar.projet.dal.vehicule.VehiculeContract;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import lokacar.projet.dal.helper.AppDbHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import lokacar.projet.bo.vehicules.VehiculeListAdapter;

import lokacar.projet.R;
import lokacar.projet.app.AppName;
public class ListVehiculesActivity extends AppCompatActivity {

    private class MonListener implements VehiculeListAdapter.OnItemClickListener{

        @Override
        public void onItemClick(Cursor cursor, int position) {
            Class destinationActivity = DetailsVehiculeActivity.class;
            Intent myIntent = new Intent(context, destinationActivity);

            if (cursor.moveToPosition(position)){
                Log.i("TAG_VEH",Integer.toString(cursor.getInt(cursor.getColumnIndex("_id"))) + " test");
                myIntent.putExtra("idVehicule", Integer.toString(cursor.getInt(cursor.getColumnIndex("_id"))));

                startActivity(myIntent);
            }



        }
    }

    private MonListener mListener = new MonListener();
    private SQLiteDatabase mDb;
    private VehiculeListAdapter mAdapter;
    Context context = this;
    private android.support.v7.widget.RecyclerView vehiculeRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_list_vehicules);

        String mAppName = ((AppName) this.getApplication()).getmAppName();
        setTitle(mAppName);

        vehiculeRecyclerView = this.findViewById(R.id.mVehiculeRecyclerView);
        vehiculeRecyclerView.setLayoutManager(new android.support.v7.widget.LinearLayoutManager(this));

        AppDbHelper dbHelper = new AppDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
        Cursor cursor = getVehicules();

        mAdapter = new VehiculeListAdapter(this, cursor, mListener);
        vehiculeRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    public void rechercherVoitureFiltre(View view) {
        AppDbHelper dbHelper = new AppDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
        Cursor cursor = getVoitures();

        mAdapter = new VehiculeListAdapter(this, cursor, mListener);
        vehiculeRecyclerView.setAdapter(mAdapter);

        mAdapter.swapCursor(getVoitures());
    }

    public void rechercherUtilitaireFiltre(View view) {
        AppDbHelper dbHelper = new AppDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
        Cursor cursor = getUtilitaires();

        mAdapter = new VehiculeListAdapter(this, cursor, mListener);
        vehiculeRecyclerView.setAdapter(mAdapter);

        mAdapter.swapCursor(getUtilitaires());
    }
    public void rechercherVehiculesFiltre(View view) {
        AppDbHelper dbHelper = new AppDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
        Cursor cursor = getVehicules();

        mAdapter = new VehiculeListAdapter(this, cursor, mListener);
        vehiculeRecyclerView.setAdapter(mAdapter);

        mAdapter.swapCursor(getVehicules());
    }

    public void onclickAjouterVehicule(View view) {
        Class destinationActivity = AjouterModifierVehiculeActivity.class;
        android.content.Intent myIntent = new android.content.Intent(context, destinationActivity);
        startActivity(myIntent);

    }
    private Cursor getVehicules() {
        return mDb.query(
                VehiculeContract.VehiculeEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    private Cursor getVoitures() {
        return mDb.query(
                VehiculeContract.VehiculeEntry.TABLE_NAME,
                null,
                VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_TYPE + " LIKE '%voiture%'",
                null,
                null,
                null,
                null
        );
    }

    private Cursor getUtilitaires() {
        return mDb.query(
                VehiculeContract.VehiculeEntry.TABLE_NAME,
                null,
                VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_TYPE + " LIKE '%utilitaire%'",
                null,
                null,
                null,
                null
        );
    }

}
