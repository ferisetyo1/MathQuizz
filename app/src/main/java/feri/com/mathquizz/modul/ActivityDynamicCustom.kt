package feri.com.mathquizz.modul

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.rpc.Help
import feri.com.mathquizz.R
import feri.com.mathquizz.model.DynamicDataModel
import feri.com.mathquizz.util.Helpers
import kotlinx.android.synthetic.main.activity_dynamic_custom.*
import kotlinx.android.synthetic.main.activity_pengerjaan_latihan.*

class ActivityDynamicCustom : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_custom)
        supportActionBar?.hide()

        val data = intent.getParcelableExtra<DynamicDataModel>("data")
        val kategori = intent.getStringExtra("kategori")
        tv_tittle.text = kategori

        if (kategori.equals("materi", true)) {
            val judul = intent.getStringExtra("judul")
            container.addView(Helpers.generateTextView(this, judul).apply {
                textSize = 18f
                setTypeface(this.typeface, Typeface.BOLD)
            })
            val dataText = data.data?.split("<section>")
            Helpers.textselection(this, dataText?.get(0), container)
            val btn_pembahasan = Button(this)
            btn_pembahasan.setOnClickListener {
                startActivity(
                    Intent(this, ActivityDynamicCustom::class.java)
                        .putExtra("data", DynamicDataModel(null, dataText?.get(1)))
                        .putExtra("kategori", "Pembahasan")
                )

            }
            btn_pembahasan.background = ContextCompat.getDrawable(this, R.drawable.solidcorner)
            btn_pembahasan.setTextColor(ContextCompat.getColor(this, android.R.color.white))
            btn_pembahasan.text = "Cek Pembahasan"
            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            lp.gravity=Gravity.CENTER_HORIZONTAL
            lp.setMargins(0, 8, 0, 0)
            btn_pembahasan.setEms(20)
            btn_pembahasan.layoutParams = lp
            container.addView(btn_pembahasan)
        } else {
            Helpers.textselection(this, data.data, container)
        }


        btn_back.setOnClickListener {
            finish()
        }
    }
}
