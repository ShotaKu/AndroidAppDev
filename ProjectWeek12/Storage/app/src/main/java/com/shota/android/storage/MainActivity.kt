package com.shota.android.storage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.shota.android.storage.localValue.LocalValueController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lvCon:LocalValueController? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lvCon = LocalValueController(this);
        Log.d("Debug.message","app created!")
    }

    override fun onStart(){
        super.onStart()
        Log.d("Debug.message","app started!")
    }

    override fun onResume() {
        super.onResume()
        loadTask()
        Log.d("Debug.message","app resumed!")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Debug.message","app paused!")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Debug.message","app stopped!")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Debug.message","app destroyed!")
    }

    fun OnClickSave(view: View){
        val sText = SaveText.text.toString()
        if(sText != null && !sText.isEmpty())
            lvCon!!.setString("save",sText)
    }

    fun OnClickLoad(view: View){
        loadTask()
    }

    fun loadTask(){
        val txt = lvCon!!.getString("save");
        if(txt != null){
            LoadText.setText(txt);
        }else{
            LoadText.setText("No value to load!!")
        }
    }
}
