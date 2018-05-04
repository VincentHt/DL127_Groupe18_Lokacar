package lokacar.projet.activities.client;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import lokacar.projet.R;
import lokacar.projet.bo.client.Client;
import lokacar.projet.dal.helper.client.ClientDAO;


public class ClientDetailsActivity extends AppCompatActivity {

    private Client client;
    private ClientDAO clientDAO;
    private int clientId;
    private TextView nom;
    private TextView prenom;
    private TextView adresse;
    private TextView email;
    private TextView tel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);

    //   getSupportActionBar().setDisplayShowHomeEnabled(true);
     //   getSupportActionBar().setIcon(android.R.drawable.btn_star);
        clientDAO = new ClientDAO(ClientDetailsActivity.this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_client_detail, menu);

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();

        nom =  findViewById(R.id.textClientNom);
        prenom =  findViewById(R.id.textClientPrenom);
        adresse = findViewById(R.id.textClientAdresse);
        email =  findViewById(R.id.textClientEmail);
        tel =  findViewById(R.id.textClientTel);


        clientId = intent.getIntExtra("clientId", 0);

        ChargeClient chargeArticle = new ChargeClient();
        chargeArticle.execute(clientId);


    }

    @Override
    public void onPause() {
        super.onPause();

    }

    public void enableAllClient() {

    }

    public void disableAllClient() {


    }
        private class ChargeClient extends AsyncTask<Integer, Integer, Void> {

        private Client client;

        @Override
        protected Void doInBackground(Integer... params) {

            this.client = clientDAO.getClientById(params[0]);

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            disableAllClient();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            chargeClient(this.client);
        }
    }

    private void chargeClient(Client cli){

        this.client = cli;

        nom.setText(client.getNom());
        prenom.setText(client.getPrenom());
        adresse.setText(client.getAdresse());
        email.setText(client.getEmail());
        tel.setText(client.getTel());

        enableAllClient();
    }

    private class DeleteClient extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params) {

            clientDAO.deleteClient(clientId);

            return getString(R.string.return_dao);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            enableAllClient();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            disableAllClient();
        }

    }


    private class UpdateClient extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params) {


            clientDAO.setClient(clientId, client);

            return getString(R.string.return_dao);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            enableAllClient();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            disableAllClient();
        }

    }


    public void onClickModifyButton(View view) {
        Intent intent = new Intent(ClientDetailsActivity.this, CreateModifyClient.class);
        intent.putExtra("clientId", clientId);
        startActivity(intent);
    }

    public void onClickDeleteButton(View view) {
        Toast.makeText(ClientDetailsActivity.this, R.string.client_delete, Toast.LENGTH_LONG).show();
        DeleteClient deleteThread = new DeleteClient();
        deleteThread.execute();
        ClientDetailsActivity.this.finish();

    }
}
