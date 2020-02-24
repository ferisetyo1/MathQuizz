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
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import feri.com.mathquizz.R
import feri.com.mathquizz.model.DynamicDataModel

class MateriActivity : AppCompatActivity(), View.OnClickListener {

    val db = FirebaseFirestore.getInstance()
    val materiReff = db.collection("materi")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materi)
        supportActionBar?.hide()
    }

    override fun onClick(p0: View?) {
        val builderdialog = AlertDialog.Builder(this)
        builderdialog.setCancelable(false)
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        builderdialog.setView(inflater.inflate(R.layout.progress, null))
        val dialog = builderdialog.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        when (p0?.id) {
            R.id.btn_bagiankubus->{
                startActivity(Intent(this,KubusInteraktifActivity::class.java))
            }
            R.id.btn_materi1 -> {
                dialog.show()
                showSoal(dialog, "materi1",R.string.materi1)
            }
            R.id.btn_materi2 -> {
                dialog.show()
                showSoal(dialog, "materi2",R.string.materi2)
            }
            R.id.btn_materi3 -> {
                dialog.show()
                showSoal(dialog, "materi3",R.string.materi3)
            }
            R.id.btn_materi4 -> {
                dialog.show()
                showSoal(dialog, "materi4",R.string.materi4)
            }
            R.id.btn_materi5 -> {
                dialog.show()
                showSoal(dialog, "materi5",R.string.materi5)
            }
            R.id.btn_materi6 -> {
                dialog.show()
                showSoal(dialog, "materi6",R.string.materi6)
            }
            R.id.btn_back -> {
                finish()
            }
        }
    }

    private fun showSoal(dialog: AlertDialog?, s: String,resjudul:Int) {
        materiReff.whereEqualTo("id", s).get().addOnSuccessListener {
            dialog?.dismiss()
            if (it.isEmpty) {
                Log.d("TAG", "is empty");
                Toast.makeText(this, "Materi masih belum tersedia", Toast.LENGTH_LONG).show()
            } else {
                val data = it.toObjects(DynamicDataModel::class.java)
                Log.d("TAG", "${data.get(0)}");
                startActivity(
                    Intent(
                        this,
                        ActivityDynamicCustom::class.java
                    )
                        .putExtra("data", data.get(0))
                        .putExtra("kategori", "Materi")
                        .putExtra("judul",getString(resjudul))
                )
            }
        }
    }
}
