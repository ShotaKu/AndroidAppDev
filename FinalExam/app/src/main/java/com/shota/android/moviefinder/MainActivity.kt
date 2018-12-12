package com.shota.android.moviefinder

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import com.shota.android.moviefinder.controller.LocalValueController
import com.shota.android.moviefinder.movie.MovieAdapter
import com.shota.android.moviefinder.movie.Movie
import com.shota.android.moviefinder.movie.MovieIndex
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var index:MovieIndex? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //FuelManager.instance.basePath = ""
        "https://bit.ly/part3_movie".httpGet().responseString { request, response, result ->
            //reuslt have both JSON data and Error data
            val (data, error) = result
            //if no error
            if (error == null) {
                val gson = Gson()
                val json = data!!
                index = MovieIndex.fromJson(json)

                val rView = movies
                rView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

                //Making adapter with students inputs
                var adapter = MovieAdapter(index!!.getMovies());

                //Change list format as custom cells.
                rView.adapter = adapter;
            } else {
                Toast.makeText(this, "HTTP Error!!", Toast.LENGTH_LONG);
            }
        }
    }

    var isShowMovie = true
    fun onSwitch(view:View){
        isShowMovie = !isShowMovie
        if(isShowMovie){
            movies.visibility = View.VISIBLE
            fav.visibility = View.INVISIBLE
        }
        else{
            movies.visibility = View.INVISIBLE
            fav.visibility = View.VISIBLE
            val lvCon = LocalValueController(this)
            val favs = lvCon.getStringArray("fav")
            val favList = ArrayList<Movie>()
            for(fav in favs){
                val m = index!!.findByID(fav);
                if(m != null){
                    favList.add(m!!)
                }
            }
            val rView = fav
            rView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

            //Making adapter with students inputs
            var adapter = MovieAdapter(favList);

            //Change list format as custom cells.
            rView.adapter = adapter;
        }

    }
}
