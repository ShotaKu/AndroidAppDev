package com.shota.android.firebaseconnection

import android.os.Bundle
import android.app.Activity
import com.google.firebase.auth.FirebaseAuth

import kotlinx.android.synthetic.main.activity_home.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*


class HomeActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

    }

}
