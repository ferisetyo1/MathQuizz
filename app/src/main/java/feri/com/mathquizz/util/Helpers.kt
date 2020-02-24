package feri.com.mathquizz.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.size
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.target.Target
import feri.com.mathquizz.R
import io.github.kexanie.library.MathView
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

    fun generateMathView(context: Context, string: String): MathView {
        val tv1 = MathView(context, null)
        val lptv1 = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        lptv1.setMargins(0, 0, 0, 0)
        tv1.setLayoutParams(lptv1)
        tv1.setText(string) // title
        return tv1
    }

    fun generateImageView(context: Context): ImageView {
        val iv = ImageView(context)
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        lp.setMargins(0, 8, 0, 0)
        lp.gravity = Gravity.CENTER_HORIZONTAL
        iv.setLayoutParams(lp)
        iv.scaleType = ImageView.ScaleType.FIT_CENTER
        return iv
    }

    fun generateKey(prefix: String, separator: String, length: Int): String {
        val STRING_CHARACTERS = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return "${prefix}${separator}" + (1..length).map { STRING_CHARACTERS.random() }.joinToString(
            ""
        )
    }

    fun isInternetAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }

                }
            }
        }

        return result
    }

    fun textselection(context: Context, par: String?, container: LinearLayout) {
        if (par?.contains("<img>")!! || par?.contains("<rms>")!!) {
            val listString = par?.split("<img>|<rms>".toRegex())
            Log.d("listString", listString.toString())
            listString?.forEach {
                if (it.isNotEmpty()) {
                    if (it.contains(".jpg", true) || it.contains(".png", true)) {
                        val iv = generateImageView(context)
                        var myOptions = RequestOptions()
                            .fitCenter()
                        Glide.with(context)
                            .asDrawable()
                            .load(it)
                            .transition(withCrossFade())
                            .apply(myOptions)
                            .error(R.drawable.ic_image_black_24dp)
                            .into(iv)
                        container.addView(iv)
                    } else if (it.contains("@(@") || it.contains("@@")) {
                        container.addView(
                            generateMathView(
                                context,
                                it.replace("@(@", "\\(")
                                    .replace("@)@", "\\)")
                                    .replace("@div@", "$$")
                                    .replace("@@","\\")
                            )
                        )
                        //\tiny, \scriptsize, \footnotesize, \small, \normalsize (default), \large, \Large (capital "L"), \LARGE (all caps), \huge, \Huge (capital "H")
                    } else {
                        container.addView(generateTextView(context, it.replace("<br>", "\n")))
                    }
                }
            }
        } else {
            container.addView(generateTextView(context, par.replace("<br>", "\n")))
        }
    }
}