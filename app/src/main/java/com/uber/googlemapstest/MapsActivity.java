package com.uber.googlemapstest;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnCameraChangeListener {

    private GoogleMap mGoogleMap;
    private LatLng mLatLngGoldenGateBridge = new LatLng(37.819877, -122.478939);

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override public void onCameraChange(CameraPosition cameraPosition) {
        LatLng latLng = cameraPosition.target;

        new AlertDialog.Builder(this)
                .setTitle(String.format("%s %s", latLng.latitude, latLng.longitude))
                .create()
                .show();
    }

    private void setUpMapIfNeeded() {
        if (mGoogleMap == null) {
            mGoogleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            if (mGoogleMap != null) {
                mGoogleMap.setOnCameraChangeListener(this);
                mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLngGoldenGateBridge, 15));
            }
        }
    }
}
