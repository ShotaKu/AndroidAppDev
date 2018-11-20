package com.shota.android.firebaseconnection

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_memo.*


class MemoActivity : AppCompatActivity() {

    var ref: String? = "";
    var title = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)
        title = intent.getStringExtra("title")
        val content = intent.getStringExtra("content")
        ref = intent.getStringExtra("Ref");

        if (title != null && !title.isEmpty()) {
            title_memo.setText(title);
        }

        if (content != null && !content.isEmpty()) {
            content_memo.setText(content);
        }
    }

    fun onClickSave(view: View) {
        val dCon = DatabaseController()
        dCon.setMemo(title,title_memo.text.toString(),content_memo.text.toString())
    }
}
