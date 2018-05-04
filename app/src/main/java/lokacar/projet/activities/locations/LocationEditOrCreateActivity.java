package lokacar.projet.activities.locations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import lokacar.projet.R;
import lokacar.projet.activities.ActionsChoiceActivity;
import lokacar.projet.activities.client.ClientDetailsActivity;
import lokacar.projet.activities.client.ClientMainActivity;
import lokacar.projet.activities.vehicules.DetailsVehiculeActivity;
import lokacar.projet.activities.vehicules.ListVehiculesActivity;
import lokacar.projet.bo.client.Client;
import lokacar.projet.bo.locations.Location;
import lokacar.projet.bo.vehicules.Vehicule;
import lokacar.projet.dal.locations.LocationDAO;

public class LocationEditOrCreateActivity extends AppCompatActivity {

    TextView tvDateDebut, tvDateFin, tvClientNom, tvVehicule;
    Location location;
    Vehicule vehicule;
    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_create_or_modify);

        tvDateDebut = findViewById(R.id.editDateDeDebut);
        tvDateFin = findViewById(R.id.editDateDeFin);
        tvClientNom = findViewById(R.id.txtViewNomClient);
        tvVehicule = findViewById(R.id.txtViewVehicule);

        Intent intent = getIntent();

        location = intent.getParcelableExtra("location");
        Log.i("TESTEDIT", location.toString());

        if(location != null){
            client = location.getClient();
            vehicule = location.getVehicule();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

            tvDateDebut.setText(String.valueOf(sdf.format(location.getDateDebut())));
            tvDateFin.setText(String.valueOf(sdf.format(location.getDateFin())));

            String textClient = client.getNom() + " " + client.getPrenom();
            tvClientNom.setText(textClient);

            String textVehicule = vehicule.getMarque() + " " + vehicule.getModele();
            tvVehicule.setText(textVehicule);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 18:
                client = data.getParcelableExtra("client");
                String textClient = client.getNom() + " " + client.getPrenom();
                tvClientNom.setText(textClient);
                break;
            case 12:
                vehicule = data.getParcelableExtra("vehicule");
                Log.i("TEST INSERTION VEHICULE", vehicule.toString());
                String textVehicule = vehicule.getMarque() + " " + vehicule.getModele();
                tvVehicule.setText(textVehicule);
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.menu_liste, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.retour:
                Intent intent = new Intent(this, ListVehiculesActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void detailClientOnClick(View view) {
        if(getIntent().getParcelableExtra("client") != null) {
            Intent intent = new Intent(this, ClientDetailsActivity.class);
            intent.putExtra("idClient", location.getClient().getId());
            startActivity(intent);
        } else {
            Toast.makeText(this, "Aucun client sélectionné", Toast.LENGTH_SHORT).show();
        }
    }

    public void rechercheClientOnClick(View view) {
        Intent intent = new Intent(this, ClientMainActivity.class);
        intent.putExtra("choixLocation", true);
        startActivityForResult(intent, 18);
    }

    public void detailVehiculeOnClick(View view) {
        if(getIntent().getParcelableExtra("vehicule") != null) {
            Intent intent = new Intent(this, DetailsVehiculeActivity.class);
            intent.putExtra("idVehicule", location.getVehicule().getId());
            startActivity(intent);
        } else {
            Toast.makeText(this, "Aucun véhicule sélectionné", Toast.LENGTH_SHORT).show();
        }
    }

    public void rechercheVehiculeOnClick(View view) {
        Intent intent = new Intent(this, ListVehiculesActivity.class);
        intent.putExtra("choixLocation", true);
        startActivityForResult(intent, 12);
    }

    public void validationEditOrCreateOnClick(View view) {
        LocationDAO locationDAO = new LocationDAO(this);

        if(location != null){
            locationDAO.update(location);
        } else {
            String s = tvDateDebut.getText().toString();
            String[] dateDebutTableau = String.valueOf(tvDateDebut.getText()).split("/");
            String[] dateFinTableau = tvDateFin.getText().toString().split("/");

            Log.i("tableauxDateDebut", "Date début : " + dateDebutTableau[2]+" - "+dateDebutTableau[1]+" - "+dateDebutTableau[0]);
            Log.i("tableauxDateFin", "Date fin : " + dateFinTableau[2]+" - "+dateFinTableau[1]+" - "+dateFinTableau[0]);

            if(dateDebutTableau.length == 3 && dateFinTableau.length == 3 && tvDateDebut.getText().length()==10 && tvDateFin.getText().length()==10){
                Date dateDebut = new Date((Integer.parseInt(dateDebutTableau[2])-1900), Integer.parseInt(dateDebutTableau[1])-1, Integer.parseInt(dateDebutTableau[0])+0);
                Date dateFin = new Date((Integer.parseInt(dateFinTableau[2])-1900), Integer.parseInt(dateFinTableau[1])-1, Integer.parseInt(dateFinTableau[0])+0);

                location = new Location(dateDebut, dateFin, vehicule, client);
                locationDAO.insert(location);
                finish();
            } else {
                Toast.makeText(this, "Erreur de saisie de date", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
