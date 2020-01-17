package feri.com.mathquizz.modul

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import feri.com.mathquizz.R
import feri.com.mathquizz.model.DynamicDataModel
import feri.com.mathquizz.util.Helpers
import kotlinx.android.synthetic.main.activity_dynamic_custom.*

class ActivityDynamicCustom : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_custom)
        supportActionBar?.hide()

        val kategori = intent.getStringExtra("kategori")
        tv_tittle.text = kategori

        if (kategori.equals("Materi")) {
            val judul = intent.getStringExtra("judul")
            container.addView(Helpers.generateTextView(this, judul).apply {
                textSize = 18f
                setTypeface(this.typeface, Typeface.BOLD)
            })
        }

        val data = intent.getParcelableExtra<DynamicDataModel>("data")
        data.data.forEach {
            when (it.tipe) {
                0 -> {
                    container.addView(
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
                    container.addView(iv)
                }
            }
        }

        btn_back.setOnClickListener {
            finish()
        }
    }
}
