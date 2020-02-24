package feri.com.mathquizz.modul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import feri.com.mathquizz.R

class PetaKonsepActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_peta_konsep)
        supportActionBar?.hide()
    }

    fun back(view: View) {
        finish()
    }
    fun next(view: View) {
        startActivity(Intent(this, MateriActivity::class.java))
    }
}
