package aditya.wibisana.permission

import aditya.wibisana.easypermission.isPermissionGrantedLiveData
import aditya.wibisana.easypermission.requestPermission
import aditya.wibisana.permission.databinding.ActivityMainBinding
import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        isPermissionGrantedLiveData(Manifest.permission.RECORD_AUDIO).observe(this) {
            binding.micPermission.isVisible = !it
        }
        binding.micPermission.setOnClickListener {
            requestPermission(Manifest.permission.RECORD_AUDIO)
        }
    }
}