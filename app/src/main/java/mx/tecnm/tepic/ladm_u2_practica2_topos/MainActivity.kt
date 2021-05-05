package mx.tecnm.tepic.ladm_u2_practica2_topos

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun goToAnotherActivity(view: View?) {
        startActivity(Intent(this, MainActivity2::class.java))
    }
}