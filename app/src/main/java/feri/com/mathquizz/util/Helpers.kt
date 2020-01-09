package feri.com.mathquizz.util

import android.app.ActionBar
import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import feri.com.mathquizz.R
import java.util.concurrent.TimeUnit

object Helpers {
    fun longtoDate(millis: Long): String {
        return String.format(
            "%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
            TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
            TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1)
        )
    }

    fun generateTextView(context: Context, string: String): TextView {
        val tv1 = TextView(context)
        val lptv1 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        lptv1.setMargins(0, 8, 0, 0)

        tv1.setLayoutParams(lptv1)
        tv1.setText(string) // title
        tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14F)
        tv1.setTextColor(ContextCompat.getColor(context, R.color.colorSkyBlue1))
        return tv1
    }

    fun generateImageView(context: Context): ImageView {
        val iv = ImageView(context)
        val lp = LinearLayout.LayoutParams(
            300,
            300
        )
        lp.setMargins(0, 8, 0, 0)
        lp.gravity = Gravity.CENTER_HORIZONTAL
        iv.setLayoutParams(lp)
//        iv.getLayoutParams().height = 150
//        iv.getLayoutParams().width = 150
        iv.scaleType = ImageView.ScaleType.CENTER_INSIDE
        return iv
    }
}