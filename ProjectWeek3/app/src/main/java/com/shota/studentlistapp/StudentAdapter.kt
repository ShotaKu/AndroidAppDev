package com.shota.studentlistapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.customcell.view.*
import android.widget.Toast



class StudentAdapter(val studentList: ArrayList<Student>):RecyclerView.Adapter<StudentAdapter.ViewHolder>(){
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

    override fun onBindViewHolder(viewHolder: StudentAdapter.ViewHolder, p1: Int) {
        var name = studentList[p1].name;
        var id = studentList[p1].id
        viewHolder.studentName.text = name;
        viewHolder.id.text = id;
        viewHolder.itemView.setOnClickListener {
            //Toast.makeText(this, studentList[p1].id+": "+studentList[p1].name, Toast.LENGTH_SHORT).show()
            //TODO create toast pop up when click card view
            Toast.makeText(it.context,id+":"+name, Toast.LENGTH_LONG).show()
        }
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val studentName = v.title
        val id = v.subtitle
    }
}