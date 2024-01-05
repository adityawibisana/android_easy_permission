package aditya.wibisana.easypermission

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class RecordAudioPermission : BasePermission() {
    override fun reload(activity: AppCompatActivity) {
        _isPermissionGranted.value =
            ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED
    }

    fun request(activity: AppCompatActivity) {
        activity.requestPermission(Manifest.permission.RECORD_AUDIO)
    }
}