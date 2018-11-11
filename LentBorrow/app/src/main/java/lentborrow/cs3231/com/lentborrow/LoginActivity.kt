package lentborrow.cs3231.com.lentborrow

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import lentborrow.cs3231.com.lentborrow.controller.activity.ActivityMigrationController
import lentborrow.cs3231.com.lentborrow.controller.auth.LoginController
import lentborrow.cs3231.com.lentborrow.controller.localValue.LocalValueController

class LoginActivity : AppCompatActivity() {
    var fbAuth = FirebaseAuth.getInstance()

    //Awake
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    //Start
    override fun onResume() {
        super.onResume()

        val lCon = LocalValueController(this);
        var email = lCon.getString("email");
        var pass = lCon.getString("password")

        if(email.isEmpty() || pass.isEmpty()){
            email = intent.getStringExtra("email");
            pass = intent.getStringExtra("password")
        }
        email_login.setText(email, TextView.BufferType.EDITABLE);
        password_login.setText(pass, TextView.BufferType.EDITABLE);
    }

    //Login button function
    fun Login(view: View) {
        val name = email_login.text.toString();
        val pass = password_login.text.toString();
        login(view, name, pass);
    }

    //go to registration activity
    fun toRegistration(view: View) {
        val mail = email_login.text.toString();
        val pass = password_login.text.toString();
        val amCon = ActivityMigrationController();
        amCon.setRegistrationActivity(this)
                .pass("email",mail)
                .pass("password", pass)
                .go()
    }

    //
    fun login(view: View, email: String, password: String) {
        showMessage("Authenticating...")

        val lCon = LoginController();

        lCon.Login(this, email, password,
                fun (email:String,pass:String){
                    showMessage("Hi "+email);
                    val lvCon = LocalValueController(this);
                    lvCon.setString("email",email);
                    lvCon.setString("password",pass);
                    val amCon = ActivityMigrationController();
//                    amCon.setRequestBoxActivity(this)
//                            .pass("email",email)
//                            .go()
                    amCon.setSearchActivity(this);
                }
            , fun (task: Task<AuthResult>){
                    showMessage("Error: ${task.exception?.message}")
                }
        );
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
