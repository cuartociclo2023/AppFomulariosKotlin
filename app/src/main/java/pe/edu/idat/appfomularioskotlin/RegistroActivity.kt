package pe.edu.idat.appfomularioskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import pe.edu.idat.appfomularioskotlin.databinding.ActivityRegistroBinding
import pe.edu.idat.appfomularioskotlin.util.AppMensaje
import pe.edu.idat.appfomularioskotlin.util.TipoMensaje


class RegistroActivity : AppCompatActivity(), View.OnClickListener, AdapterView.OnItemSelectedListener {

    private lateinit var  binding: ActivityRegistroBinding
    private var estadocivil = ""
    private val listaPersonas = ArrayList<String>()
    private val listaPreferencias = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ArrayAdapter.createFromResource(
            applicationContext, R.array.estado_civil,
            android.R.layout.simple_spinner_item
        ).also {
            adapter -> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spestadocivil.adapter = adapter
        }
        binding.btnregistrarpersona.setOnClickListener(this)
        binding.btnlistarpersonas.setOnClickListener(this)
        binding.spestadocivil.onItemSelectedListener = this
        binding.cbdeporte.setOnClickListener(this)
        binding.cbmusica.setOnClickListener(this)
        binding.cbotros.setOnClickListener(this)
    }
    fun validarEstadoCivil():Boolean{
        return estadocivil != ""
    }
    fun validarPreferencia(): Boolean{
        var respuesta = false
        if(binding.cbdeporte.isChecked || binding.cbmusica.isChecked || binding.cbotros.isChecked)
            respuesta = true
        return respuesta
    }
    fun validarGenero(): Boolean {
        var respuesta = true
        if(binding.rggenero.checkedRadioButtonId == -1)
            respuesta = false
        return respuesta
    }
    fun validarNombresApellidos():Boolean{
        var respuesta = true
        if(binding.etnombre.text.toString().trim().isEmpty()){
            binding.etnombre.isFocusableInTouchMode = true
            binding.etnombre.requestFocus()
            respuesta = false
        }else if(binding.etapellido.text.toString().trim().isEmpty()){
            binding.etapellido.isFocusableInTouchMode = true
            binding.etapellido.requestFocus()
            respuesta = false
        }
        return respuesta
    }

    fun validarFormulario():Boolean{
        var respuesta = false
        var mensaje = ""
        if(!validarNombresApellidos()){
            mensaje = getString(R.string.errornombreapellido)
        }else if(!validarGenero()){
            mensaje = getString(R.string.errorgenero)
        }else if(!validarPreferencia()){
            mensaje = getString(R.string.errorpreferencia)
        }else if(!validarEstadoCivil()){
            mensaje = getString(R.string.errorestadocivil)
        }else {
            respuesta = true
        }
        if(!respuesta) AppMensaje.enviarMensaje(binding.root, mensaje, TipoMensaje.ERROR)
        return respuesta
    }
    override fun onClick(v: View?) {
        if(v!! is CheckBox){
            agregarQuitarPreferencia(v!!)
        }else{
            when(v!!.id){
                R.id.btnlistarpersonas -> irListadoPersonas()
                R.id.btnregistrarpersona -> registrarPersona()
            }
        }
    }
    private fun agregarQuitarPreferencia(v: View) {
        val checkBox = v as CheckBox
        if(checkBox.isChecked)
            listaPreferencias.add(checkBox.text.toString())
        else
            listaPreferencias.remove(checkBox.text.toString())
    }

    private fun registrarPersona() {
        if(validarFormulario()){
            var infoPersona = binding.etnombre.text.toString()+" "+
                    binding.etapellido.text.toString()+" "+
                    obtenerGenero() + " " +
                    listaPreferencias.toArray()+" " +
                    estadocivil+" "+
                    binding.swnotificacion.isChecked
            listaPersonas.add(infoPersona)
            AppMensaje.enviarMensaje(binding.root, getString(R.string.mensajeregistro), TipoMensaje.CORRECTO)
        }

    }
    fun obtenerGenero():String{
        return if(binding.rggenero.checkedRadioButtonId == R.id.rbmasculino)
            binding.rbmasculino.text.toString()
        else binding.rbfemenino.text.toString()
    }
    private fun irListadoPersonas() {
        var intentListado = Intent(applicationContext,
            ListaActivity::class.java).apply {
                putExtra("listaPersonas", listaPersonas)
        }
        startActivity(intentListado)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        estadocivil = if(position > 0) parent!!.getItemAtPosition(position).toString() else ""
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}