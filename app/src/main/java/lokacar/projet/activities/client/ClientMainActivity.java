package lokacar.projet.activities.client;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import lokacar.projet.R;
import lokacar.projet.activities.ActionsChoiceActivity;
import lokacar.projet.adapters.ClientAdapter;
import lokacar.projet.bo.client.Client;
import lokacar.projet.dal.clients.ClientDAO;


public class ClientMainActivity extends AppCompatActivity {


    ClientAdapter clientAdapter;
    private ClientDAO clientDAO;
    private ListView listViewClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_main_activity);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(android.R.drawable.btn_star);

        FloatingActionButton btnAjoutClient = (FloatingActionButton) findViewById(R.id.btnAjoutClient);
        btnAjoutClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientMainActivity.this, CreateModifyClient.class);
                startActivity(intent);
            }
        });

        //Création de la DAL

        clientDAO = new ClientDAO(ClientMainActivity.this);

        //Création d'une liste vide pour initialiser l'adapter

        List<Client> listeClient = new ArrayList<>();
        clientAdapter = new ClientAdapter(this, R.layout.client_item_list, listeClient);

        listViewClient = findViewById(R.id.listeClient);
        listViewClient.setAdapter(clientAdapter);

        listViewClient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Client client = (Client) parent.getItemAtPosition(position);
                if(!getIntent().getBooleanExtra("choixLocation", false)) {
                    Intent intent = new Intent(ClientMainActivity.this, ClientDetailsActivity.class);
                    intent.putExtra("clientId", client.getId());
                    startActivity(intent);
                } else {
                    Log.i("CLIENTTEST", client.toString());
                    Intent intent = new Intent ();
                    intent.putExtra("client", client);
                    Log.i("INTENT CLIENT", intent.getParcelableExtra("client").toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        }
        );

        listViewClient.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(
                        view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();

        //On charge la liste avant l'affichage de l'activité afin que la liste soit
        //mise à jour à chaque fois
        clientAdapter.clear();
        clientAdapter.addAll(clientDAO.getAllClients());
        clientAdapter.notifyDataSetChanged();

        }

        public void onClickAjouterButton(View view) {
            Intent intent = new Intent(ClientMainActivity.this, CreateModifyClient.class);
            startActivity(intent);
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
}
