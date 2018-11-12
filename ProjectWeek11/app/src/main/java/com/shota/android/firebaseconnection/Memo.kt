package com.shota.android.firebaseconnection

import android.support.v7.widget.DialogTitle

class Memo() {
    var title:String = "";
    var content:String = "";

    constructor(title: String, content:String):this(){
        this.title = title;
        this.content = content;
    }
}