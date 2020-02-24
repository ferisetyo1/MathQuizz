package feri.com.mathquizz.modul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import feri.com.mathquizz.R
import kotlinx.android.synthetic.main.activity_materi_prasyarat.*

class MateriPrasyaratActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materi_prasyarat)
        supportActionBar?.hide()
    }

    fun back(view: View) {finish()}
    fun lihatKubus(view: View) {startActivity(Intent(this,KubusInteraktifActivity::class.java))}
}
