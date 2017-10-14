package com.example.divyankitharaghavaurs.smartcitybarcodescanner;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.wearable.internal.ChannelSendFileResponse;


/**
 * Created by divyankithaRaghavaUrs on 8/29/17.
 */

public class LocateMap extends AppCompatActivity implements OnMapReadyCallback //Main map activity used to diaplay maps to the user
{
    private int PROXIMITY_RADIUS = 10000;
    private static final int PERMISSION_REQUEST_CODE = 1;
    GoogleMap mMap;
    double lat = 0, lng =0 ;
    String bestProvider;
    Criteria criteria;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }



    @Override
    public void onMapReady(GoogleMap map)
    {
        mMap = map;
        /*if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
        {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {
                //
            } else
            {
                Log.d("permission", "permission denied for maps - requesting it");
                String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

                requestPermissions(permissions, PERMISSION_REQUEST_CODE);


            }
        }*/

        LocationManager locationMangaer = null;


        locationMangaer = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Location currentlocation = locationMangaer.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(currentlocation != null)
        {
            lat = currentlocation.getLatitude();
            lng = currentlocation.getLongitude();
        }
        else
        {
            //locationManager.requestLocationUpdates(bestProvider, 1000, 0, this);
            System.out.println("No location");
        }


        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        CameraPosition googlePlex = CameraPosition.builder()
                .target(new LatLng(lat,lng))
                .zoom(16)
                .bearing(0)
                .tilt(45)
                .build();

        map.addMarker(new MarkerOptions()
                .position(new LatLng(lat, lng))
                .title("Current Location"));

        //map.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null);

        map.moveCamera(CameraUpdateFactory.newCameraPosition(googlePlex));
        map.setMyLocationEnabled(true);



        Button btnRestaurant = (Button) findViewById(R.id.restuarant);
        btnRestaurant.setOnClickListener(new View.OnClickListener() {
            String Restaurant = "restaurant";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                mMap.clear();
                String url = getUrl(lat, lng, Restaurant);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(LocateMap.this,"Fetching Nearby Restaurants", Toast.LENGTH_LONG).show();
            }
    });

        Button btnHospital = (Button) findViewById(R.id.hospital);
        btnHospital.setOnClickListener(new View.OnClickListener() {
            String Hospital = "hospital";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                mMap.clear();
                String url = getUrl(lat, lng, Hospital);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(LocateMap.this,"Fetching Nearby Hospitals", Toast.LENGTH_LONG).show();
            }
        });

        Button btnMall = (Button) findViewById(R.id.school);
        btnMall.setOnClickListener(new View.OnClickListener() {
            String Mall = "shopping_mall";
            @Override
            public void onClick(View v) {
                Log.d("onClick", "Button is Clicked");
                mMap.clear();
                String url = getUrl(lat, lng, Mall);
                Object[] DataTransfer = new Object[2];
                DataTransfer[0] = mMap;
                DataTransfer[1] = url;
                Log.d("onClick", url);
                GetNearbyPlacesData getNearbyPlacesData = new GetNearbyPlacesData();
                getNearbyPlacesData.execute(DataTransfer);
                Toast.makeText(LocateMap.this,"Fetching Nearby Shopping malls", Toast.LENGTH_LONG).show();
            }
        });
    }



    private String getUrl(double latitude, double longitude, String nearbyPlace) {

        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlacesUrl.append("&type=" + nearbyPlace);
        //googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + "AIzaSyDlV5jQEbbyU7dlRvFLfYMU3ECSypUEptc");
        googlePlacesUrl.append("&sensor=true");
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }
}
