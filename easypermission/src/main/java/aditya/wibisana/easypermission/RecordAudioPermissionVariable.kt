package aditya.wibisana.easypermission

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

class RecordAudioPermissionVariable : BasePermissionVariable() {
    fun setup(activity: AppCompatActivity) {
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

    fun request(activity: AppCompatActivity) {
        activity.requestPermission(Manifest.permission.RECORD_AUDIO)
    }
}