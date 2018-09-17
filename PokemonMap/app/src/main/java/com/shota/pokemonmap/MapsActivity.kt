package com.shota.pokemonmap

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var pokemonList = ArrayList<Pokemon>();
    var playerPower = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        loadPokemon()
        checkPermission()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when(requestCode){
            888 -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    getUserLocation()
                }
                else{
                    Toast.makeText(this,"Failed to get your location", Toast.LENGTH_LONG).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private fun checkPermission() {
        if(Build.VERSION.SDK_INT >= 23){
            if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),888)
                return
            }
        }
        getUserLocation();
    }

    private fun getUserLocation() {
        Toast.makeText(this, "Got your location!",Toast.LENGTH_LONG).show()
        var myLocation = MyLocationListener()
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager;
       // locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,3f,myLocation);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,3,3f,myLocation)

        var myTread = MyTread()
        myTread.start();
    }

    var location:Location? = null
    inner class MyLocationListener: LocationListener{
        constructor(){
            location = Location("Start");
            location!!.latitude = 0.0;
            location!!.longitude = 0.0;
        }

        override fun onLocationChanged(newLocation: Location?) {
            location = newLocation;
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderEnabled(p0: String?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderDisabled(p0: String?) {
            //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    var oldLocation: Location? = null;
    inner class MyTread : Thread{
        constructor(){
            oldLocation = Location("Start");
            oldLocation!!.latitude = 0.0;
            oldLocation!!.longitude = 0.0;
        }

        override fun run() {
            while(true){
                try {
                    if(oldLocation!!.distanceTo(location) == 0f){
                        continue
                    }
                    oldLocation = location;
                    runOnUiThread{
                        mMap.clear()
                        var currentLocation = LatLng(location!!.latitude,location!!.longitude)
                        mMap.addMarker(MarkerOptions()
                                .position(currentLocation)
                                .title("Me")
                                .snippet("Here is my location")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.player)))
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,15f))

                        for(i in 0..pokemonList.size - 1){
                            var newPoke = pokemonList[i];
                            if(!newPoke.isCatched){
                                var pLocation = LatLng(newPoke.lat,newPoke.long);
                                mMap.addMarker(MarkerOptions()
                                        .position(pLocation)
                                        .title(newPoke.name)
                                        .snippet(newPoke.des)
                                        .icon(BitmapDescriptorFactory.fromBitmap(pPin)))
                            }
                            var pLocation = Location("Pokemon");
                            pLocation.latitude = newPoke.lat
                            pLocation.longitude = newPoke.long
                            if(location!!.distanceTo(pLocation)<2){

                            }
                        }
                    }
                    Thread.sleep(1000);
                }catch (e:Exception){

                }
            }
        }
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

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    fun loadPokemon(){
        pokemonList.add(Pokemon("フシギダネ"
                ,"うまれたときから　せなかにふしぎな　タネが　うえてあってからだと　ともに　そだつという。"
                , R.drawable.hushigidane
                ,100
                ,13.621536, 100.840534
                ,false));
        pokemonList.add(Pokemon("ヒトカゲ"
                ,"しっぽの　ほのおは　ヒトカゲのせいめいりょくの　あかし。　げんきだと　さかんに　もえさかる。"
                , R.drawable.hushigidane
                ,100
                ,13.612779, 100.833291
                ,false));
        pokemonList.add(Pokemon("ゼニガメ"
                ,"こうらに　とじこもり　みを　まもる。　あいての　すきを　みのがさずみずを　ふきだして　はんげきする。"
                , R.drawable.hushigidane
                ,100
                ,13.612060, 100.838752
                ,false));
    }
}
