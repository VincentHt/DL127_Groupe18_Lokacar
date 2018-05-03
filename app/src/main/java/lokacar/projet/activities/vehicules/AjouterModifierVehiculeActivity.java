package lokacar.projet.activities.vehicules;

import android.widget.RadioGroup;
import android.widget.RadioButton;
import lokacar.projet.bo.vehicules.Vehicule;
import lokacar.projet.dal.helper.AppDbHelper;
import lokacar.projet.dal.vehicule.VehiculeContract;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lokacar.projet.R;
import lokacar.projet.app.AppName;

public class AjouterModifierVehiculeActivity extends AppCompatActivity {
    private SQLiteDatabase mDb;

    private EditText mMarqueVehiculeEditText, mModeleVehiculeEditText, mImmatriculationVehiculeEditText,
            mFraisLocationParJourEditText, mTypeVehiculeEditText;
    private Button mValiderAjouterEditerButton;
    private RadioButton mVoitureRadioButton, mUtilitaireRadioButton;
    private RadioGroup mTypeVehiculeRadioGroup;

    private AppDbHelper dbHelper;
    Context context = this;
    private final static String LOG_TAG = AjouterModifierVehiculeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_modifier_vehicule);
        String mAppName = ((AppName) this.getApplication()).getmAppName();
        setTitle(mAppName);


        mMarqueVehiculeEditText = findViewById(R.id.mMarqueVehiculeEditText);
        mModeleVehiculeEditText = findViewById(R.id.mModeleVehiculeEditText);
        mImmatriculationVehiculeEditText = findViewById(R.id.mImmatriculationVehiculeEditText);
        mFraisLocationParJourEditText = findViewById(R.id.mFraisLocationParJourEditText);
        //mTypeVehiculeEditText = findViewById(R.id.mTypeVehiculeEditText);
        mValiderAjouterEditerButton = findViewById(R.id.mValiderAjouterEditerButton);
        mTypeVehiculeRadioGroup = findViewById(R.id.mTypeVehiculeRadioGroup);
        mVoitureRadioButton = findViewById(R.id.mVoitureRadioButton);
        mUtilitaireRadioButton = findViewById(R.id.mUtilitaireRadioButton);
    }


    public void onclickValiderAjouterVehicule(View view) {
        if (mMarqueVehiculeEditText.getText().length() == 0 ||
                mModeleVehiculeEditText.getText().length() == 0 ||
                mImmatriculationVehiculeEditText.getText().length() == 0 ||
                mFraisLocationParJourEditText.getText().length() == 0/* ||
                mTypeVehiculeEditText.getText().length() == 0*/) {
            Toast toast = Toast.makeText(context, "tous les champs sont obligatoires", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        float mFraisLocationParJour = 1;
        try {
            mFraisLocationParJour = Float.parseFloat(mFraisLocationParJourEditText.getText().toString());
        } catch (NumberFormatException ex) {
            Log.e(LOG_TAG, "Failed to parse party size text to number: " + ex.getMessage());
        }

        int radioButtonId = mTypeVehiculeRadioGroup.getCheckedRadioButtonId();
        String selectedValue = "";
        switch (radioButtonId)
        {
            case R.id.mVoitureRadioButton:
                selectedValue = "voiture";
                break;
            case R.id.mUtilitaireRadioButton:
                selectedValue = "utilitaire";
                break;
        }

        Vehicule vehicule = new Vehicule(mMarqueVehiculeEditText.getText().toString(), mModeleVehiculeEditText.getText().toString(),
                mImmatriculationVehiculeEditText.getText().toString(), mFraisLocationParJour, selectedValue);

        addNewVehicule(vehicule);

        Class destinationActivity = ListVehiculesActivity.class;
        Intent myIntent = new Intent(context, destinationActivity);
        startActivity(myIntent);
    }



    private long addNewVehicule(Vehicule vehicule) {
        ContentValues cv = new ContentValues();
        cv.put(VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_MARQUE, vehicule.getMarque());
        cv.put(VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_MODELE, vehicule.getModele());
        cv.put(VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_IMMATRICULATION, vehicule.getImmatriculation());
        cv.put(VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_FRAIS_LICATION_JOUR, vehicule.getPrixJour());
        cv.put(VehiculeContract.VehiculeEntry.COLUMN_VEHICULE_TYPE, vehicule.getType());

        dbHelper = new AppDbHelper(this);
        mDb = dbHelper.getWritableDatabase();

        return mDb.insert(VehiculeContract.VehiculeEntry.TABLE_NAME, null, cv);
    }





}
