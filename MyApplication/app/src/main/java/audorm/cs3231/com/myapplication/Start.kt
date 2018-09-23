package audorm.cs3231.com.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import StyleManager.*
import android.graphics.Color
import kotlinx.android.synthetic.main.activity_start.*


class Start : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideTitleBar(this);
        setContentView(R.layout.activity_start)
        setActivityBackgroundColor()
    }

    fun setActivityBackgroundColor() {
        val view = this.window.decorView
        view.setBackgroundColor(resources.getColor(R.color.colorPrimary))
    }
}
