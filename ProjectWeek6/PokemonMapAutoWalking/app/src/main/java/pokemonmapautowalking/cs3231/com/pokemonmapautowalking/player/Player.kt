package pokemonmapautowalking.cs3231.com.pokemonmapautowalking.player

import android.content.Context
import android.location.Location
import android.os.Debug
import android.widget.Toast
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import pokemonmapautowalking.cs3231.com.pokemonmapautowalking.R
import pokemonmapautowalking.cs3231.com.pokemonmapautowalking.locationListener.MyLocationListener
import pokemonmapautowalking.cs3231.com.pokemonmapautowalking.pokemon.Pokemon

class Player(){
    private var lat:Double = 0.0;
    private var long:Double = 0.0;
    private var power:Int = 0;
    constructor(lat:Double,long: Double) : this() {
        this.lat = lat;
        this.long = long;
    }

    init {
        this.power = 0;
    }

    fun getLatLng(): LatLng {
        return LatLng(lat,long);
    }

    fun setDefoultLocation(){
        //Assumption university = 13.611944, 100.837828
        this.lat = 13.611944;
        this.long = 100.837828;
    }

    fun catchPokemon(pokemon: Pokemon,context: Context){
        power += pokemon.pow;
        pokemon.catched();
        Toast.makeText(context, "You catch "+pokemon.name+" Your current power is now "+ this.power, Toast.LENGTH_LONG).show();
    }
    fun getGoogleMapMarker(): MarkerOptions? {
           return MarkerOptions()
                    .position(this.getLatLng())
                    .title("Me")
                    .snippet("Here is my location")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.me))
    }
    fun getLocation(): Location {
        var userLocation = Location("Start");
        userLocation!!.latitude = this.lat
        userLocation!!.longitude = this.long
        return userLocation;
    }

    fun walkTo(pokemon: Pokemon,steps:Int): LatLng {
        var latDist = (pokemon.lat - this.lat)/steps;
        var longDist = (pokemon.long - this.long)/steps;

        this.lat += latDist;
        this.long += longDist;

        return LatLng(latDist,longDist);
    }
}