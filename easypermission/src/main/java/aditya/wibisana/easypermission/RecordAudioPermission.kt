package aditya.wibisana.easypermission

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RecordAudioPermission {
    private val _isGranted = MutableStateFlow(false)
    val isGrantedLiveData = _isGranted.asLiveData()
    val isGranted = _isGranted.asStateFlow()

    fun setup(activity: AppCompatActivity) {
        activity.lifecycleScope.launch {
            activity.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                reload(activity)
            }
        }
    }

    private fun reload(context: Context) {
        _isGranted.value =
            ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
    }

    fun request(activity: AppCompatActivity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECORD_AUDIO)) {
            ActivityCompat.requestPermissions(
                activity,
                listOf(Manifest.permission.RECORD_AUDIO).toTypedArray(),
                1)
        } else {
            activity.baseContext.packageName
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", activity.baseContext.packageName, null)
            intent.data = uri
            activity.startActivity(intent)
        }
    }
}