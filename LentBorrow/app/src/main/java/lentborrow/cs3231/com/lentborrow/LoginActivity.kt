package lentborrow.cs3231.com.lentborrow

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
    fun toRegistration(view: View){
        var int = Intent(this, MainActivity::class.java);
        startActivity(int);
    }
}