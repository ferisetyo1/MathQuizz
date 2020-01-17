package feri.com.mathquizz.modul

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import feri.com.mathquizz.MainActivity
import feri.com.mathquizz.R
import feri.com.mathquizz.shared.SPManager

class SplashActivity : AppCompatActivity() {

    private val SPLASH_DELAYED = 2000L
    private val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        if (mAuth.currentUser==null){
            val builderdialog = AlertDialog.Builder(this)
            builderdialog.setCancelable(false)
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            builderdialog.setView(inflater.inflate(R.layout.progress, null))
            val dialog = builderdialog.create()
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
            mAuth.signInWithEmailAndPassword("ublearntech.lab@gmail.com", "admin1234")
                .addOnCompleteListener {
                    dialog.dismiss()
                    if (it.isSuccessful) {
                        redirect()
                    } else {
                        Toast.makeText(
                            this,
                            it.exception?.localizedMessage.toString(),
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
        }else{
            redirect()
        }
    }

    private fun redirect() {
        Handler().postDelayed(Runnable {
            if (SPManager(this).getNamaUser().equals("")) {
                startActivity(Intent(this, NewUserActivity::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
            finish()
        }, SPLASH_DELAYED)
    }
}
