package feri.com.mathquizz.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@Keep
@IgnoreExtraProperties
@Parcelize
class SoalModel(
    var id: String? = null,
    var tipe: Int? = -1,
    var pertanyaan: String? = null,
    var jawaban_benar: Int? = null,
    var daftar_jawaban: ArrayList<String>,
    var pembahasan: String? = null,
    var motivasi: String? = null
) : Parcelable {

    constructor() : this("", -1, "", -1, ArrayList(), "", "")

    override fun toString(): String {
        return "SoalModel(id=$id, tipe=$tipe, pertanyaan=$pertanyaan, jawaban_benar=$jawaban_benar, daftar_jawaban=$daftar_jawaban, pembahasan=$pembahasan, motivasi=$motivasi)"
    }

}