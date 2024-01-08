package aditya.wibisana.easypermission

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

sealed class SimplePermission (
    appCompatActivity: AppCompatActivity
) : PermissionVariable()
{
    open val permissionManifest : String = ""

    init {
        appCompatActivity.lifecycleScope.launch {
            appCompatActivity.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                reload(appCompatActivity)
            }
        }
    }

    open fun reload(activity: AppCompatActivity) {
        _isPermissionGranted.value =
            ContextCompat.checkSelfPermission(activity, permissionManifest) == PackageManager.PERMISSION_GRANTED
    }

    open fun request(activity: AppCompatActivity) {
        activity.requestPermission(permissionManifest)
    }
}