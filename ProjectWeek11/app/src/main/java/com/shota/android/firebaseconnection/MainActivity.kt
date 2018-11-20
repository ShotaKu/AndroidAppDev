package com.shota.android.firebaseconnection

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val dCon = DatabaseController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setTitle("Memo")
    }

    override fun onResume() {
        super.onResume()
        val list = rView_main
        list.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        val context = this.baseContext;

        dCon.getAllMemo({ memoList ->
            list.adapter = MemoListAdapter(memoList);
        },{ error ->
            Toast.makeText(context, error.message.toString(),Toast.LENGTH_LONG);
        })
    }

    fun onClickAddNewMemo(view:View){
        val intent = Intent(this,MemoActivity::class.java);
        intent.apply {
            putExtra("title","");
            putExtra("content","");
        }
        startActivity(intent);
    }
}
