package aditya.wibisana.permission

import aditya.wibisana.easypermission.MicPermission
import aditya.wibisana.permission.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    lateinit var micPermission: MicPermission

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        micPermission = MicPermission(this)
        micPermission.isPermissionGranted.observe(this) {
            binding.micPermission.isVisible = !it
        }
        binding.micPermission.setOnClickListener {
            micPermission.request(this)
        }
    }
}