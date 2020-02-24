package feri.com.mathquizz.modul

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import feri.com.mathquizz.MainActivity
import feri.com.mathquizz.R
import feri.com.mathquizz.model.SoalModel
import feri.com.mathquizz.shared.SPManager
import kotlinx.android.synthetic.main.activity_result_prasyarat.*
import kotlinx.android.synthetic.main.dialog_setelah_belajar.*

class ResultPrasyarat : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_prasyarat)
        supportActionBar?.hide()


        val isLulus = intent.getBooleanExtra("isLulus", false)
        val listSoal = intent.getParcelableArrayListExtra<SoalModel>("listSoal")
        Log.d("listSoal",listSoal.toString())
        if (isLulus) {
            SPManager(this).apply {
                saveSPBoolean(KEY_LULUS_PRASYARAT, true)
            }
            btn_materi_bg_datar.setOnClickListener {
                startActivity(Intent(this, PetaKonsepActivity::class.java))
            }
        } else {
            btn_materi_bg_datar.apply {
                text = "ulangi tes"
                setOnClickListener {
                    startActivity(
                        Intent(
                            this@ResultPrasyarat,
                            PengerjaanPrasyaratActivity::class.java
                        ).putExtra("dataSoal", listSoal)
                    )
                }
            }
            text_result.text =
                "Kamu gagal, baca materi prasyarat terlebih dahulu dan ulangi tes lagi"
            img_result.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp)
        }
        btn_home.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        btn_materi_pra.setOnClickListener {
            startActivity(Intent(this,MateriPrasyaratActivity::class.java))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
