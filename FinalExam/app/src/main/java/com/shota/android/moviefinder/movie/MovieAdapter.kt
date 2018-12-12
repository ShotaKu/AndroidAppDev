package com.shota.android.moviefinder.movie

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shota.android.moviefinder.DetailActivity
import com.shota.android.moviefinder.R
import com.shota.android.moviefinder.controller.ImageDownloader
import kotlinx.android.synthetic.main.customcell_movie.view.*

class MovieAdapter(val movieList: ArrayList<Movie>): RecyclerView.Adapter<MovieAdapter.ViewHolder>(){
    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.customcell_movie,parent,false)
/*        v.setOnClickListener{
            var message = v.subtitle.text +":"+ v.title.text
            Toast.makeText(it,message, Toast.LENGTH_LONG).show()
        }*/
        val newView = ViewHolder(v)
        return newView
    }

    override fun onBindViewHolder(viewHolder: MovieAdapter.ViewHolder, p1: Int) {
        var title = movieList[p1].title_en;
        var gen = movieList[p1].genre
        val date = movieList[p1].release_date
        viewHolder.title.text = title;
        viewHolder.gen.text = gen;
        viewHolder.date.text = date;
        val context = viewHolder.itemView.context
        val iDow = ImageDownloader(movieList[p1].getImageURL(),viewHolder.image)
        iDow.startDownload {  }

        viewHolder.itemView.setOnClickListener {
            //Toast.makeText(this, movieList[p1].id+": "+movieList[p1].name, Toast.LENGTH_SHORT).show()
            //TODO create toast pop up when click card view
            val intent = Intent(context,DetailActivity::class.java).apply {
                putExtra("title",title);
                putExtra("gen",gen);
                putExtra("date",date);
                putExtra("id",movieList[p1].id)
                putExtra("detail",movieList[p1].synopsis_en)
                putExtra("image",movieList[p1].poster_ori)
            }
            context.startActivity(intent)
            //intent.set
        }
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val title = v.title_movie
        val gen = v.gen_movie
        val date = v.date_movie
        val image = v.image_movie
    }
}