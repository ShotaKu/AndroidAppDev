package com.shota.studentlistapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.shota.studentlistapp.R.id.recyclerView
import kotlinx.android.synthetic.main.activity_list.*

class List : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val rView = recyclerView
        rView.layoutManager = LinearLayoutManager(this,LinearLayout.VERTICAL, false)
        val student = ArrayList<Student>()
        student.add(Student("5618455","Shopan Kuroda"))
        student.add(Student("5699999","Tanapol"))

        var adapter = StudentAdapter(student);
        rView.adapter = adapter;
    }
}
