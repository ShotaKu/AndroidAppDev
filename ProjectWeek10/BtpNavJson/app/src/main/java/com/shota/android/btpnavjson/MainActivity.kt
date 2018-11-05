package com.shota.android.btpnavjson

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.*
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.customcell.view.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iStream:InputStream = getAssets().open("btp-nav.json");
        val json:String = iStream.reader().readText();

        val gson = Gson()
        val datas = gson.fromJson(json,Array<Data>::class.java);
        val list:LinearLayout = list
        var lastMonth = 0;
        for(data:Data in datas){
            val dateTime = data.getDate();
            if(dateTime.get(Calendar.MONTH) != lastMonth){
                lastMonth = dateTime.get(Calendar.MONTH)
                var text = TextView(this);
                text.textSize = 18f;
                text.setPadding(0,5,0,5)
                if(0<=lastMonth){
                    text.setText(DateFormatSymbols().getMonths()[dateTime.get(Calendar.MONTH)]+" "+dateTime.get(Calendar.YEAR));
                }else{
                    break;
                }
                list.addView(text);
            }
            val cell = createCell(SimpleDateFormat("dd/MM/yyyy").format(dateTime.time),data.preTaxNav.toString(),data.preTaxBid.toString());
            list.addView(cell);
        }
    }
    fun createCell(date:String,data1:String,data2:String): View {
        val factory = LayoutInflater.from(this)
        val myView = factory.inflate(R.layout.customcell, null)
        myView.date.setText(date);
        myView.data1.setText(data1);
        myView.data2.setText(data2);
        return myView;
    }
    fun createText(str:String): TextView {
        var text = TextView(this);
        text.textSize = 18f;
        text.setPadding(0,5,0,5)
        text.setText(str)
        return text;
    }
    //data class  data(val date: Date, val preTaxNav:Double, val preTaxBid:Double, val fundSize:Double, val change:Double, val percentChange:Double)
}
