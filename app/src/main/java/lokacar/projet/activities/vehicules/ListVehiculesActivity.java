package lokacar.projet.activities.vehicules;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import lokacar.projet.activities.ActionsChoiceActivity;
import lokacar.projet.bo.vehicules.Vehicule;
import lokacar.projet.bo.vehicules.VehiculeContract;
import lokacar.projet.dal.helper.AppDbHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import lokacar.projet.adapters.VehiculeListAdapter;

import lokacar.projet.R;
import lokacar.projet.app.AppName;
public class ListVehiculesActivity extends AppCompatActivity {

    private AppDbHelper helper;

    private class MonListener implements VehiculeListAdapter.OnItemClickListener{

        @Override
        public void onItemClick(Cursor cursor, int position) {

            if (cursor.moveToPosition(position)){
                Vehicule vehicule = new Vehicule(1, "Citroen", "Xsara", "AA-000-AA", 5, "voiture");
                if(!getIntent().getBooleanExtra("choixLocation", false)){
                    Intent myIntent = new Intent(context, DetailsVehiculeActivity.class);
                    myIntent.putExtra("idVehicule", Integer.toString(cursor.getInt(cursor.getColumnIndex("_id"))));
                    Log.i("TAG_VEH",Integer.toString(cursor.getInt(cursor.getColumnIndex("_id"))) + " test");
                    startActivity(myIntent);
                } else {
                    Intent intent = new Intent ();
                    intent.putExtra("vehicule", vehicule);
                    setResult(RESULT_OK, intent);
                    finish();
                }

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
        getMenuInflater().inflate(R.menu.menu_liste, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.retour:
                Intent intent = new Intent(this, ActionsChoiceActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
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
