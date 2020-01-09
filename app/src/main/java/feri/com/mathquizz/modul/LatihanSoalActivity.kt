package feri.com.mathquizz.modul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import feri.com.mathquizz.R
import feri.com.mathquizz.model.SoalModel
import feri.com.mathquizz.util.Helpers
import kotlinx.android.synthetic.main.activity_latihan_soal.*

class LatihanSoalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latihan_soal)
        supportActionBar?.hide()

        val dataSoal = intent.getParcelableExtra("dataSoal") as SoalModel
        tv_tipe.text = when (dataSoal.tipe) {
            "materi1" -> getString(R.string.materi1)
            "materi2" -> getString(R.string.materi2)
            "materi3" -> getString(R.string.materi3)
            "materi4" -> getString(R.string.materi4)
            "materi5" -> getString(R.string.materi5)
            else -> getString(R.string.materi6)
        }

        pertanyaan3.text = dataSoal.pertanyaan
        jawaban_a.text = dataSoal.daftar_jawaban.get(0)
        jawaban_b.text = dataSoal.daftar_jawaban.get(1)
        jawaban_c.text = dataSoal.daftar_jawaban.get(2)
        jawaban_d.text = dataSoal.daftar_jawaban.get(3)
        jawaban_e.text = dataSoal.daftar_jawaban.get(4)

        btn_cek.setOnClickListener {
            lyt_pembahasan.visibility = View.VISIBLE
            status_jawaban.text = when (jawaban_group.checkedRadioButtonId) {
                R.id.jawaban_a -> cekJawaban(dataSoal, 1)
                R.id.jawaban_b -> cekJawaban(dataSoal, 2)
                R.id.jawaban_c -> cekJawaban(dataSoal, 3)
                R.id.jawaban_d -> cekJawaban(dataSoal, 4)
                else -> cekJawaban(dataSoal, 5)
            }
        }

        jawaban_benar.text = dataSoal.daftar_jawaban.get(dataSoal.jawaban_benar!! - 1)

        dataSoal.pembahasan.forEach {
            when (it.tipe) {
                0 -> {
                    lyt_pembahasan.addView(Helpers.generateTextView(this,it.data!!))
                }
                1->{
                    val iv=Helpers.generateImageView(this)
                    Glide.with(this)
                        .load(it.data)
                        .error(R.drawable.ic_person_black_24dp)
                        .into(iv)
                    lyt_pembahasan.addView(iv)
                }
            }
        }

        lyt_pembahasan.visibility = View.GONE
    }

    fun cekJawaban(dataSoal: SoalModel, i: Int): String {
        if (dataSoal.jawaban_benar == i) {
            lyt_pembenaran.visibility = View.GONE
            return "benar"
        } else {
            lyt_pembenaran.visibility = View.VISIBLE
            return "salah"
        }
    }
}
