package feri.com.mathquizz.modul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.RadioButton
import feri.com.mathquizz.R
import feri.com.mathquizz.model.JawabanModel
import feri.com.mathquizz.model.SoalModel
import feri.com.mathquizz.util.Helpers
import kotlinx.android.synthetic.main.activity_pengerjaan_quizz.*

class PengerjaanQuizzActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var activeRadioButton: RadioButton
    var position = 0
    val listsoal = ArrayList<SoalModel>()
    val jawaban = ArrayList<JawabanModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengerjaan_quizz)
        supportActionBar?.hide()


        jawaban_a.setOnClickListener(this)
        jawaban_b.setOnClickListener(this)
        jawaban_c.setOnClickListener(this)
        jawaban_d.setOnClickListener(this)
        jawaban_e.setOnClickListener(this)

        val dataSoal = intent.getParcelableArrayListExtra<SoalModel>("dataSoal")
        listsoal.addAll(dataSoal)

        updateSoal()

        (0..listsoal.size - 1).forEach {
            jawaban.add(
                JawabanModel(
                    listsoal.get(it).id,
                    0, 0
                )
            )
        }

        btn_next.setOnClickListener {
            var jawabanModel = jawaban.get(position)
            jawabanModel.jawaban = when (activeRadioButton.id) {
                R.id.jawaban_a -> 1
                R.id.jawaban_b -> 2
                R.id.jawaban_c -> 3
                R.id.jawaban_d -> 4
                R.id.jawaban_e -> 5
                else -> 0
            }

            jawabanModel.timestamp = SystemClock.elapsedRealtime() - chronometer.base

            if (btn_next.text.equals("finish")) {
                startActivity(
                    Intent(this, ResultActivity::class.java)
                        .putExtra("dataSoal", listsoal)
                        .putExtra("dataJawaban", jawaban)
                        .putExtra("tipe", 2)
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

            updateSoal()
        }

    }

    private fun updateSoal() {
        activeRadioButton = RadioButton(this)
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start()
        val dataSoal = listsoal.get(position);
        clearContainer()
        Helpers.textselection(this, dataSoal.pertanyaan, pertanyaan)
        Helpers.textselection(this, dataSoal.daftar_jawaban.get(0), container_a)
        Helpers.textselection(this, dataSoal.daftar_jawaban.get(1), container_b)
        Helpers.textselection(this, dataSoal.daftar_jawaban.get(2), container_c)
        Helpers.textselection(this, dataSoal.daftar_jawaban.get(3), container_d)
        Helpers.textselection(this, dataSoal.daftar_jawaban.get(4), container_e)
        setchecked(false, false, false, false, false)
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
    }

    fun back(view: View) {
        finish()
    }
}
