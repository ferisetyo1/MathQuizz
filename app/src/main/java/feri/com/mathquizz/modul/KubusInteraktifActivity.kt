package feri.com.mathquizz.modul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import feri.com.mathquizz.R
import kotlinx.android.synthetic.main.activity_kubus_interaktif.*

class KubusInteraktifActivity : AppCompatActivity(), View.OnClickListener {

    var activeButtonID: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kubus_interaktif)
        supportActionBar?.hide()
    }

    override fun onClick(p0: View?) {
        if (activeButtonID != null && !p0?.id?.equals(R.id.btn_back)!!) {
            activeButtonID?.background = ContextCompat.getDrawable(this, R.drawable.solidcorner)
        }
        when (p0?.id) {
            R.id.btn_back -> {
                finish()
            }
            R.id.ad -> {
                activeButtonID = ad
                ad.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.ad)
            }
            R.id.ab -> {
                activeButtonID = ab
                ab.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.ab)
            }
            R.id.bc -> {
                activeButtonID = bc
                bc.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.bc)
            }
            R.id.dc -> {
                activeButtonID = dc
                dc.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.dc)
            }
            R.id.ef -> {
                activeButtonID = ef
                ef.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.ef)
            }
            R.id.fg -> {
                activeButtonID = fg
                fg.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.fg)
            }
            R.id.hg -> {
                activeButtonID = hg
                hg.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.hg)
            }
            R.id.eh -> {
                activeButtonID = eh
                eh.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.eh)
            }

            R.id.ae -> {
                activeButtonID = ae
                ae.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.ae)
            }

            R.id.bf -> {
                activeButtonID = bf
                bf.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.bf)
            }

            R.id.cg -> {
                activeButtonID = cg
                cg.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.cg)
            }

            R.id.dh -> {
                activeButtonID = dh
                dh.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.dh)
            }

            R.id.abfe -> {
                activeButtonID = abfe
                abfe.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.abfe)
            }

            R.id.adhe -> {
                activeButtonID = adhe
                adhe.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.adhe)
            }

            R.id.efgh -> {
                activeButtonID = efgh
                efgh.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.efgh)
            }
            R.id.abcd -> {
                activeButtonID = abcd
                abcd.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.abcd)
            }
            R.id.dcgh -> {
                activeButtonID = dcgh
                dcgh.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.dcgh)
            }
            R.id.bfgc -> {
                activeButtonID = bfgc
                bfgc.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.bfgc)
            }

            R.id.dbfh -> {
                activeButtonID = dbfh
                dbfh.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.dbfh)
            }
            R.id.ehcb -> {
                activeButtonID = ehcb
                ehcb.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.ehcb)
            }
            R.id.adgf -> {
                activeButtonID = adgf
                adgf.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.adgf)
            }
            R.id.efcd -> {
                activeButtonID = efcd
                efcd.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.efcd)
            }
            R.id.aegc -> {
                activeButtonID = aegc
                aegc.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.aegc)
            }
            R.id.abgh -> {
                activeButtonID = abgh
                abgh.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.abgh)
            }

            R.id.df -> {
                activeButtonID = df
                df.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.df)
            }

            R.id.ag -> {
                activeButtonID = ag
                ag.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.ag)
            }

            R.id.ec -> {
                activeButtonID = ec
                ec.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.ec)
            }

            R.id.bh -> {
                activeButtonID = bh
                bh.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.bh)
            }

            R.id.eb -> {
                activeButtonID = eb
                eb.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.eb)
            }

            R.id.cf -> {
                activeButtonID = cf
                cf.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.cf)
            }

            R.id.bg -> {
                activeButtonID = bg
                bg.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.bg)
            }

            R.id.gd -> {
                activeButtonID = gd
                gd.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.gd)
            }

            R.id.ed -> {
                activeButtonID = ed
                ed.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.ed)
            }

            R.id.ha -> {
                activeButtonID = ha
                ha.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.ha)
            }

            R.id.db -> {
                activeButtonID = db
                db.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.db)
            }

            R.id.hf -> {
                activeButtonID = hf
                hf.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.hf)
            }

            R.id.eg -> {
                activeButtonID = eg
                eg.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.eg)
            }

            R.id.af -> {
                activeButtonID = af
                af.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.af)
            }

            R.id.ac -> {
                activeButtonID = ac
                ac.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.ac)
            }

            R.id.hc -> {
                activeButtonID = hc
                hc.background = ContextCompat.getDrawable(this, R.drawable.activesolidcorner)
                img_kubus.setImageResource(R.drawable.hc)
            }
        }
    }

}
