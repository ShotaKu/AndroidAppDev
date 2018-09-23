package pokemonmapautowalking.cs3231.com.pokemonmapautowalking

import android.location.*
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import pokemonmapautowalking.cs3231.com.pokemonmapautowalking.player.Player
import pokemonmapautowalking.cs3231.com.pokemonmapautowalking.pokemon.Pokedex

import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var pokedex = Pokedex();
    private var player = Player();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        pokedex.load();
        player.setDefoultLocation();
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
        var myThread = MyThread();
        myThread.start();
    }

//    var oldLocation: Location? = null
    var nowSeePokemon = 0;
    var steps = 20;
    inner class MyThread : Thread {

        constructor() : super() {
            nowSeePokemon = 0;
//            oldLocation = Location("Start")
//            oldLocation!!.latitude = 0.0
//            oldLocation!!.longitude = 0.0
        }

        override fun run() {
            while (true) {
                try {
//                    if (oldLocation!!.distanceTo(userLocation) == 0f) {
//                        continue
//                    }
//                    oldLocation = userLocation
                    var userLocation = player.getLocation();
                    runOnUiThread {
                        mMap.clear()
                        mMap!!.addMarker(player.getGoogleMapMarker())
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(player.getLatLng(), 18f))

                        for (i in 0..pokedex.size - 1) {
                            var newPokemon = pokedex.getPokemon(i)!!;
                            if (!newPokemon.isCatch) {
                                var pokemonLocation = LatLng(newPokemon.lat, newPokemon.long)
                                mMap!!.addMarker(newPokemon.getGoogleMapMarker())
                                var pokemonL = newPokemon.getLocation();
                                if (userLocation!!.distanceTo(pokemonL) < 2) {
                                    player.catchPokemon(newPokemon,applicationContext);
                                    nowSeePokemon++;
                                    steps = 20;
//                                    newPokemon.isCatch = true
//                                    playerPower += newPokemon.power
//                                    Toast.makeText(applicationContext, "You catch ${newPokemon.name}. Your current power is now $playerPower", Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                        if(nowSeePokemon<pokedex.size){
                            val p = pokedex.getPokemon(nowSeePokemon)!!;
                            player.walkTo(p,steps);
                        }else{
                            Thread.sleep(1000)
                            Toast.makeText(applicationContext,"You got all Pokemon!! Congratulation!!", Toast.LENGTH_LONG).show()
                            //Get all pokemon!
                        }
                        steps--;
                    }

                    Thread.sleep(1000)
                } catch (ex: Exception) {
                }
            }
        }

    }
}
