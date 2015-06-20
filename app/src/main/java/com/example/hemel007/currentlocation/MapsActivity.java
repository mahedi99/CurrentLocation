package com.example.hemel007.currentlocation;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements GPSTracker.GetLocationFrequently{

    Marker marker;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    //private  gpsTracker;
    GPSTracker gpsTracker;

    double startOne=0,startTwo=0;
    double lati=0;
    double longi=0;


    LatLng latLng;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        gpsTracker = new GPSTracker(this,this);
        //gpsTracker.showSettingsAlert();

        String stringLatitude = String.valueOf(gpsTracker.latitude);
        String stringLongitude = String.valueOf(gpsTracker.longitude);
        lati = Double.parseDouble(stringLatitude);
        longi = Double.parseDouble(stringLongitude);






        //latLng.latitude =

       // latLng = data;

        setUpMapIfNeeded();
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
//        mMap.addMarker(new MarkerOptions().position(new LatLng(lati, longi)).title("Marker"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lati, longi), 18));
//        mMap.setMyLocationEnabled(true);
        startOne=lati;
        startTwo=longi;
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(lati, longi))
                .title("Starting")).showInfoWindow();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lati, longi), 18));
        mMap.setMyLocationEnabled(true);
        Toast.makeText(getApplicationContext(),lati+"   "+longi,Toast.LENGTH_LONG).show();


    }

    @Override
    public void getLoc(Location loc) {

        Toast.makeText(getApplicationContext(),loc.getLatitude()+"   "+loc.getLatitude(),Toast.LENGTH_LONG).show();

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(loc.getLatitude(), loc.getLongitude()))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                .title("Marker"));
        mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(startOne, startTwo), new LatLng(loc.getLatitude(), loc.getLongitude()))
                .width(5)
                .color(Color.RED));
        startOne=loc.getLatitude();
        startTwo=loc.getLongitude();
        mMap.setMyLocationEnabled(true);

    }

}
