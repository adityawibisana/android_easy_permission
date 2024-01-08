package aditya.wibisana.easypermission

import android.Manifest
import androidx.appcompat.app.AppCompatActivity

class RecordAudioPermission(appCompatActivity: AppCompatActivity)
    : BasePermission(appCompatActivity)
{
    override val permissionManifest: String get() = Manifest.permission.RECORD_AUDIO
}