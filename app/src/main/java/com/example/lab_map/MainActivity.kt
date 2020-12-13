package com.example.lab_map

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions

class MainActivity : AppCompatActivity(),OnMapReadyCallback{
    private val REQUEST_PERMISSIONS=1
    private lateinit var map:GoogleMap
    lateinit var thisView: View



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isEmpty()) return
        when (requestCode) {
            REQUEST_PERMISSIONS -> {
                for (result in grantResults)
                    if (result != PackageManager.PERMISSION_GRANTED)
                        finish()
                    else {
                        val map = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
                        map.getMapAsync(this)
                    }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),REQUEST_PERMISSIONS)
        else{
            val map=supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            map.getMapAsync(this)
        }
    }


    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.isMyLocationEnabled = true
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED)

            return
        map.isMyLocationEnabled=true
        val marker=MarkerOptions()
        val marker1=MarkerOptions()
        marker.position(LatLng(25.04226382928084,121.53444170951843)) //前緯度後經度
        marker.title("台北科技大學站(建國)")
        marker.snippet("Under NTUT.The greatest college in the world")
        marker.draggable(false)
        marker.anchor(0.5f,0.5f)
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        //marker.icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_mylocation))
        map.addMarker(marker)


        marker1.position(LatLng(25.044674435126495,121.53282701969147))
        marker1.title("光華商場")
        marker1.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        marker1.draggable(false)
        map.addMarker(marker1)




        val polylineOpt= PolylineOptions()
        polylineOpt.add(LatLng(25.033611,121.565000))
        polylineOpt.add(LatLng(25.032728,121.565137))
        polylineOpt.add(LatLng(25.033611,121.517081))
        polylineOpt.color(Color.BLUE)
        val polyLine=map.addPolyline(polylineOpt)
        polyLine.width=10f
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(25.043536270155712,121.53464946927375),16f))
    }




}

        /*
        */

