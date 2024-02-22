package pe.edu.idat.appfomularioskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import pe.edu.idat.appfomularioskotlin.databinding.ActivityListaBinding

class ListaActivity : AppCompatActivity() {

    lateinit var binding: ActivityListaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val listaPersonas = intent.getSerializableExtra("listaPersonas")
                as ArrayList<String>
        val adapter = ArrayAdapter(applicationContext,
            android.R.layout.simple_list_item_1,
            listaPersonas)
        binding.lvpersonas.adapter = adapter
    }
}