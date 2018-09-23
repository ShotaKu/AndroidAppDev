package kiratijuta.pokemonmap

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    var listOfPokemon = ArrayList<Pokemon>()
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

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
//        val sydney = LatLng(-34.0, 151.0)
//        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    fun loadPokemon() {

        listOfPokemon.add(
                Pokemon("Charmander",
                        "This is Charmander.",
                        R.drawable.charmander,
                        100,
                        13.613273, 100.835483,
                        false))

        listOfPokemon.add(
                Pokemon("Bulbasaur",
                        "This is Bulbasaur.",
                        R.drawable.bulbasaur,
                        150,
                        13.613325, 100.834791,
                        false))

        listOfPokemon.add(
                Pokemon("Squirtle",
                        "This is Squirtle.",
                        R.drawable.squirtle,
                        200,
                        13.611135, 100.839197,
                        false))


    }

    fun checkPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        888)
                return
            }
        }

        getUserLocation()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            888 -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getUserLocation()
                } else {
                    Toast.makeText(this, "We cannot access to your location", Toast.LENGTH_LONG).show()
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    fun getUserLocation() {

        Toast.makeText(this, "User location access ON", Toast.LENGTH_LONG).show()

        var myLocation = MyLocationListener()
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3, 3f, myLocation)

        var myThread = MyThread()
        myThread.start()
    }

    var userLocation: Location? = null
    inner class MyLocationListener: LocationListener {

        constructor() {
            userLocation = Location("Start")
            userLocation!!.latitude = 0.0
            userLocation!!.longitude = 0.0
        }

        override fun onLocationChanged(location: Location?) {
            userLocation = location
        }

        override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderEnabled(provider: String?) {
            //To change body of created functions use File | Settings | File Templates.
        }

        override fun onProviderDisabled(provider: String?) {
            //To change body of created functions use File | Settings | File Templates.
        }
    }


    var oldLocation: Location? = null
    inner class MyThread: Thread {

        constructor():super() {
            oldLocation = Location("Start")
            oldLocation!!.latitude = 0.0
            oldLocation!!.longitude = 0.0
        }

        override fun run() {
            while (true) {
                try {
                    if (oldLocation!!.distanceTo(userLocation) == 0f) {
                        continue
                    }

                    oldLocation = userLocation

                    runOnUiThread {
                        mMap.clear()

                        var currentLocation = LatLng(userLocation!!.latitude,
                                                     userLocation!!.longitude)
                        mMap!!.addMarker(MarkerOptions()
                                .position(currentLocation)
                                .title("Me")
                                .snippet("Here is my location")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.me)))
                        mMap.moveCamera(
                                CameraUpdateFactory.newLatLngZoom(currentLocation, 18f))

                        for (i in 0..listOfPokemon.size-1) {
                            var newPokemon = listOfPokemon[i]
                            if (!newPokemon.isCatch) {
                                var pokemonLocation = LatLng(newPokemon.lat, newPokemon.long)
                                mMap!!.addMarker(MarkerOptions()
                                        .position(pokemonLocation)
                                        .title(newPokemon.name)
                                        .snippet(newPokemon.des)
                                        .icon(BitmapDescriptorFactory.fromResource(newPokemon.image)))

                                var pokemonL = Location("pokemon")
                                pokemonL.latitude = newPokemon.lat
                                pokemonL.longitude = newPokemon.long
                                if (userLocation!!.distanceTo(pokemonL) < 2) {
                                    newPokemon.isCatch = true
                                    playerPower += newPokemon.power
                                    Toast.makeText(applicationContext, "You catch ${newPokemon.name}. Your current power is now $playerPower", Toast.LENGTH_LONG).show()
                                }
                            }
                        }


                    }

                    Thread.sleep(1000)
                } catch (ex: Exception) {}
            }
        }

    }

}








