package com.shota.android.studentlistver2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rView = recyclerView
        rView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val student = ArrayList<Student>()
        student.add(Student("5618455","Shopan Kuroda"))
        student.add(Student("5699999","Tanapol"))

        var adapter = StudentAdapter(student);
        rView.adapter = adapter;
    }
}
