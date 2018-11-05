package lentborrow.cs3231.com.lentborrow

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import lentborrow.cs3231.com.lentborrow.controller.activity.ActivityMigrationController
import lentborrow.cs3231.com.lentborrow.controller.database.DatabaseController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dCon = DatabaseController();
        val obj = test("Test post",1)
        dCon.setObject("Test",obj);
    }

    fun requestBox(view: View) {
        var intent = Intent(this, RequestBoxActivity::class.java)
        startActivity(intent);
    }
    fun register(view: View){
        val amController = ActivityMigrationController();
        amController.setRegistrationActivity(this).go();
    }
    data class test(val message: String, val num: Int)
}
