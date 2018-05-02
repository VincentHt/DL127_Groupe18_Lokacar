package lokacar.projet.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import lokacar.projet.R;
import lokacar.projet.activities.locations.ListLocationsActivity;
import lokacar.projet.activities.vehicules.ListVehiculesActivity;

public class ActionsChoiceActivity extends AppCompatActivity {

    private Button btnGestionVehicules, btnGestionClients, btnGestionLocations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions_choice);

        btnGestionClients = findViewById(R.id.btnGestionClients);
        btnGestionVehicules = findViewById(R.id.btnGestionVehicules);
        btnGestionLocations = findViewById(R.id.btnGestionLocations);
    }

    public void gestionVehiculesOnClick(View view) {
        Intent intent = new Intent(ActionsChoiceActivity.this, ListVehiculesActivity.class);
        startActivity(intent);
    }

    public void gestionClientsOnClick(View view) {
        /*
        * Intent intent = new Intent(ActionsChoiceActivity.this, ClientsMainActivity.class);
        * startActivity(intent);
        */
    }

    public void gestionLocationsOnClick(View view) {
        Intent intent = new Intent(ActionsChoiceActivity.this, ListLocationsActivity.class);
        startActivity(intent);
    }
}
