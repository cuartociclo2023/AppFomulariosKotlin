package pe.edu.idat.appfomularioskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import pe.edu.idat.appfomularioskotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnirregistro.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        startActivity(Intent(applicationContext,
            RegistroActivity::class.java))
    }
}