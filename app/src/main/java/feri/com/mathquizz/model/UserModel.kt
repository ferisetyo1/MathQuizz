package feri.com.mathquizz.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Keep
@Parcelize
class UserModel(
    var nama: String? = null,
    var recovercode: String? = null
) : Parcelable{

}