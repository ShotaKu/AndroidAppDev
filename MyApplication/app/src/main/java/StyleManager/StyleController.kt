package StyleManager

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.Window
import java.security.AccessControlContext

public fun hideTitleBar(activity: Activity){
        activity.window.requestFeature(Window.FEATURE_NO_TITLE);
}