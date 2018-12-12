package com.shota.android.moviefinder.movie

import com.google.gson.Gson
import com.google.gson.JsonObject



class MovieIndex(){
    var movies:Array<Movie> = arrayOf()

    constructor(movies:Array<Movie>):this(){
       this.movies = movies
    }

    fun getMovies():ArrayList<Movie>{
        return movies.toCollection(ArrayList());
    }

    fun findByID(id:String):Movie?{
        for(movie in movies){
            if(movie.id == id)
                return movie
        }
        return null;
    }

    companion object {
        fun fromJson(json:String):MovieIndex{
            val gson = Gson()

            val jsonObject = gson.fromJson(json, JsonObject::class.java)
            val index = gson.fromJson(json,MovieIndex::class.java)
            return index
        }
    }
}