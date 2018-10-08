package com.shota.android.studentlistver2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_student_detail.*

class StudentDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)

        var sName = intent.getStringExtra("NAME");
        var sID = intent.getStringExtra("ID");
        name.text = sName;
        ID.text = sID;
    }
    fun OnClickBackButton(view: View){
        var intent = Intent(this,MainActivity::class.java);
        startActivity(intent)
    }
}
