package audorm.cs3231.com.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Start : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        //leanBackMode()
    }

    fun leanBackMode(){
        val decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
    }
}
