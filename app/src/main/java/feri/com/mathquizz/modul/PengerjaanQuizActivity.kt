package feri.com.mathquizz.modul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.CompoundButton
import androidx.core.content.ContextCompat
import feri.com.mathquizz.model.JawabanModel
import feri.com.mathquizz.R
import feri.com.mathquizz.model.SoalModel
import feri.com.mathquizz.util.Helpers
import kotlinx.android.synthetic.main.activity_pengerjaan_quiz.*

class PengerjaanQuizActivity : AppCompatActivity(), View.OnClickListener,
    CompoundButton.OnCheckedChangeListener {

    var position = 0
    val listsoal = ArrayList<SoalModel>()
    val jawaban = ArrayList<JawabanModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengerjaan_quiz)
        supportActionBar?.hide()

        val dataSoal = intent.getParcelableArrayListExtra<SoalModel>("dataSoal")
        listsoal.addAll(dataSoal)

        progressline.max = listsoal.size
        progressline.progress = 1
        progressline.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
                return false
            }
        })

        chronometer2.format = "HH:MM:ss"
        chronometer2.setOnChronometerTickListener {
            it.text = Helpers.longtoDate(SystemClock.elapsedRealtime() - it.base)
        }

        chronometer2.start()

        (0..listsoal.size - 1).forEach {
            jawaban.add(
                JawabanModel(
                    listsoal.get(it).id,
                    0, 0
                )
            )
        }
        jawaban_a.setOnCheckedChangeListener(this)
        jawaban_b.setOnCheckedChangeListener(this)
        jawaban_c.setOnCheckedChangeListener(this)
        jawaban_d.setOnCheckedChangeListener(this)
        jawaban_e.setOnCheckedChangeListener(this)
        updateSoal()
    }

    fun updateSoal() {
        pertanyaan.text = listsoal.get(position).pertanyaan?.replace("<br>", "\n")
        jawaban_a.text = listsoal.get(position).daftar_jawaban.get(0)
        jawaban_b.text = listsoal.get(position).daftar_jawaban.get(1)
        jawaban_c.text = listsoal.get(position).daftar_jawaban.get(2)
        jawaban_d.text = listsoal.get(position).daftar_jawaban.get(3)
        jawaban_e.text = listsoal.get(position).daftar_jawaban.get(4)
        println("${jawaban.get(position).jawaban}")
        if (jawaban.get(position).jawaban != 0) {
            set_jawaban(jawaban.get(position).jawaban!!)
        } else {
            jawaban_group.clearCheck()
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_back -> {
                position--
                if (position < 0) {
                    Log.d("position error", "$position")
                    position++
                    Log.d("position fixed", "$position")
                    return
                }
                Log.d("position", "$position")
                progressline.progress = position + 1
                updateSoal()
            }
            R.id.btn_next -> {
                position++
                if (position == listsoal.size) {
                    Log.d("position error", "$position")
                    position--
                    Log.d("position fixed", "$position")
                    return
                }
                Log.d("position", "$position")
                progressline.progress = position + 1
                Log.d("progress", progressline.progress.toString())
                updateSoal()
            }
            R.id.btn_submit -> {
                startActivity(
                    Intent(this, ResultActivity::class.java)
                        .putExtra("dataSoal", listsoal)
                        .putExtra("dataJawaban", jawaban)
                        .putExtra("tipe", 2)
                        .putExtra("time", SystemClock.elapsedRealtime() - chronometer2.base)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                )
                finish()
            }
        }

        urutan_soal.text = "${position + 1}"

        if (position == 0) {
            btn_back.setBackgroundResource(R.drawable.outlinecorner)
            btn_back.setTextColor(ContextCompat.getColor(this, R.color.colorBlueLab))
            btn_next.setBackgroundResource(R.drawable.solidcorner)
            btn_next.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        } else if (position == listsoal.size - 1) {
            btn_next.setBackgroundResource(R.drawable.outlinecorner)
            btn_next.setTextColor(ContextCompat.getColor(this, R.color.colorBlueLab))
            btn_back.setBackgroundResource(R.drawable.solidcorner)
            btn_back.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        } else {
            btn_back.setBackgroundResource(R.drawable.solidcorner)
            btn_next.setBackgroundResource(R.drawable.solidcorner)
            btn_back.setTextColor(ContextCompat.getColor(this, android.R.color.white))
            btn_next.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        }
    }

    fun set_jawaban(i: Int) {
        when (i) {
            1 -> {
                jawaban_a.isChecked = true
            }
            2 -> {
                jawaban_b.isChecked = true
            }
            3 -> {
                jawaban_c.isChecked = true
            }
            4 -> {
                jawaban_d.isChecked = true
            }
            5 -> {
                jawaban_e.isChecked = true
            }
        }
    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        var jawabanModel = jawaban.get(position)
        when (p0?.id) {
            R.id.jawaban_a -> {
                jawabanModel.jawaban = 1
            }
            R.id.jawaban_b -> {
                jawabanModel.jawaban = 2
            }
            R.id.jawaban_c -> {
                jawabanModel.jawaban = 3
            }
            R.id.jawaban_d -> {
                jawabanModel.jawaban = 4
            }
            R.id.jawaban_e -> {
                jawabanModel.jawaban = 5
            }
        }
        jawaban.set(position, jawabanModel)
        Log.d("print jawaban", jawaban.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        chronometer2.stop()
    }
}
