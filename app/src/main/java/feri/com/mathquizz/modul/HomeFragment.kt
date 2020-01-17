package feri.com.mathquizz.modul


import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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
import feri.com.mathquizz.shared.SPManager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(), View.OnClickListener {

    private val CODE_UBAH_NAMA = 111
    private lateinit var v: View
    val db = FirebaseFirestore.getInstance()
    val soalReff = db.collection("soal")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_quiz.setOnClickListener(this)
        btn_latihan.setOnClickListener(this)
        btn_materi.setOnClickListener(this)
        btn_ganti_nama.setOnClickListener(this)

        nama_user?.text = SPManager(context!!).getNamaUser()
    }

    override fun onClick(p0: View?) {
        val builderdialog = AlertDialog.Builder(activity)
        builderdialog.setCancelable(false)
        val inflater =
            context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        builderdialog.setView(inflater.inflate(R.layout.progress, null))
        val dialog = builderdialog.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        when (p0?.id) {
            R.id.btn_quiz -> {
                dialog.show()
                val list = ArrayList<SoalModel>()
                soalReff.whereEqualTo("tipe", 2).get().addOnSuccessListener {
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
                                PengerjaanQuizActivity::class.java
                            ).putExtra("dataSoal", list)
                        )
                    }
                }.addOnFailureListener {
                    dialog.dismiss()
                }
            }

            R.id.btn_latihan -> {
                dialog.show()
                soalReff.whereEqualTo("tipe", 1).get().addOnSuccessListener {
                    dialog?.dismiss()
                    val list = ArrayList<SoalModel>()
                    if (it.isEmpty) {
                        Log.d("TAG", "is empty");
                        Toast.makeText(activity, "Soal masih belum tersedia", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        val data = it.toObjects(SoalModel::class.java)
                        list.addAll(data)
                        startActivity(
                            Intent(
                                activity,
                                PengerjaanLatihanActivity::class.java
                            ).putExtra("dataSoal", list)
                        )
                    }
                }
            }

            R.id.btn_materi->{
                startActivity(Intent(activity,MateriActivity::class.java))
            }

            R.id.btn_ganti_nama -> {
                startActivityForResult(
                    Intent(activity, GantiNamaActivity::class.java),
                    CODE_UBAH_NAMA
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE_UBAH_NAMA && resultCode == RESULT_OK) {
            v.nama_user.text = data?.getStringExtra("data")
        }
    }
}

