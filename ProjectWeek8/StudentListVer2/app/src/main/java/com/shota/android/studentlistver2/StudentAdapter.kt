package com.shota.android.studentlistver2

import android.content.Intent
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.customcell.view.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class StudentAdapter(val studentList: ArrayList<Student>): RecyclerView.Adapter<StudentAdapter.ViewHolder>(){
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
        var student = studentList[p1];
        var name = student.name;
        var id = student.id
        viewHolder.studentName.text = name;
        viewHolder.id.text = id;
        viewHolder.itemView.setOnClickListener {

            var intent = Intent(viewHolder.itemView.context, StudentDetailActivity::class.java).apply {
                putExtra("NAME",student.name);
                putExtra("ID",student.id);
            }
            viewHolder.itemView.context.startActivity(intent);
        }
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val studentName = v.findViewById<TextView>(R.id.title);
        val id =v.findViewById<TextView>(R.id.subtitle);
    }
}