package aditya.wibisana.easypermission

import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class ActionManageOverlayPermission(appCompatActivity: AppCompatActivity)
    : SimplePermission(appCompatActivity) {

    override fun reload(activity: AppCompatActivity) {
        _isPermissionGranted.value = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            true
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            Settings.canDrawOverlays(activity)
        } else {
            if (Settings.canDrawOverlays(activity)) true
            try {
                val mgr = activity.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                val viewToAdd = View(activity)
                val params = WindowManager.LayoutParams(
                    0,
                    0,
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                        WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
                    else
                        WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSPARENT
                )
                viewToAdd.layoutParams = params
                mgr.addView(viewToAdd, params)
                mgr.removeView(viewToAdd)
                true
            } catch (e: Exception) {
                e.printStackTrace()
            }
            false
        }
    }

    override fun request(activity: AppCompatActivity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return

        val uri = Uri.parse("package:${activity.packageName}")
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, uri)
        activity.startActivity(intent)
    }
}