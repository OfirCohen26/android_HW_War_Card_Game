package com.example.myapplication1hw;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Fragment_Map  extends Fragment implements OnMapReadyCallback {

    private GoogleMap gMap;
    private View rootView;
    private double dLatitude;
    private double dLongtitude;

    public static Fragment_Map newInstance() {
        Log.d("newInstance", "newInstance");

        Fragment_Map fragment = new Fragment_Map();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("dddd", "onCreateView");

        // Initialize view
        if(rootView==null) {
            rootView = inflater.inflate(R.layout.fragment_map, container, false);
        }
        // Initialize map fragment
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_LAY_map);

        // Async map
        supportMapFragment.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        final int ZOOM = 15;
        gMap = googleMap;
        LatLng latLng = new LatLng(dLatitude, dLongtitude);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng);
        gMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, ZOOM));
        gMap.addMarker(markerOptions);
    }
}

