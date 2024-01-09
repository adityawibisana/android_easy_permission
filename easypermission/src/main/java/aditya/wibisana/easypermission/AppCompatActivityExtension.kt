package aditya.wibisana.easypermission

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

fun AppCompatActivity.requestPermission(permission: String) {
    if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
        baseContext.packageName
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", baseContext.packageName, null)
        intent.data = uri
        startActivity(intent)
    } else {
        ActivityCompat.requestPermissions(
            this,
            listOf(permission).toTypedArray(),
            1)
    }
}

fun AppCompatActivity.isPermissionGranted(permission: String) : StateFlow<Boolean> {
    val granted = MutableStateFlow(false)
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.RESUMED) {
            granted.value = ContextCompat.checkSelfPermission(this@isPermissionGranted, permission) == PackageManager.PERMISSION_GRANTED
        }
    }
    return granted
}

fun AppCompatActivity.isPermissionGrantedLiveData(permission: String) : LiveData<Boolean> {
    return isPermissionGranted(permission).asLiveData()
}