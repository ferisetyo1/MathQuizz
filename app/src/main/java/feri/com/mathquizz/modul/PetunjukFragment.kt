package feri.com.mathquizz.modul


import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.firestore.FirebaseFirestore

import feri.com.mathquizz.R
import feri.com.mathquizz.util.Helpers
import kotlinx.android.synthetic.main.fragment_petunjuk.*

/**
 * A simple [Fragment] subclass.
 */
class PetunjukFragment : Fragment() {

    var db = FirebaseFirestore
        .getInstance()
        .collection("aplikasidata")
        .document("petunjuk")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_petunjuk, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val builderdialog = AlertDialog.Builder(activity)
        builderdialog.setCancelable(false)
        val inflater =
            context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        builderdialog.setView(inflater.inflate(R.layout.progress, null))
        val dialog = builderdialog.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        db.get().addOnSuccessListener {
            dialog.dismiss()
            Helpers.textselection(context!!, it.getString("data"), container)
        }.addOnFailureListener {
            dialog.dismiss()
            it.printStackTrace()
        }
    }

}
