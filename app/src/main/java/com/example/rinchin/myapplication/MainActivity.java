package com.example.rinchin.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;



import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

//public class MainActivity extends AppCompatActivity {
//public class MainActivity extends FragmentActivity implements OnMapReadyCallback{
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    ArrayList<myPoint> listLatLng = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Array and
        listLatLng.add(new myPoint("Москва Билайн",new LatLng(55.773596, 37.609142),"Билайн"));
        listLatLng.add(new myPoint("Test",new LatLng(55.773716, 37.606313),"Test"));
        listLatLng.add(new myPoint("Test2",new LatLng(55.774452, 37.606320),"Test2"));

        for (myPoint point :listLatLng) {
            mMap.addMarker(new MarkerOptions().position(point.latLng).title(point.Name));
        }

        //move the camera
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(listLatLng.get(0).latLng, 17));
    }



    //My own method for adding in Array new Points
    void addPoints(myPoint point){
        listLatLng.add(point);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    class myPoint{
        String Name;
        String comments;
        LatLng latLng;

        public myPoint(String Name, double latitude, double longitude, String comments){
            this.Name = Name;
            this.latLng = new LatLng(latitude,longitude);
            this.comments = comments;
        }

        public myPoint(String Name, LatLng latLng, String comments){
            this.Name = Name;
            this.latLng = latLng;
            this.comments = comments;
        }
    }
}
