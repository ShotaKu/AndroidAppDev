package com.example.admin.projectweek1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyName.text = "Shopan";
        MyName.textSize = 32f;
        answer.textSize = 32f;
        answer.text = "Add number";
        var adr = 0
        var adnd = 0
        calculate.setOnClickListener()
        {
            adr = adder.text.toString().toInt()
            adnd = addend.text.toString().toInt()
            answer.text = (adr + adnd).toString();
        }
    }
}
