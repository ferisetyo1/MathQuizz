package feri.com.mathquizz.util

import android.content.Context
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
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
        tv1.setTextColor(ContextCompat.getColor(context, R.color.colorBlueLab))
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
        iv.scaleType = ImageView.ScaleType.FIT_CENTER
        return iv
    }

    fun generateKey(prefix: String,separator:String, length: Int): String {
        val STRING_CHARACTERS = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return "${prefix}${separator}" + (1..length).map { STRING_CHARACTERS.random() }.joinToString("")
    }
}