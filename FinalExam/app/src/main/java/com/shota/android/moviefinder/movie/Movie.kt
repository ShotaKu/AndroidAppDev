package com.shota.android.moviefinder.movie

class Movie(){
    val baseURL = "http://www.majorcineplex.com"
    var id = ""
    var title_en = ""
    var synopsis_en = ""
    var genre = ""
    var release_date = ""
    var poster_ori = ""

    constructor(id:String,title_en:String,synopsis_en:String,genre:String,release_date:String,poster_ori:String):this(){
        this.id = id
        this.title_en = title_en
        this.synopsis_en = synopsis_en
        this.genre = genre
        this.release_date = release_date
        this.poster_ori = poster_ori
    }

    fun getImageURL():String{
        return baseURL + poster_ori
    }
}