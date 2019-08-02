package com.conv.aayojok.ParticipantActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.conv.aayojok.Models.CurrentEvent
import com.conv.aayojok.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class LocationActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        Toast.makeText(this,"Click on the Marker for Google Map Direction Option", Toast.LENGTH_SHORT).show()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val loc = LatLng(CurrentEvent.longitude.toDouble(), CurrentEvent.latitude.toDouble())
        mMap.addMarker(MarkerOptions().position(loc).title(CurrentEvent.location))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 18.0f))
    }
}
