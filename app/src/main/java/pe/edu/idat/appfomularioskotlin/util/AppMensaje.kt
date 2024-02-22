package pe.edu.idat.appfomularioskotlin.util

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import pe.edu.idat.appfomularioskotlin.R

object AppMensaje {

    fun enviarMensaje(vista: View, mensaje: String, tipo: TipoMensaje){
        val snackBar = Snackbar.make(vista, mensaje, Snackbar.LENGTH_LONG)
        when(tipo){
            TipoMensaje.ERROR-> snackBar.setBackgroundTint(ContextCompat.getColor(MiApp.instancia, R.color.error))
            TipoMensaje.CORRECTO-> snackBar.setBackgroundTint(ContextCompat.getColor(MiApp.instancia, R.color.correcto))
            TipoMensaje.ADVERTENCIA-> snackBar.setBackgroundTint(ContextCompat.getColor(MiApp.instancia, R.color.advertencia))
            TipoMensaje.INFORMACION-> snackBar.setBackgroundTint(ContextCompat.getColor(MiApp.instancia, R.color.info))
        }
        snackBar.show()
    }


}