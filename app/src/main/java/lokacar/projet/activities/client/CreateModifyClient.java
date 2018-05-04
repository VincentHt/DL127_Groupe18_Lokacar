package lokacar.projet.activities.client;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import lokacar.projet.R;
import lokacar.projet.activities.ActionsChoiceActivity;
import lokacar.projet.bo.client.Client;
import lokacar.projet.dal.clients.ClientDAO;

public class CreateModifyClient extends AppCompatActivity {

    private int clientId;
    private Client nouveauClient;
    private ClientDAO clientDAO;
    private EditText editNom;
    private EditText editPrenom;
    private EditText editAdresse;
    private EditText editEmail;
    private EditText editTel;
  //  private RatingBar rbNote;
    private ProgressBar progress;
  //  private boolean existant = false;
    Button btnValider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_modify_client);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(android.R.drawable.btn_star);

        editNom =  findViewById(R.id.Form_Create_Modify_Nom);
        editPrenom =  findViewById(R.id.Form_Create_Modify_Prenom);
        editAdresse =  findViewById(R.id.Form_Create_Modify_Adresse);
        editEmail =  findViewById(R.id.Form_Create_Modify_Email);
        editTel =  findViewById(R.id.Form_Create_Modify_Tel);
        btnValider = findViewById(R.id.btnValidClient);

        progress =  findViewById(R.id.Form_Create_progressBar);


        clientDAO = new ClientDAO(CreateModifyClient.this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        clientId = intent.getIntExtra("clientId", -1);
        if (clientId != -1)
        {
            ChargeClient chargeClient = new ChargeClient();
            chargeClient.execute(clientId);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_liste, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.retour:
                Intent intent = new Intent(this, ClientMainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickButtonAjouter(View view) {

        String nom = editNom.getText().toString();
        String prenom   = editPrenom.getText().toString();
        String adresse   = editAdresse.getText().toString();
        String email   = editEmail.getText().toString();
        String tel   = editTel.getText().toString();
        nouveauClient = new Client(nom, prenom, adresse, email, tel);

        InsertOrUpdate majClientDAO = new InsertOrUpdate();
        majClientDAO.execute();

    }

    /**
     *  désactive tous les widgets lors des Tâches asynchrone
     */
    public void disableAll()
    {
        editNom.setEnabled(false);
        editPrenom.setEnabled(false);
        editAdresse.setEnabled(false);
        editEmail.setEnabled(false);
        editTel.setEnabled(false);

        progress.setVisibility(View.VISIBLE);
    }

    public void enableAll(String s)
    {
        this.enableAll();
        Toast.makeText(CreateModifyClient.this, s, Toast.LENGTH_LONG).show();
        CreateModifyClient.this.finish();
    }

    private void enableAll(){

        editNom.setEnabled(true);
        editPrenom.setEnabled(true);
        editEmail.setEnabled(true);
        editAdresse.setEnabled(true);
        editTel.setEnabled(true);

        progress.setVisibility(View.GONE);
    }

    public void chargeClient(Client client){

        editNom.setText(client.getNom());
        editPrenom.setText(client.getPrenom());
        editAdresse.setText(client.getAdresse());
        editEmail.setText(client.getEmail());
        editTel.setText(client.getTel());

        this.enableAll();

    }


    // Class interne AsyncTask permettant de lancer une tâche lourde pouvant bloquer l'UI
    private class InsertOrUpdate extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params) {

            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (clientId == -1) {


                clientDAO.insert(nouveauClient);
            }
            else
            {
                clientDAO.setClient(clientId, nouveauClient);
            }
            return getString(R.string.return_dao);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            enableAll(s);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            disableAll();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
    }

    /**
     * Chargement de l'objet article depuis
     */
    private class ChargeClient extends AsyncTask<Integer, Integer, Void>{

        private Client client;

        @Override
        protected Void doInBackground(Integer... params) {

            this.client = clientDAO.getClientById(params[0]);

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            disableAll();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            chargeClient(this.client);
        }
    }


}
