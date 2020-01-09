package feri.com.mathquizz.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Keep
@Parcelize
class PembahasanModel(
    var tipe: Int? = -1,
    var data: String? = null
) : Parcelable {
    constructor() : this(-1, "")

    override fun toString(): String {
        return "PembahasanModel(tipe=$tipe, data=$data)"
    }


}