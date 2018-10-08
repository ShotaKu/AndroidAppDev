package com.shota.android.twopage


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun buttonClicked(view: View){
        var editText = findViewById<EditText>(R.id.editText);
        var message = editText.text.toString();
        if(message != ""){
            var intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("MESSAGE",message);
            }
            startActivity(intent);
        } else{
            Toast.makeText(this,"Fill text please!", Toast.LENGTH_LONG);
        }
    }
}
