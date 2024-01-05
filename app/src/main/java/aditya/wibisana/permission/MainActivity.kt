package aditya.wibisana.permission

import aditya.wibisana.easypermission.RecordAudioPermission
import aditya.wibisana.permission.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private val recordAudioPermission = RecordAudioPermission()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recordAudioPermission.setup(this)
        recordAudioPermission.isGrantedLiveData.observe(this) {
            binding.micPermission.isVisible = !it
        }
        binding.micPermission.setOnClickListener {
            recordAudioPermission.request(this)
        }
    }
}