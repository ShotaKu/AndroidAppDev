package com.shota.android.moviefinder.controller

import android.content.Context
import android.content.SharedPreferences

class LocalValueController(context: Context) {
    val localStrageID = "LENTBORROW_STORAGE"
    var local: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    init {
        this.local = context.getSharedPreferences(localStrageID, 0)
        this.editor = local!!.edit()
    }

    public fun getString(key: String): String {
        return local!!.getString(key, "")
    }

    public fun setString(key: String, value: String) {
        editor!!.putString(key, value)
        editor!!.commit()
    }

    public fun getStringArray(key: String):ArrayList<String> {
        val result:ArrayList<String> = arrayListOf()
        var i = 0;
        while (true) {
            val v = getString(key+i)
            if(!v.isEmpty()){
                result.add(v);
            }else
                break
            i++;
        }
        return  result
    }

    public fun addStringArray(key:String,value:String){
        val index = getStringArray(key)
        if(!index.isEmpty()){
            for (v in index){
                if(v == value)
                    return
            }
        }
        setString(key+index.size.toString(),value);
    }
}