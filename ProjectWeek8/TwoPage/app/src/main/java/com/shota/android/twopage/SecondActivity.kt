package com.shota.android.twopage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.shota.android.twopage.R.id.text
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        var message = intent.getStringExtra("MESSAGE");
        textView.text = message;
    }
}
