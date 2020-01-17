package feri.com.mathquizz.modul

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.google.firebase.firestore.FirebaseFirestore
import feri.com.mathquizz.model.JawabanModel
import feri.com.mathquizz.MainActivity
import feri.com.mathquizz.R
import feri.com.mathquizz.model.ResultModel
import feri.com.mathquizz.model.SoalModel
import feri.com.mathquizz.shared.SPManager
import feri.com.mathquizz.util.Helpers
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    val resultReff = db.collection("result")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        supportActionBar?.hide()

        val listsoal = intent.getParcelableArrayListExtra<SoalModel>("dataSoal")
        val jawaban = intent.getParcelableArrayListExtra<JawabanModel>("dataJawaban")
        val time = intent.getLongExtra("time", 0);
        val tipe = intent.getIntExtra("tipe", -1);
        if (time == 0L) {
            waktu.visibility = View.GONE
        }
        var inc_score = 0
        (0..listsoal.size - 1).map {
            if (jawaban.get(it).jawaban == listsoal.get(it).jawaban_benar) inc_score++
        }
        var total_score = (inc_score * 100.0) / listsoal.size
        var win = total_score > 70
        if (win == false) {
            img_result.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp)
            text_result.text = "Coba lagi, Selanjutnya kamu pasti bisa"
        }
        waktu.text = Helpers.longtoDate(time)
        score.text = total_score.toInt().toString()

        val builderdialog = AlertDialog.Builder(this)
        builderdialog.setCancelable(false)
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        builderdialog.setView(inflater.inflate(R.layout.progress, null))
        val dialog = builderdialog.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        val id = Helpers.generateKey("", "", 20)
        val sp = SPManager(this)
        resultReff.document(id).set(
            ResultModel(
                id,
                sp.getIDUser(),
                tipe,
                time,
                jawaban
            )
        ).addOnCompleteListener {
            dialog.dismiss()
        }

        btn_home.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
