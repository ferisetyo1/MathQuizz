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
import com.google.firebase.firestore.FirebaseFirestore
import feri.com.mathquizz.MainActivity
import feri.com.mathquizz.R
import feri.com.mathquizz.model.UserModel
import feri.com.mathquizz.shared.SPManager
import feri.com.mathquizz.util.Helpers
import kotlinx.android.synthetic.main.activity_new_user.*

class NewUserActivity : AppCompatActivity() {

    val db = FirebaseFirestore.getInstance()
    val userReff = db.collection("user")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)
        supportActionBar?.hide()
        val builderdialog = AlertDialog.Builder(this)
        builderdialog.setCancelable(false)
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        builderdialog.setView(inflater.inflate(R.layout.progress, null))
        val dialog = builderdialog.create()

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        btn_simpan.setOnClickListener {
            dialog.show()
            var id=Helpers.generateKey(input_nama.text.toString().replace(" ",""), "_", 6)
            userReff.document(id).set(UserModel(input_nama.text.toString(), Helpers.generateKey("", "", 6)))
                .addOnCompleteListener {
                    dialog.dismiss()
                    SPManager(this).apply {
                        saveSPString(KEY_NAMA_USER, input_nama.text.toString())
                        saveSPString(KEY_ID_USER, id)
                    }
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
        }
    }
}
