package aditya.wibisana.permission

import aditya.wibisana.easypermission.MicPermission
import aditya.wibisana.permission.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private val micPermission: MicPermission by lazy {
        MicPermission(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        micPermission.isGrantedLiveData.observe(this) {
            binding.micPermission.isVisible = !it
        }
        binding.micPermission.setOnClickListener {
            micPermission.request(this)
        }
    }
}