package com.shota.android.firebaseconnection

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import android.view.View
import kotlinx.android.synthetic.main.customcell.view.*

class MemoListAdapter(val studentList: ArrayList<Memo>): RecyclerView.Adapter<MemoListAdapter.ViewHolder>(){
    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.customcell,parent,false)
/*        v.setOnClickListener{
            var message = v.subtitle.text +":"+ v.title.text
            Toast.makeText(it,message, Toast.LENGTH_LONG).show()
        }*/
        val newView = ViewHolder(v)
        return newView
    }

    override fun onBindViewHolder(viewHolder: MemoListAdapter.ViewHolder, p1: Int) {
        var name = studentList[p1].title;
        var con = studentList[p1].content
        viewHolder.title.text = name;
        if(20<con.length){
            con = con.substring(0,19) + "...";
        }
        viewHolder.content.text = con;
        viewHolder.itemView.setOnClickListener {
            //Toast.makeText(this, studentList[p1].id+": "+studentList[p1].name, Toast.LENGTH_SHORT).show()
            //TODO create toast pop up when click card view
            val intent = Intent(viewHolder.itemView.context, MemoActivity::class.java);
            //Toast.makeText(it.context,con+":"+name, Toast.LENGTH_LONG).show()
        }
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val title = v.title
        val content = v.content
    }
}