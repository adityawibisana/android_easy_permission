package aditya.wibisana.easypermission

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class BasePermission {
    protected val _isPermissionGranted = MutableStateFlow(false)
    val isPermissionGranted = _isPermissionGranted.asStateFlow()
    val isPermissionGrantedLiveData = _isPermissionGranted.asLiveData()
    open val permissionManifest : String = ""

    fun setup(activity: AppCompatActivity) {
        activity.lifecycleScope.launch {
            activity.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                reload(activity)
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