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
        val newView = ViewHolder(v)
        return newView
    }

    override fun onBindViewHolder(p0: StudentAdapter.ViewHolder, p1: Int) {
        p0.studentName.text = studentList[p1].name;
        p0.id.text = studentList[p1].id;
        p0.itemView.setOnClickListener {
            //Toast.makeText(this, studentList[p1].id+": "+studentList[p1].name, Toast.LENGTH_SHORT).show()
            //TODO create toast pop up when click card view
        }
    }


    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val studentName = v.title
        val id = v.subtitle
    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }
}