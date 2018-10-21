package lentborrow.cs3231.com.lentborrow.controller.activity

import android.content.Context

class ActivityMigrationController() : ActivityMigrationBuilder(){

    private constructor(context: Context,activityName: String):this(){
        super.set(context,activityName);
    }

    public fun setLoginActivity(context: Context): ActivityMigrationBuilder {
        return ActivityMigrationController(context,"Login");
    }

    public fun setRegistrationActivity(context: Context): ActivityMigrationBuilder {
        return ActivityMigrationController(context,"Register");
    }
}