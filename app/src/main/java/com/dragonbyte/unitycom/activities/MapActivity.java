package com.dragonbyte.unitycom.activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.dragonbyte.unitycom.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {


    private ArrayList<Marker> markerList = new ArrayList <Marker>();

    GoogleMap myMap;

    private LatLngBounds.Builder bounds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map3);
        mapFragment.getMapAsync(this);
        SharedPreferences prefs = getSharedPreferences("itemList", Context.MODE_PRIVATE);
        String httpParamJSONList = prefs.getString("itemList", "");
    }


    class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
        private final View mContentsView;
        MyInfoWindowAdapter() {
            mContentsView = getLayoutInflater().inflate(R.layout.map_info_window, null);
        }

        public class MarkerCallback implements Callback {
            Marker marker=null;

            MarkerCallback(Marker marker) {
                this.marker=marker;
            }

            @Override
            public void onError() {
            }

            @Override
            public void onSuccess() {
                if (marker != null && marker.isInfoWindowShown()) {
                    marker.hideInfoWindow();
                    marker.showInfoWindow();
                }
            }
        }

        @Override
        public View getInfoWindow(Marker marker) {

            return mContentsView;
        }

        @Override
        public View getInfoContents(Marker marker) {
            return null;
        }
    }
    void addMarkers () {
        int i = 0;

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;
        bounds = new LatLngBounds.Builder();
        addMarkers();
        myMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 50));
        myMap.setInfoWindowAdapter(new MyInfoWindowAdapter());
        myMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                /*
                Intent intent = new Intent(MapActivity.this, SavedLocationActivity.class);
                Item item = myList.get((int) marker.getTag());
                intent.putExtra("picture", item.address);
                intent.putExtra("lat", item.latitude);
                intent.putExtra("lon", item.longitude);
                startActivity(intent);
            */

            }
        });

    }

    @Override
    public void onMapClick(LatLng latLng) {

    }


}
