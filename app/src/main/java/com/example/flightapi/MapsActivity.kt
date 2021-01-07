package com.example.flightapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.flightapi.models.FlightFeed

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // J'ajoute un marker sur la position du vol et je bouge la caméra

        val jsonBody = intent.getStringExtra("flightObject")
        val gson = GsonBuilder().create()
        val sType = object : TypeToken<List<FlightFeed>>() { }.type
        val flightFeed = gson.fromJson<List<FlightFeed>>(jsonBody, sType)
        val flightObject = flightFeed[0]

        val latitude = flightObject.geography.latitude
        val longitude = flightObject.geography.longitude


        val flightPosition = LatLng(latitude.toDouble(), longitude.toDouble())
        mMap.addMarker(MarkerOptions().position(flightPosition).title("Marker in flight position"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(flightPosition, 5f))
    }
}