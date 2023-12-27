package aditya.wibisana.easypermission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MicPermission(
    activity: AppCompatActivity
) {
    private val _isPermissionGranted = MutableStateFlow(false)
    val isPermissionGranted = _isPermissionGranted.asLiveData()

    init {
        activity.lifecycleScope.launch {
            activity.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                reload(activity)
            }
        }
    }

    private fun reload(context: Context) {
        _isPermissionGranted.value =
            ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
    }

}