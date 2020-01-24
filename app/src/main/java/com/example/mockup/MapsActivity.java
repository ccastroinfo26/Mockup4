package com.example.mockup;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private DrawerLayout drawer;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng Caxias_do_Sul = new LatLng(-29.173160, -51.176020);
        mMap.addMarker(new MarkerOptions().position(Caxias_do_Sul).title("Marcador em Caxias").icon(BitmapDescriptorFactory.fromResource(R.drawable.pokemon)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Caxias_do_Sul));

        LatLng Gramado = new LatLng(-29.378889, -50.873889);
        mMap.addMarker(new MarkerOptions().position(Gramado).title("Marcador em Gramado").icon(BitmapDescriptorFactory.fromResource(R.drawable.pokemon)));

        LatLng Porto_Alegre = new LatLng(-30.033056, -51.230000);
        mMap.addMarker(new MarkerOptions().position(Porto_Alegre).title("Marcador em POA").icon(BitmapDescriptorFactory.fromResource(R.drawable.pokemon)));

        LatLng Torres = new LatLng(-29.342778, -49.727500);
        mMap.addMarker(new MarkerOptions().position(Torres).title("Marcador em Torres").icon(BitmapDescriptorFactory.fromResource(R.drawable.pokemon)));

        LatLng SFP = new LatLng(-29.447778, -50.583889);
        mMap.addMarker(new MarkerOptions().position(SFP));


        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(SFP, 8.0f));
        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.addCircle(
                new CircleOptions()
                        .center(SFP)
                        .radius(100000.0)
                        .strokeWidth(3f)
                        .strokeColor(Color.RED)
                        .fillColor(Color.argb(70, 150, 50, 50)));

        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);

        mMap.addPolyline(new PolylineOptions().add(Caxias_do_Sul, Gramado, Porto_Alegre, Torres, Caxias_do_Sul).width(5).color(Color.RED));
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
