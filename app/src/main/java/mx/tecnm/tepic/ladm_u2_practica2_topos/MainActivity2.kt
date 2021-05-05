package mx.tecnm.tepic.ladm_u2_practica2_topos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lienzo = Lienzo(this)
        setContentView(lienzo)
    }
}