package feri.com.mathquizz.modul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.google.gson.JsonArray
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

class PengerjaanLatihanActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var activeRadioButton: RadioButton
    var position = 0
    val listsoal = ArrayList<SoalModel>()
    val jawaban = ArrayList<JawabanModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengerjaan_latihan)
        supportActionBar?.hide()

        jawaban_a.setOnClickListener(this)
        jawaban_b.setOnClickListener(this)
        jawaban_c.setOnClickListener(this)
        jawaban_d.setOnClickListener(this)
        jawaban_e.setOnClickListener(this)

        val dataSoal = intent.getParcelableArrayListExtra<SoalModel>("dataSoal")
        listsoal.addAll(dataSoal)

        updateSoal()

        btn_cek.setOnClickListener {
            chronometer.stop()
            if (activeRadioButton.id == -1) {
                Toast.makeText(this, "pilih jawaban terlebih dahulu", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            lyt_pembahasan.visibility = View.VISIBLE
            status_jawaban?.text = when (activeRadioButton.id) {
                R.id.jawaban_a -> cekJawaban(1)
                R.id.jawaban_b -> cekJawaban(2)
                R.id.jawaban_c -> cekJawaban(3)
                R.id.jawaban_d -> cekJawaban(4)
                R.id.jawaban_e -> cekJawaban(5)
                else -> cekJawaban(0)
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
        activeRadioButton = RadioButton(this)
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start()
        val dataSoal = listsoal.get(position);
        lyt_pembahasan.visibility = View.GONE
        lyt_pembahasansoal.removeAllViews()
        clearContainer()
        Helpers.textselection(this, dataSoal.pertanyaan, pertanyaan)
        Helpers.textselection(this, dataSoal.daftar_jawaban.get(0), container_a)
        Helpers.textselection(this, dataSoal.daftar_jawaban.get(1), container_b)
        Helpers.textselection(this, dataSoal.daftar_jawaban.get(2), container_c)
        Helpers.textselection(this, dataSoal.daftar_jawaban.get(3), container_d)
        Helpers.textselection(this, dataSoal.daftar_jawaban.get(4), container_e)
        Helpers.textselection(
            this,
            dataSoal.daftar_jawaban.get(dataSoal.jawaban_benar!! - 1),
            jawaban_benar
        )
        Helpers.textselection(this, dataSoal.pembahasan, lyt_pembahasansoal)
        motivasi.text = dataSoal.motivasi
        setchecked(false, false, false, false, false)
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

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.jawaban_a -> {
                activeRadioButton = p0 as RadioButton
                setchecked(true, false, false, false, false)
            }
            R.id.jawaban_b -> {
                activeRadioButton = p0 as RadioButton
                setchecked(false, true, false, false, false)
            }
            R.id.jawaban_c -> {
                activeRadioButton = p0 as RadioButton
                setchecked(false, false, true, false, false)
            }
            R.id.jawaban_d -> {
                activeRadioButton = p0 as RadioButton
                setchecked(false, false, false, true, false)
            }
            R.id.jawaban_e -> {
                activeRadioButton = p0 as RadioButton
                setchecked(false, false, false, false, true)
            }
        }
    }

    private fun setchecked(b: Boolean, b1: Boolean, b2: Boolean, b3: Boolean, b4: Boolean) {
        jawaban_a.isChecked = b
        jawaban_b.isChecked = b1
        jawaban_c.isChecked = b2
        jawaban_d.isChecked = b3
        jawaban_e.isChecked = b4
    }

    private fun clearContainer() {
        pertanyaan.removeAllViews()
        container_a.removeAllViews()
        container_b.removeAllViews()
        container_c.removeAllViews()
        container_d.removeAllViews()
        container_e.removeAllViews()
        jawaban_benar.removeAllViews()
    }

    fun back(view: View) {
        finish()
    }

}
