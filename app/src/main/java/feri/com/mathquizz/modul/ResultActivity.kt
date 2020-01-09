package feri.com.mathquizz.modul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import feri.com.mathquizz.model.JawabanModel
import feri.com.mathquizz.MainActivity
import feri.com.mathquizz.R
import feri.com.mathquizz.model.SoalModel
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        supportActionBar?.hide()

        val listsoal = intent.getParcelableArrayListExtra<SoalModel>("dataSoal")
        val jawaban = intent.getParcelableArrayListExtra<JawabanModel>("dataJawaban")

        var score = 0
        (0..listsoal.size - 1).map {
            if (jawaban.get(it).jawaban == listsoal.get(it).jawaban_benar) score++
        }
        jml_benar.text = score.toString()
        total_soal.text = listsoal.size.toString()

        btn_home.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}
