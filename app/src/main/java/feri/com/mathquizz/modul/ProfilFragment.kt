package feri.com.mathquizz.modul


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
import feri.com.mathquizz.model.DynamicDataModel
import kotlinx.android.synthetic.main.fragment_profil.*

/**
 * A simple [Fragment] subclass.
 */
class ProfilFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        more.setOnClickListener(this)
        less.setOnClickListener(this)
        btn_motivasi.setOnClickListener(this)
        btn_pengantar.setOnClickListener(this)
        btn_petunjuk.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.more -> {
                more.visibility = View.GONE
                profil_detail.visibility = View.VISIBLE
            }
            R.id.less -> {
                more.visibility = View.VISIBLE
                profil_detail.visibility = View.GONE
            }
            R.id.btn_pengantar -> {
                redirect("Pengantar")
            }
            R.id.btn_petunjuk -> {
                redirect("Petunjuk")
            }
            R.id.btn_motivasi -> {
                redirect("Motivasi")
            }
        }
    }

    private fun redirect(s: String) {
        val builderdialog = AlertDialog.Builder(activity)
        builderdialog.setCancelable(false)
        val inflater =
            context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        builderdialog.setView(inflater.inflate(R.layout.progress, null))
        val dialog = builderdialog.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
        FirebaseFirestore.getInstance()
            .collection("aplikasidata")
            .whereEqualTo("id", s.toLowerCase()).get()
            .addOnSuccessListener {
                dialog?.dismiss()
                if (it.isEmpty) {
                    Log.d("TAG", "is empty");
                    Toast.makeText(activity, "$s masih belum tersedia", Toast.LENGTH_LONG)
                        .show()
                } else {
                    val data = it.toObjects(DynamicDataModel::class.java)
                    Log.d("TAG", "${data.get(0)}");
                    startActivity(
                        Intent(
                            activity,
                            ActivityDynamicCustom::class.java
                        )
                            .putExtra("data", data.get(0))
                            .putExtra("kategori", s)
                    )
                }
            }
    }


}
