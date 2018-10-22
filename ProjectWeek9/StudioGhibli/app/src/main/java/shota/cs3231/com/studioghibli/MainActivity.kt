package shota.cs3231.com.studioghibli

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.gson.gsonDeserializerOf
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import shota.cs3231.com.studioghibli.customWidget.FilmAdapter
import shota.cs3231.com.studioghibli.ghibli.Film
import shota.cs3231.com.studioghibli.ghibli.FilmIndex

class MainActivity : AppCompatActivity() {

    var index:FilmIndex = FilmIndex();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FuelManager.instance.basePath = "https://ghibliapi.herokuapp.com/"
        "/films".httpGet().responseString { request, response, result ->
            //make a GET to https://httpbin.org/get and do something with response
            val (data, error) = result
            if (error == null) {
                //do something when success
                index = FilmIndex(data!!);
                val rView = filmList
                rView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

                var adapter = FilmAdapter(index.getArrayListIndex());
                rView.adapter = adapter;
            } else {
                //error handling
            }
        }
    }

    fun showMessage(message:String){
        Toast.makeText(this,message,Toast.LENGTH_LONG);
    }
}
