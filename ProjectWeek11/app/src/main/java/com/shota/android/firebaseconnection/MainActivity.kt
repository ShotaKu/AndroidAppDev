package com.shota.android.firebaseconnection

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val mAuth = FirebaseAuth.getInstance();
    val user = mAuth.currentUser!!;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = FirebaseDatabase.getInstance()
        if(user != null){
            var email:String = user.email!!
            email = email.replace(".","(dot)")
            val myRef = database.getReference(email)
            val list = rView_main
            list.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
            val context = this.baseContext;

            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val memoList:ArrayList<Memo> = ArrayList()
                    for(data in dataSnapshot.children){
                        val title = data.key.toString();
                        val content = data.value.toString()
                        val memo = Memo(title, content);
                        memoList.add(memo);
                    }
                    list.adapter = MemoListAdapter(memoList);
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, error.message.toString(),Toast.LENGTH_LONG);
                }
            })
        }
    }
}
