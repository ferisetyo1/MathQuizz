package feri.com.mathquizz.modul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import feri.com.mathquizz.R
import feri.com.mathquizz.model.JawabanModel
import feri.com.mathquizz.model.SoalModel
import feri.com.mathquizz.util.Helpers
import kotlinx.android.synthetic.main.activity_pengerjaan_latihan.*
import kotlinx.android.synthetic.main.activity_pengerjaan_latihan.btn_next
import kotlinx.android.synthetic.main.activity_pengerjaan_latihan.jawaban_a
import kotlinx.android.synthetic.main.activity_pengerjaan_latihan.jawaban_b
import kotlinx.android.synthetic.main.activity_pengerjaan_latihan.jawaban_c
import kotlinx.android.synthetic.main.activity_pengerjaan_latihan.jawaban_d
import kotlinx.android.synthetic.main.activity_pengerjaan_latihan.jawaban_e
import kotlinx.android.synthetic.main.activity_pengerjaan_latihan.jawaban_group

class PengerjaanLatihanActivity : AppCompatActivity() {

    var position = 0
    val listsoal = ArrayList<SoalModel>()
    val jawaban = ArrayList<JawabanModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengerjaan_latihan)
        supportActionBar?.hide()

        val dataSoal = intent.getParcelableArrayListExtra<SoalModel>("dataSoal")
        listsoal.addAll(dataSoal)
        Log.d("Latihan list soal", listsoal.toString())

        updateSoal()

        btn_cek.setOnClickListener {
            chronometer.stop()
            if (jawaban_group.checkedRadioButtonId == -1) {
                Toast.makeText(this, "pilih jawaban terlebih dahulu", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            lyt_pembahasan.visibility = View.VISIBLE
            status_jawaban?.text = when (jawaban_group.checkedRadioButtonId) {
                R.id.jawaban_a -> cekJawaban(1)
                R.id.jawaban_b -> cekJawaban(2)
                R.id.jawaban_c -> cekJawaban(3)
                R.id.jawaban_d -> cekJawaban(4)
                else -> cekJawaban(5)
            }
        }

        btn_next.setOnClickListener {
            position++

            if (btn_next.text.equals("finish")) {
                startActivity(
                    Intent(this, ResultActivity::class.java)
                        .putExtra("dataSoal", listsoal)
                        .putExtra("dataJawaban", jawaban)
                        .putExtra("tipe", 1)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                )
            }

            if (position == listsoal.size - 1) {
                btn_next.text = "finish"
            }

            if (position == listsoal.size) {
                Log.d("position error", "$position")
                position--
                Log.d("position fixed", "$position")
                return@setOnClickListener
            }
            updateSoal()
        }
        (0..listsoal.size - 1).forEach {
            jawaban.add(
                JawabanModel(
                    listsoal.get(it).id,
                    0, 0
                )
            )
        }
    }

    private fun updateSoal() {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start()
        val dataSoal = listsoal.get(position);
        lyt_pembahasan.visibility = View.GONE
        lyt_pembahasansoal.removeAllViews()
        pertanyaan3.text = dataSoal.pertanyaan
        jawaban_a.text = dataSoal.daftar_jawaban.get(0)
        jawaban_b.text = dataSoal.daftar_jawaban.get(1)
        jawaban_c.text = dataSoal.daftar_jawaban.get(2)
        jawaban_d.text = dataSoal.daftar_jawaban.get(3)
        jawaban_e.text = dataSoal.daftar_jawaban.get(4)
        jawaban_benar?.text = dataSoal.daftar_jawaban.get(dataSoal.jawaban_benar!! - 1)
        dataSoal.pembahasan.forEach {
            when (it.tipe) {
                0 -> {
                    lyt_pembahasansoal.addView(
                        Helpers.generateTextView(
                            this,
                            it.data!!.replace("<br>", "\n")
                        )
                    )
                }
                1 -> {
                    val iv = Helpers.generateImageView(this)
                    Glide.with(this)
                        .load(it.data)
                        .error(R.drawable.ic_image_black_24dp)
                        .into(iv)
                    lyt_pembahasansoal.addView(iv)
                }
            }
        }
        jawaban_group.clearCheck()
    }

    fun cekJawaban(i: Int): String {
        var jawabanModel = jawaban.get(position)
        jawabanModel.jawaban = i
        jawabanModel.timestamp = SystemClock.elapsedRealtime() - chronometer.base
        jawaban.set(position, jawabanModel)
        if (listsoal.get(position).jawaban_benar == i) {
            lyt_pembenaran.visibility = View.GONE
            return "benar"
        } else {
            lyt_pembenaran.visibility = View.VISIBLE
            return "salah"
        }
    }
}
