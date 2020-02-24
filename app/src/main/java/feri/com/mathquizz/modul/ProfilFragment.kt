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
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.firestore.FirebaseFirestore

import feri.com.mathquizz.R
import feri.com.mathquizz.model.DynamicDataModel
import kotlinx.android.synthetic.main.fragment_profil.*

/**
 * A simple [Fragment] subclass.
 */
class ProfilFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter = ViewPagerProfil(activity?.supportFragmentManager!!)
        tabView.setupWithViewPager(viewPager)
    }

}
