package lokacar.projet.activities.locations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_create_or_modify);

        tvDateDebut = findViewById(R.id.txtDateDebut);
        tvDateFin = findViewById(R.id.txtDateFin);
        tvClientNom = findViewById(R.id.txtViewNomClient);
        tvVehicule = findViewById(R.id.txtViewVehicule);

        Intent intent = getIntent();

        location = intent.getParcelableExtra("location");

        if(location != null){
            tvDateDebut.setText(location.getDateDebut().toString());
            tvDateFin.setText(location.getDateFin().toString());
            String textClient = location.getClient().getNom() + " " + location.getClient().getPrenom();
            tvClientNom.setText(textClient);
            String textVehicule = location.getVehicule().getMarque() + " " + location.getVehicule().getModele();
            tvVehicule.setText(textVehicule);
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
            Toast.makeText(this, "Aucun client sélectionné", Toast.LENGTH_LONG).show();
        }
    }

    public void rechercheClientOnClick(View view) {
        Intent intent = new Intent(this, ClientMainActivity.class);
        intent.putExtra("choixLocation", true);
        startActivity(intent);
    }

    public void detailVehiculeOnClick(View view) {
        if(getIntent().getParcelableExtra("vehicule") != null) {
            Intent intent = new Intent(this, DetailsVehiculeActivity.class);
            intent.putExtra("idVehicule", location.getVehicule().getId());
            startActivity(intent);
        } else {
            Toast.makeText(this, "Aucun véhicule sélectionné", Toast.LENGTH_LONG).show();
        }
    }

    public void rechercheVehiculeOnClick(View view) {
        Intent intent = new Intent(this, ListVehiculesActivity.class);
        intent.putExtra("choixLocation", true);
        startActivity(intent);
    }

    public void validationEditOrCreateOnClick(View view) {
        LocationDAO locationDAO = new LocationDAO(this);

        if(location != null){

            locationDAO.update(location);
            Intent intent = new Intent(this, ListLocationsActivity.class);
            startActivity(intent);
        } else {

            String[] dateDebutTableau = tvDateDebut.getText().toString().split("/");
            String[] dateFinTableau = tvDateFin.getText().toString().split("/");

            if(dateDebutTableau.length == 3 && dateFinTableau.length == 3 && tvDateDebut.getText().length()==10 && tvDateFin.getText().length()==10){
                Date dateDebut = new Date(Integer.parseInt(dateDebutTableau[2]), Integer.parseInt(dateDebutTableau[1]), Integer.parseInt(dateDebutTableau[0]));
                Date dateFin = new Date(Integer.parseInt(dateFinTableau[2]), Integer.parseInt(dateFinTableau[1]), Integer.parseInt(dateFinTableau[0]));

                Client client = getIntent().getParcelableExtra("client");
                Vehicule vehicule = getIntent().getParcelableExtra("vehicule");

                location = new Location(dateDebut, dateFin, vehicule, client);
                locationDAO.insert(location);
            } else {
                Toast.makeText(this, "Erreur de saisie de date", Toast.LENGTH_LONG).show();
            }

        }

    }
}
