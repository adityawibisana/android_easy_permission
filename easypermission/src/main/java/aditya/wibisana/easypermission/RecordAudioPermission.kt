package aditya.wibisana.easypermission

import android.Manifest

class RecordAudioPermission : BasePermission() {
    override val permissionManifest: String
        get() = Manifest.permission.RECORD_AUDIO
}