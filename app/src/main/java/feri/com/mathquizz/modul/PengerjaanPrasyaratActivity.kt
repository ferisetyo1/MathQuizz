package feri.com.mathquizz.modul

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.content.ContextCompat
import feri.com.mathquizz.R
import feri.com.mathquizz.model.JawabanModel
import feri.com.mathquizz.model.SoalModel
import feri.com.mathquizz.util.Helpers
import kotlinx.android.synthetic.main.activity_pengerjaan_prasyarat.*
import kotlinx.android.synthetic.main.activity_pengerjaan_prasyarat.btn_next
import kotlinx.android.synthetic.main.dialog_sebelum_belajar.*

class PengerjaanPrasyaratActivity : AppCompatActivity(), View.OnClickListener {

    var position = 0
    val listsoal = ArrayList<SoalModel>()
    //    val jawaban = ArrayList<JawabanModel>()
    var salahCount = 0
    lateinit var activeRadioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        ShowDialogDoa()

        setContentView(R.layout.activity_pengerjaan_prasyarat)

        val dataSoal = intent.getParcelableArrayListExtra<SoalModel>("dataSoal")
        listsoal.addAll(dataSoal)

        jawaban_a.setOnClickListener(this)
        jawaban_b.setOnClickListener(this)
        jawaban_c.setOnClickListener(this)
        jawaban_d.setOnClickListener(this)

        btn_next.setOnClickListener {
            val dataSoal = listsoal.get(position)
            when (activeRadioButton?.id) {
                R.id.jawaban_a -> {
                    if (dataSoal.jawaban_benar != 1) salahCount++
                }
                R.id.jawaban_b -> {
                    if (dataSoal.jawaban_benar != 2) salahCount++
                }
                R.id.jawaban_c -> {
                    if (dataSoal.jawaban_benar != 3) salahCount++
                }
                R.id.jawaban_d -> {
                    if (dataSoal.jawaban_benar != 4) salahCount++
                }
                else -> {
                    salahCount++
                }
            }
            if (btn_next.text.equals("finish")) {
                val isLulus = salahCount < 3
                startActivity(
                    Intent(this, ResultPrasyarat::class.java)
                        .putExtra("isLulus", isLulus)
                        .putExtra("listSoal", listsoal)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                )
            }
            position++
            if (position == listsoal.size - 1) {
                btn_next.text = "finish"
            }

            if (position == listsoal.size) {
                Log.d("position error", "$position")
                position--
                Log.d("position fixed", "$position")
                return@setOnClickListener
            }
            clearContainer()
            updateSoal(listsoal.get(position))
        }
        clearContainer()
        updateSoal(listsoal.get(position))
    }

    private fun ShowDialogDoa() {
        val dialog = Dialog(this)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_sebelum_belajar)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.close.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun updateSoal(dataSoal: SoalModel) {
        activeRadioButton = RadioButton(this)
        Helpers.textselection(this, dataSoal.pertanyaan, pertanyaan)
        Helpers.textselection(this, dataSoal.daftar_jawaban.get(0), container_a)
        Helpers.textselection(this, dataSoal.daftar_jawaban.get(1), container_b)
        Helpers.textselection(this, dataSoal.daftar_jawaban.get(2), container_c)
        Helpers.textselection(this, dataSoal.daftar_jawaban.get(3), container_d)
        setchecked(false, false, false, false)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.jawaban_a -> {
                activeRadioButton = p0 as RadioButton
                setchecked(true, false, false, false)
            }
            R.id.jawaban_b -> {
                activeRadioButton = p0 as RadioButton
                setchecked(false, true, false, false)
            }
            R.id.jawaban_c -> {
                activeRadioButton = p0 as RadioButton
                setchecked(false, false, true, false)
            }
            R.id.jawaban_d -> {
                activeRadioButton = p0 as RadioButton
                setchecked(false, false, false, true)
            }
        }
    }

    private fun setchecked(b: Boolean, b1: Boolean, b2: Boolean, b3: Boolean) {
        jawaban_a.isChecked = b
        jawaban_b.isChecked = b1
        jawaban_c.isChecked = b2
        jawaban_d.isChecked = b3
    }

    private fun clearContainer() {
        pertanyaan.removeAllViews()
        container_a.removeAllViews()
        container_b.removeAllViews()
        container_c.removeAllViews()
        container_d.removeAllViews()
    }

    fun back(view: View) {onBackPressed()}
}
