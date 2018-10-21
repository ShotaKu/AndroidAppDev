package lentborrow.cs3231.com.lentborrow

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_registration.*
import lentborrow.cs3231.com.lentborrow.controller.activity.ActivityMigrationController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class RegistrationActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        //Check last login
        mAuth = FirebaseAuth.getInstance();
        val currentUser = mAuth!!.getCurrentUser();
        if(currentUser != null){

        }
        else{
            val name = userNameRegistration.text.toString();
            val pass = passwordRegistration.text.toString();
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.

    }
    fun toLogin(view: View){
        val name = userNameRegistration.text.toString();
        val pass = passwordRegistration.text.toString();
        val amController = ActivityMigrationController();
        amController.setLoginActivity(this)
                .pass("userName",name)
                .pass("password",pass)
                .go();
    }

}
