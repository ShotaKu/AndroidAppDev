package com.shota.android.moviefinder

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.shota.android.moviefinder.controller.ImageDownloader
import com.shota.android.moviefinder.controller.LocalValueController
import com.shota.android.moviefinder.movie.Movie
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    var movie:Movie? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
	    val title:String = intent.getStringExtra("title")
	    val id = intent.getStringExtra("id")
        val gen = intent.getStringExtra("gen")
        val date = intent.getStringExtra("date")
        val detail = intent.getStringExtra("detail")
        val image = intent.getStringExtra("image")
        movie = Movie(id,title,detail,gen,date,image)

        title_detail.text = movie!!.title_en
        date_detail.text = movie!!.release_date
        gen_detail.text = movie!!.genre
        detail_detail.text = movie!!.synopsis_en
        val iDow = ImageDownloader(movie!!.getImageURL(),image_detail)
        iDow.startDownload {  }
    }
    fun onClickFavorite(view: View){
        if(movie != null){
            val lvCon = LocalValueController(this)
            lvCon.addStringArray("fav", movie!!.id);
        }
    }
}
