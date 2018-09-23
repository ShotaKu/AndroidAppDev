package pokemonmapautowalking.cs3231.com.pokemonmapautowalking.pokemon

import android.location.Location
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Pokemon(){
    public var name:String = "";
    public var des:String = "";
    public var image:Int = 0;
    public var pow:Int = 0;
    public var lat:Double = 0.0;
    public var long:Double = 0.0;
    public var isCatch = false;
    constructor(name: String, des: String,image: Int, power: Int,lat: Double, long: Double, isCatch: Boolean) : this() {
        this.name = name;
        this.des = des;
        this.image = image;
        this.pow = power;
        this.lat = lat;
        this.long = long;
        this.isCatch = isCatch;
    }
    fun getLatLng(): LatLng {
        return LatLng(this.lat,this.long);
    }

    fun catched() {
        this.isCatch = true;
    }

    fun getGoogleMapMarker(): MarkerOptions? {
        return MarkerOptions()
                .position(this.getLatLng())
                .title(this.name)
                .snippet(this.des)
                .icon(BitmapDescriptorFactory.fromResource(this.image))
    }

    fun getLocation(): Location? {
        var loc = Location("pokemon")
        loc.latitude = this.lat
        loc.longitude = this.long
        return loc;
    }
}