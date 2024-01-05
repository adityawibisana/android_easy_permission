package aditya.wibisana.easypermission

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

fun AppCompatActivity.requestPermission(permissionManifest: String) {
    if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissionManifest)) {
        baseContext.packageName
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", baseContext.packageName, null)
        intent.data = uri
        startActivity(intent)
    } else {
        ActivityCompat.requestPermissions(
            this,
            listOf(permissionManifest).toTypedArray(),
            1)
    }
}