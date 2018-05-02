package lokacar.projet.activities.client;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import lokacar.projet.R;
import lokacar.projet.adapters.ClientAdapter;
import lokacar.projet.bo.client.Client;
import lokacar.projet.dal.helper.client.ClientDAO;


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


        //Création de la DAL

        clientDAO = new ClientDAO(ClientMainActivity.this);

        //Création d'une liste vide pour initialiser l'adapter

        List<Client> listeClient = new ArrayList<>();
        clientAdapter = new ClientAdapter(this, R.layout.client_main_activity, listeClient);

        listViewClient = (ListView) findViewById(R.id.listeClient);
        listViewClient.setAdapter(clientAdapter);

        listViewClient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Client client = (Client) parent.getItemAtPosition(position);
                Intent intent = new Intent(ClientMainActivity.this, ClientDetailsActivity.class);
                intent.putExtra("clientId", client.getId());
                startActivity(intent);
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
        clientAdapter.addAll(clientDAO.getClient());
        clientAdapter.notifyDataSetChanged();

        }

        public void onClickAjouterButton(View view) {
            Intent intent = new Intent(ClientMainActivity.this, CreateModifyClient.class);
            startActivity(intent);
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_liste, menu);
            return true;
        }


        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }
