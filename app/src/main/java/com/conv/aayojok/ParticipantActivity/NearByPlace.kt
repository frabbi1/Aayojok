package com.conv.aayojok.ParticipantActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.conv.aayojok.Models.CurrentEvent
import com.conv.aayojok.Models.CurrentPlace
import com.conv.aayojok.Models.Place
import com.conv.aayojok.R
import com.conv.aayojok.services.BackEndService
import com.conv.aayojok.services.ServiceBuilder

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Response

class NearByPlace : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var lon = 0.0
    var lat = 0.0
    var loc = ""
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val plc = LatLng(lon, lat)
        val zoom = 18.0f
        mMap.addMarker(MarkerOptions().position(plc).title(loc))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(plc,zoom))
        mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_near_by_place)

        var intent = intent
        lon = intent.getStringExtra("lon").toDouble()
        lat = intent.getStringExtra("lat").toDouble()
        loc = intent.getStringExtra("loc")

        Toast.makeText(this, "Click on the marker for more", Toast.LENGTH_SHORT).show()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.nearby_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
}
