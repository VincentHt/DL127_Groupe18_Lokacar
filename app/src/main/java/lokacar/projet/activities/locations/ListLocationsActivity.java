package lokacar.projet.activities.locations;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Date;
import java.util.List;

import lokacar.projet.R;
import lokacar.projet.adapters.LocationAdapter;
import lokacar.projet.bo.locations.Location;
import lokacar.projet.dal.locations.LocationDAO;

public class ListLocationsActivity extends AppCompatActivity {

    LocationDAO locationDAO;
    ListView locationListView;
    List<Location> listeLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_locations);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        locationListView = findViewById(R.id.locationListView);
        locationDAO = new LocationDAO(this);
        locationDAO.insertTest();
        listeLocations = locationDAO.getAllByDate(new Date(2018,1,15));
        locationListView.setAdapter(new LocationAdapter(this, R.layout.locations_item_list, listeLocations));

        locationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Location location = (Location) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListLocationsActivity.this, LocationEditOrCreateActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });

        FloatingActionButton btnAjoutLocation = (FloatingActionButton) findViewById(R.id.btnAjoutLocation);
        btnAjoutLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListLocationsActivity.this, LocationEditOrCreateActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton btnRechercheLocation = (FloatingActionButton) findViewById(R.id.btnRechercheLocation);
        btnRechercheLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });






    }

}
