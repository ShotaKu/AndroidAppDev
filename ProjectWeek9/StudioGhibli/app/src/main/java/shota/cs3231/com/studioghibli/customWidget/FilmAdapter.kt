package shota.cs3231.com.studioghibli.customWidget

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.customcell.view.*
import android.widget.Toast
import shota.cs3231.com.studioghibli.R
import shota.cs3231.com.studioghibli.ghibli.Film


class FilmAdapter(val filmList: ArrayList<Film>): RecyclerView.Adapter<FilmAdapter.ViewHolder>(){
    override fun getItemCount(): Int {
        return filmList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.customcell,parent,false)
        val newView = ViewHolder(v)
        return newView
    }

    override fun onBindViewHolder(viewHolder: FilmAdapter.ViewHolder, p1: Int) {
        var name = this.filmList[p1].title;
        var id = this.filmList[p1].id
        viewHolder.filmName.text = name;
        viewHolder.itemView.setOnClickListener {
            //Toast.makeText(this, filmList[p1].id+": "+filmList[p1].name, Toast.LENGTH_SHORT).show()
            //TODO create toast pop up when click card view
            Toast.makeText(it.context,id+":"+name, Toast.LENGTH_LONG).show()
        }
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val filmName = v.title;
    }
}