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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.google.firebase.firestore.FirebaseFirestore

import feri.com.mathquizz.R
import kotlinx.android.synthetic.main.fragment_penyusun.*

class PenyusunFragment : Fragment() {

    var db = FirebaseFirestore
        .getInstance()
        .collection("aplikasidata")
        .document("profil")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_penyusun, container, false)
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
            Glide.with(activity!!).asDrawable()
                .load(it.getString("img_profil"))
                .transition(withCrossFade())
                .circleCrop()
                .error(R.drawable.ic_image_black_24dp)
                .into(img_profil)

            nama_lengkap.text = it.getString("nama")
            agma.text = it.getString("agama")
            alamat.text = it.getString("alamat")
            email.text = it.getString("email")
            pekerjaan.text = it.getString("pekerjaan")?.replace("<br>", "\n")
            ttl.text = it.getString("ttl")
        }.addOnFailureListener {
            dialog.dismiss()
            it.printStackTrace()
        }
    }
}
