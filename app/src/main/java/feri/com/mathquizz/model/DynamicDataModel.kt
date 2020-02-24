package feri.com.mathquizz.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Keep
@Parcelize
class DynamicDataModel(

    var id: String? = null,
    var data: String? = null

) : Parcelable {
    constructor() : this("", "")

    override fun toString(): String {
        return "DynamicDataModel(id=$id, data=$data)"
    }
    
}