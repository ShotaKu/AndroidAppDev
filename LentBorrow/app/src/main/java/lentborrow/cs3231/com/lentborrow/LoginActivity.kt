package lentborrow.cs3231.com.lentborrow

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import lentborrow.cs3231.com.lentborrow.controller.activity.ActivityMigrationController
import lentborrow.cs3231.com.lentborrow.controller.auth.LoginController

class LoginActivity : AppCompatActivity() {
    var fbAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onResume() {
        super.onResume()
        val name = intent.getStringExtra("userName");
        val pass = intent.getStringExtra("password")
        userName_login.setText(name, TextView.BufferType.EDITABLE);
        password_login.setText(pass, TextView.BufferType.EDITABLE);
    }

    fun Login(view: View) {
        val name = userName_login.text.toString();
        val pass = password_login.text.toString();
        signIn(view, name, pass);
    }

    fun toRegistration(view: View) {
        val name = userName_login.text.toString();
        val pass = password_login.text.toString();
        val amCon = ActivityMigrationController();
        amCon.setRegistrationActivity(this)
                .pass("userName", name)
                .pass("password", pass)
                .go()
    }

    fun signIn(view: View, email: String, password: String) {
        showMessage("Authenticating...")
        LoginController().Login(this, email, password,
            (fun (email:String,pass:String){
                showMessage("Hi "+email);
            }), (fun (task: Task<AuthResult>){
                showMessage("Error: ${task.exception?.message}")
            })
        );


    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
