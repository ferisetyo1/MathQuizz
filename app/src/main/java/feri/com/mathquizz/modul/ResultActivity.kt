package feri.com.mathquizz.modul

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import feri.com.mathquizz.model.JawabanModel
import feri.com.mathquizz.MainActivity
import feri.com.mathquizz.R
import feri.com.mathquizz.db.dbHelper
import feri.com.mathquizz.db.dbJawaban
import feri.com.mathquizz.db.dbResult
import feri.com.mathquizz.model.ResultModel
import feri.com.mathquizz.model.SoalModel
import feri.com.mathquizz.shared.SPManager
import feri.com.mathquizz.util.Helpers
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.dialog_setelah_belajar.*

class ResultActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    val resultReff = db.collection("result")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        supportActionBar?.hide()

        val listsoal = intent.getParcelableArrayListExtra<SoalModel>("dataSoal")
        val jawaban = intent.getParcelableArrayListExtra<JawabanModel>("dataJawaban")
        val tipe = intent.getIntExtra("tipe", -1);
        if (tipe == 1) {
            waktu.visibility = View.GONE
        }else{
            ShowDialogDoa()
        }
        var inc_score = 0
        var time = 0L;
        (0..listsoal.size - 1).map {
            time += jawaban.get(it).timestamp!!
            if (jawaban.get(it).jawaban == listsoal.get(it).jawaban_benar) inc_score++
        }
        var total_score = (inc_score * 100.0) / listsoal.size
        var win = total_score > 60
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
        val id = Helpers.generateKey("", "", 20)
        val sp = SPManager(this)
        val date = Timestamp.now()
        var resData = ResultModel(
            id,
            date,
            sp.getIDUser(),
            tipe,
            time,
            jawaban
        )
        dialog.show()
        if (Helpers.isInternetAvailable(this)) {
            println("test online")
            resultReff.document(id).set(
                resData
            ).addOnCompleteListener {
                dialog.dismiss()
            }
        } else {
            println("test offline")
            with(dbResult(this)) {
                open()
                val resultRes = addData(resData)
                if (resultRes > 0) {
                    with(dbJawaban(this@ResultActivity)) {
                        open()
                        resData.jawabanModels.forEach {
                            Log.d("test", it.toString())
                            val resultJaw = addData(resData.id!!, it)
                            if (resultJaw > 0) {
                                dialog.dismiss()
                            }
                        }
                        close()
                    }
                }
                close()
            }
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

    private fun ShowDialogDoa() {
        val dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_setelah_belajar)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.close.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}
