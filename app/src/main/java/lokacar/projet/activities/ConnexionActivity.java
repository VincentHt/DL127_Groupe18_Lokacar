package lokacar.projet.activities;

import lokacar.projet.dal.helper.AppDbHelper;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import lokacar.projet.app.AppName;

import lokacar.projet.R;
import lokacar.projet.bo.agence.Agence;
import lokacar.projet.bo.agence.AgenceContract;
import lokacar.projet.dal.agences.AgenceDAO;
import lokacar.projet.dal.agences.AgenceDbHelper;
import lokacar.projet.dal.helper.AppDbHelper;
import lokacar.projet.dal.locations.LocationBddHelper;

public class ConnexionActivity extends AppCompatActivity {

    /*private final String login = "Tonton";
    private final String password = "fuck";
    private EditText loginField;
    private EditText passwordField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VehiculeDAO dao = new VehiculeDAO(ConnexionActivity.this);

    }

    public void onClickConnexion(View view) {

        loginField = findViewById(R.id.login);
        passwordField = findViewById(R.id.motDePasse);

        String loginSaisi = loginField.getText().toString();
        String passwordSaisi = passwordField.getText().toString();

        if(login.equals(loginSaisi) && password.equals(passwordSaisi)){
            Intent intent = new Intent(ConnexionActivity.this, ActionsChoiceActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Login ou Mot de passe éronnés", Toast.LENGTH_SHORT).show();
        }

    }*/
    EditText mChoixNomAgenceEditText,  mPasswordEditText, mPasseAgenceEditText;
    TextView mNomAgenceDefiniTextView;
    Button mValiderNomAgenceButton, mValiderConnexionButton, mChangeAgencyButton;

    private SQLiteDatabase mDb;
    Context context = this;
    private Agence agence;
    private AppDbHelper dbHelper;
    private AgenceDAO agenceDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mChoixNomAgenceEditText = findViewById(R.id.mNomAgenceEditText);
        mPasseAgenceEditText = (EditText)findViewById(R.id.mPasseAgenceEditText);
        mPasswordEditText = (EditText)findViewById(R.id.mPasswordEditText);
        mNomAgenceDefiniTextView = (TextView)findViewById(R.id.mNomAgenceDefiniTextView);
        mValiderNomAgenceButton = (Button)findViewById(R.id.mValiderNomAgenceButton);
        mValiderConnexionButton = (Button)findViewById(R.id.mConnectionButton);
        mChangeAgencyButton = (Button) findViewById(R.id.btnChangementAgence);

        // Create a DB helper (this will create the DB if run for the first time, and the different tables needed in the application)
        dbHelper = new AppDbHelper(this);

        agenceDAO = new AgenceDAO(context);

        chooseInterface();

    }

    public void chooseInterface(){
        agence = getLogins();

        if(agence != null){
            setTitle(agence.getNom());
            showLogin();
        } else {
            showFirstTimeConnection();
        }
    }

    public void showFirstTimeConnection(){
        mChoixNomAgenceEditText.setText("");
        mPasseAgenceEditText.setText("");
        mChoixNomAgenceEditText.requestFocus();

        mChoixNomAgenceEditText.setVisibility(View.VISIBLE);
        mPasseAgenceEditText.setVisibility(View.VISIBLE);
        mValiderNomAgenceButton.setVisibility(View.VISIBLE);
        mNomAgenceDefiniTextView.setVisibility(View.GONE);
        mPasswordEditText.setVisibility(View.GONE);
        mValiderConnexionButton.setVisibility(View.GONE);
        mChangeAgencyButton.setVisibility(View.INVISIBLE);
    }

    public void showLogin(){
        mChoixNomAgenceEditText.setVisibility(View.GONE);
        mPasseAgenceEditText.setVisibility(View.GONE);
        mValiderNomAgenceButton.setVisibility(View.GONE);
        mNomAgenceDefiniTextView.setVisibility(View.VISIBLE);
        mPasswordEditText.setVisibility(View.VISIBLE);
        mValiderConnexionButton.setVisibility(View.VISIBLE);
        if(agence.getNom() != null){
            mPasswordEditText.setText("");
            mNomAgenceDefiniTextView.setText(agence.getNom());
            mChangeAgencyButton.setVisibility(View.VISIBLE);
        }
    }


    public void onClickValiderAgence(View view) {
        if (mChoixNomAgenceEditText.getText().length() == 0 ||
                mPasseAgenceEditText.getText().length() == 0) {
            return;
        }
        initializeApp(mChoixNomAgenceEditText.getText().toString(), mPasseAgenceEditText.getText().toString());

        chooseInterface();
    }

    public void onClickValiderConnexion(View view) {
        if ( mPasswordEditText.getText().length() == 0) {
            return;
        }
        if(agence.getPassword().equals(mPasswordEditText.getText().toString())){
            Class destinationActivity = ActionsChoiceActivity.class;
            Intent myIntent = new Intent(context, destinationActivity);
            startActivity(myIntent);
        } else {
            Toast toast = Toast.makeText(context, "mot de passe incorrect\n veuillez re-essayer", Toast.LENGTH_SHORT);
            toast.show();
            mPasswordEditText.clearFocus();
            mPasswordEditText.getText().clear();
        }
    }

    private Agence getLogins() {

        return agenceDAO.getLogins();
    }

    private long initializeApp(String nomAgence, String passeAgence) {

        AgenceDAO agenceDAO = new AgenceDAO(context);

        return agenceDAO.insert(nomAgence, passeAgence);
    }

    public void changementAgenceOnClick(View view) {
        dbHelper.onUpgrade(mDb,mDb.getVersion(),mDb.getVersion()+1);
        chooseInterface();
    }
}
