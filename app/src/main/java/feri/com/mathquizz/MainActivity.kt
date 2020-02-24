package feri.com.mathquizz

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.firestore.FirebaseFirestore
import feri.com.mathquizz.db.dbJawaban
import feri.com.mathquizz.db.dbResult
import feri.com.mathquizz.shared.SPManager
import feri.com.mathquizz.util.Helpers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val dbServer = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val nav_controller = findNavController(R.id.nav_view)
        bottomNavigationView.setupWithNavController(nav_controller)

        if (Helpers.isInternetAvailable(this)) {
            val batch = dbServer.batch()
            with(dbResult(this)) {
                open()
                Log.d("TAG ONLINE", getData().toString())
                getData().forEach {
                    with(dbJawaban(this@MainActivity)) {
                        open()
                        val res = it
                        res.jawabanModels = getData(it.id!!)
                        Log.d("TAG ONLINE", res.toString())
                        batch.set(dbServer.collection("result").document(res.id!!), res)
                        close()
                    }
                }
                close()
            }
            batch.commit().addOnSuccessListener {
                with(dbResult(this)) {
                    open()
                    truncate()
                    close()
                }
                with(dbJawaban(this)) {
                    open()
                    truncate()
                    close()
                }
            }
        } else {
            with(dbResult(this)) {
                open()
                Log.d("TAG OFFLINE", getData().toString())
                close()
            }
        }
    }
}
