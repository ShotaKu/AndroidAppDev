package com.shota.android.firebaseconnection

import android.support.v7.widget.DialogTitle
import com.google.firebase.database.DataSnapshot

class Memo() {
    var title:String = "";
    var content:String = "";

    constructor(title: String, content:String):this(){
        this.title = title;
        this.content = content;
    }

    constructor(dataSnapshot: DataSnapshot):this(){
        title = dataSnapshot.key.toString();
        content = dataSnapshot.value.toString()
    }
}