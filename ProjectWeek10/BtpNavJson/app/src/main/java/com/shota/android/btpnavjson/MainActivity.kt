package com.shota.android.btpnavjson

import android.content.res.AssetManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import com.google.gson.Gson
import com.shota.android.btpnavjson.R.layout.customcell
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iStream:InputStream = getAssets().open("btp-nav.json");
        val json:String = iStream.reader().readText();

        val gson = Gson()
        val datas = gson.fromJson(json,Array<Data>::class.java);
        val list = list
        var lastMonth = 0;
        for(data:Data in datas){
            if(data.getDate().month != lastMonth){

            }
            val cCell = View(customcell)
            cCell.date
        }
    }

    //data class  data(val date: Date, val preTaxNav:Double, val preTaxBid:Double, val fundSize:Double, val change:Double, val percentChange:Double)
}
