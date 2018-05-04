package lokacar.projet.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import lokacar.projet.activities.client.ClientMainActivity;
import lokacar.projet.app.AppName;

import lokacar.projet.R;
import lokacar.projet.activities.locations.ListLocationsActivity;
import lokacar.projet.activities.vehicules.ListVehiculesActivity;

public class ActionsChoiceActivity extends AppCompatActivity {

    android.content.Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions_choice);

        String mAppName = ((AppName) this.getApplication()).getmAppName();
        setTitle(mAppName);
    }


    public void gestionLocationsOnClick(View view) {
        Intent intent = new Intent(ActionsChoiceActivity.this, ListLocationsActivity.class);
        startActivity(intent);
    }

    public void choixListeVehiculeOnClick(View view){
        Class destinationActivity = ListVehiculesActivity.class;
            Intent myIntent = new Intent(context, destinationActivity);
            startActivity(myIntent);
    }


    public void choixListeClientOnClick(View view) {

        Intent dansTata = new Intent(ActionsChoiceActivity.this, ClientMainActivity.class);
        startActivity(dansTata);

    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
