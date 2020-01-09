package feri.com.mathquizz.modul


import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

import feri.com.mathquizz.R
import feri.com.mathquizz.model.SoalModel
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.zip.Inflater

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(), View.OnClickListener {

    val db = FirebaseFirestore.getInstance()
    val soalReff = db.collection("soal")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_quiz.setOnClickListener(this)
        btn_latihan.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_quiz -> {
                val builderdialog = AlertDialog.Builder(activity)
                builderdialog.setCancelable(false)
                val inflater =
                    context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                builderdialog.setView(inflater.inflate(R.layout.progress, null))
                val dialog = builderdialog.create()
                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.show()
                val list = ArrayList<SoalModel>()
                soalReff.get().addOnSuccessListener {
                    dialog.dismiss()
                    if (it.isEmpty) {
                        Log.d("TAG", "is empty");
                        Toast.makeText(activity, "Soal masih belum tersedia", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        val data = it.toObjects(SoalModel::class.java)
                        list.addAll(data)
                        Log.d("TAG", "$list");
                        startActivity(
                            Intent(
                                activity,
                                QuizActivity::class.java
                            ).putExtra("dataSoal", list)
                        )
                    }
                }.addOnFailureListener {
                    dialog.dismiss()
                }
            }

            R.id.btn_latihan -> {
                startActivity(Intent(activity, LatihanActivity::class.java))
            }
        }
    }
}

