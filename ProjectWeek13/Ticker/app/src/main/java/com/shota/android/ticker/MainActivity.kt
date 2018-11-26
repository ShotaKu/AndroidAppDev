package com.shota.android.ticker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.shota.android.ticker.controller.Ticker
import com.shota.android.ticker.customCell.TickerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firebase = FirebaseApp.initializeApp(this)
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("tickers")

        val rView = tickerList
        rView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        ref.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val list:ArrayList<Ticker> = arrayListOf()
                for(ticker in snapshot.children){
                    val last = ticker.child("last").value.toString()
                    val cPair = ticker.child("currencyPair").value.toString()
                    val bVolume = ticker.child("baseVolume").value.toString()
                    val pChange = ticker.child("percentChange").value.toString()
                    list.add(Ticker(last,cPair,bVolume,pChange))
                }

                rView.adapter = TickerAdapter(list);
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }
}
