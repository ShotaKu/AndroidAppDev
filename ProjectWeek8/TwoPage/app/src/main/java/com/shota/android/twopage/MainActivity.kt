package com.shota.android.twopage


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.shota.android.twopage.R.id.editText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun buttonClicked(view: View){
        var editText = findViewById<EditText>(R.id.editText);
        var message = editText.text.toString();
        if(message != ""){
            var instent = Intent(this, SecondActivity::class.java).apply {
                putExtra("MESSAGE",message);
            }
            startActivity(instent);
        } else{
            Toast.makeText(this,"Fill text please!", Toast.LENGTH_LONG);
        }
    }
}
