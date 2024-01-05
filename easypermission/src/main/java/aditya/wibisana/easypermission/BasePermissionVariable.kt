package aditya.wibisana.easypermission

import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class BasePermissionVariable {
    protected val _isPermissionGranted = MutableStateFlow(false)
    val isPermissionGranted = _isPermissionGranted.asStateFlow()
    val isPermissionGrantedLiveData = _isPermissionGranted.asLiveData()
}