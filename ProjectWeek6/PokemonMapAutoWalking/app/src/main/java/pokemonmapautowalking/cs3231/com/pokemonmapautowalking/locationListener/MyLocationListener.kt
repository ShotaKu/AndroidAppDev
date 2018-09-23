package pokemonmapautowalking.cs3231.com.pokemonmapautowalking.locationListener

import android.location.Location
import android.location.LocationListener
import android.os.Bundle


class MyLocationListener: LocationListener {
    var userLocation: Location? = null
    constructor() {
        userLocation = Location("Start")
        userLocation!!.latitude = 0.0
        userLocation!!.longitude = 0.0
    }

    fun setLatLong(lat:Double,long:Double){
        this.userLocation
        userLocation!!.latitude = lat
        userLocation!!.longitude = long
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