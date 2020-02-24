package feri.com.mathquizz.modul


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import feri.com.mathquizz.R
import kotlinx.android.synthetic.main.fragment_kompetensi.*

/**
 * A simple [Fragment] subclass.
 */
class KompetensiFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kompetensi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        comp_3.setOnClickListener(this)
        comp_4.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.comp_3 -> {
                setVisibiltyContent(comp_3, content_kompetensi_inti_3)
            }
            R.id.comp_4 ->{
                setVisibiltyContent(comp_4, content_kompetensi_inti_4)
            }
        }
    }

    private fun setVisibiltyContent(comp: TextView?, contentKompetensiInti: LinearLayout?) {
        if (contentKompetensiInti?.visibility?.equals(View.GONE)!!) {
            contentKompetensiInti.visibility = View.VISIBLE
            comp?.setCompoundDrawablesWithIntrinsicBounds(
                null, null,
                context?.let {
                    ContextCompat.getDrawable(
                        it,
                        R.drawable.ic_keyboard_arrow_up_black_24dp
                    )
                }, null
            )
            return
        }
        contentKompetensiInti.visibility = View.GONE
        comp?.setCompoundDrawablesWithIntrinsicBounds(
            null, null,
            context?.let {
                ContextCompat.getDrawable(
                    it,
                    R.drawable.ic_keyboard_arrow_down_black_24dp
                )
            }, null
        )
    }


}
