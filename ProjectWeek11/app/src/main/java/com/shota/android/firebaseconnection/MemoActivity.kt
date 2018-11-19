package com.shota.android.firebaseconnection

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_memo.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase



class MemoActivity : AppCompatActivity() {

    var ref:String? = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)
        val title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        ref = intent.getStringExtra("Ref");

        if(title != null && !title.isEmpty()){
            title_memo.setText(title);
        }

        if(content != null && !content.isEmpty()){
            content_memo.setText(title);
        }

    }

    fun onClickSave(view: View){
        if(ref != null && !ref!!.isEmpty()){
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference(ref+title_memo.text.toString())

            myRef.setValue(content_memo.text.toString())
        }
    }
}
