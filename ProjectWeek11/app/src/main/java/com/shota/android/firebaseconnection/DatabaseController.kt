package com.shota.android.firebaseconnection

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DatabaseController() {
    val database = FirebaseDatabase.getInstance()
    var reference = ""

    init {
        val email = FirebaseAuth.getInstance().currentUser!!.email!!;
        if (!email.isEmpty()) {
            reference = email.replace(".", "(dot)")
        } else {
            throw IllegalArgumentException("Empty email")
        }
    }

    fun getMemo(title: String, successCallback: (memo: Memo) -> Unit, failedCallback: (error: DatabaseError) -> Unit) {
        val ref = database.getReference(reference + "/" + title);
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val memo = Memo(dataSnapshot)
                successCallback(memo);
            }

            override fun onCancelled(error: DatabaseError) {
                failedCallback(error);
            }
        });
    }

    fun getAllMemo(successCallback: (memoList: ArrayList<Memo>) -> Unit, failedCallback: (error: DatabaseError) -> Unit) {
        val ref = database.getReference(reference);
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val memoList = ArrayList<Memo>()
                for (data in dataSnapshot.children) {
                    val title = data.key.toString();
                    val content = data.value.toString()
                    val memo = Memo(title, content);
                    memoList.add(memo);
                }
                successCallback(memoList);
            }

            override fun onCancelled(error: DatabaseError) {
                failedCallback(error);
            }
        });
    }

    fun setMemo(oldTitle: String, title: String, content: String) {
        val myRef = database.getReference(reference + "/" + title)
        myRef.setValue(content)
        if (oldTitle != title && oldTitle != "") {
            val oldRef = database.getReference(reference + "/" + oldTitle)
            oldRef.setValue(null)
        }

    }
}