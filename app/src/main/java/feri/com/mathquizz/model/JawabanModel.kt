package feri.com.mathquizz.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Keep
@Parcelize
class JawabanModel(
    var idSoal:String?=null,
    var timestamp:Long?=0,
    var jawaban:Int?=0
) : Parcelable {
    override fun toString(): String {
        return "JawabanModel(idSoal=$idSoal, timestamp=$timestamp, jawaban=$jawaban)"
    }
}