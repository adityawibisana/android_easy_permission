package aditya.wibisana.easypermission

import android.content.ComponentName
import android.content.Context
import android.provider.Settings
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

class BindAccessibilityServicePermission(
    appCompatActivity: AppCompatActivity,
    accessibilityService: Class<*>?)
    : PermissionVariable()
{
    init {
        appCompatActivity.lifecycleScope.launch {
            appCompatActivity.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                reload(appCompatActivity, accessibilityService)
            }
        }
    }

    private fun reload(context: Context, accessibilityService: Class<*>?) {
        _isPermissionGranted.value = isAccessibilityServiceEnabled(context, accessibilityService)
    }

    private fun isAccessibilityServiceEnabled(context: Context, accessibilityService: Class<*>?): Boolean {
        val expectedComponentName = ComponentName(context, accessibilityService!!)
        val enabledServicesSetting =
            Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
            )
                ?: return false
        val colonSplitter = TextUtils.SimpleStringSplitter(':')
        colonSplitter.setString(enabledServicesSetting)
        while (colonSplitter.hasNext()) {
            val componentNameString = colonSplitter.next()
            val enabledService = ComponentName.unflattenFromString(componentNameString)
            if (enabledService != null && enabledService == expectedComponentName) return true
        }
        return false
    }
}