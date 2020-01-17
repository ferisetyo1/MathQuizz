package feri.com.mathquizz.modul

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import feri.com.mathquizz.R
import feri.com.mathquizz.shared.SPManager
import kotlinx.android.synthetic.main.activity_ganti_nama.*

class GantiNamaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ganti_nama)
        supportActionBar?.hide()
        btn_simpan.setOnClickListener {
            SPManager(this).apply {
                saveSPString(KEY_NAMA_USER,input_nama.text.toString().trim())
            }
            setResult(Activity.RESULT_OK, Intent().putExtra("data",input_nama.text.toString().trim()))
            finish()
        }
        btn_back.setOnClickListener { finish() }
    }
}
