package lentborrow.cs3231.com.lentborrow.controller.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import lentborrow.cs3231.com.lentborrow.BookDetailActivity
import lentborrow.cs3231.com.lentborrow.LoginActivity
import lentborrow.cs3231.com.lentborrow.RegistrationActivity

open class ActivityMigrationBuilder{
    var intent: Intent? = null;
    var context: Context? = null;

    protected constructor();
    protected constructor(context: Context,activityName: String){
        set(context,activityName);
    }

    protected final val activityIndex = mutableMapOf(
            "Login" to LoginActivity::class.java
            ,"Register" to RegistrationActivity::class.java
            ,"BookDetail" to BookDetailActivity::class.java)

    protected fun set(context: Context,activityName: String){
        val act:Class<out AppCompatActivity>? = activityIndex.get(activityName);
        if (act != null) {
            intent = Intent(context,act!!);
            this.context = context;
        }else{
            intent = Intent();
        }
    }
    
    public fun pass(key:String, value:String): ActivityMigrationBuilder {
        if(intent != null)
            intent!!.putExtra(key,value);
        return this;
    }
    
    public fun go(){
        if(context != null && intent != null)
            context!!.startActivity(intent!!);
    }
}