package com.example.shaur.nimblenavigationdrawer;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private UiSettings mUiSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map)).getMapAsync(this);





    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        mUiSettings = mMap.getUiSettings();
        mUiSettings.setScrollGesturesEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        // Add a marker in JIIT and move the camera
        LatLng JIIT = new LatLng(28.630509, 77.372091);
        mMap.addMarker(new MarkerOptions().position(JIIT).title("Marker in JIIT"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(JIIT));
        // Add some markers to the map, and add a data object to each marker.
        LatLng OAT= new LatLng(28.63076925, 77.37155378);
        LatLng TEMPLE= new LatLng(25.363882, 131.044922);
        LatLng CAFETERIA= new LatLng(28.63039728, 77.37100393);
        LatLng H3= new LatLng(28.62978047, 77.37344474);
        LatLng H5= new LatLng(28.62932845, 77.37380952);
        LatLng JBS= new LatLng(28.62930019, 77.37143844);
        LatLng SAROJINI= new LatLng(28.6293308, 77.37201244);
        LatLng ANNAPURNA= new LatLng(28.62937318, 77.37286001);
        LatLng A2Z= new LatLng(28.62944381, 77.37321943);
        LatLng ABB1= new LatLng(28.63071746, 77.37207547);
        LatLng OAT_GROUND= new LatLng(28.63052912, 77.3715739);
        LatLng PLAY_GROUND= new LatLng(28.62980401, 77.37158462);
        LatLng H4= new LatLng(28.62983697, 77.37380818);
        LatLng ABB3 = new LatLng(28.63052912, 77.37100258);
        //Marker Temple = mMap.addMarker(new MarkerOptions()
        //.position(new google.maps.LatLng(-25.363882,131.044922))
        //       .title("Temple"));
        mMap.addMarker(new MarkerOptions()
                .position(OAT)
                .title("OAT"));
        mMap.addMarker(new MarkerOptions()
                .position(TEMPLE)
                .title("Temple"));
        mMap.addMarker(new MarkerOptions()
                .position(CAFETERIA)
                .title("Cafeteria"));
        mMap.addMarker(new MarkerOptions()
                .position(H3)
                .title("H3 Hostel"));
        mMap.addMarker(new MarkerOptions()
                .position(H5)
                .title("H5 Hostel"));
        mMap.addMarker(new MarkerOptions()
                .position(JBS)
                .title("Jaypee Business School"));
        mMap.addMarker(new MarkerOptions()
                .position(H4)
                .title("H4 Hostel"));
        mMap.addMarker(new MarkerOptions()
                .position(SAROJINI)
                .title("Sarojini Bhawan"));
        mMap.addMarker(new MarkerOptions()
                .position(A2Z)
                .title("AtoZ"));
        mMap.addMarker(new MarkerOptions()
                .position(ANNAPURNA)
                .title("Annapurna"));
        mMap.addMarker(new MarkerOptions()
                .position(ABB1)
                .title("Aryabhatta Bhawan I"));
        mMap.addMarker(new MarkerOptions()
                .position(OAT_GROUND)
                .title("OAT Ground"));
        mMap.addMarker(new MarkerOptions()
                .position(PLAY_GROUND)
                .title("Play Ground"));
        mMap.addMarker(new MarkerOptions()
                .position(ABB3)
                .title("Aryabhatta Bhawan III"));



        // Set a listener for marker click.
        mMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) this);

    }
    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();

        }
        return false;
    }
}