package feri.com.mathquizz.model

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.firebase.firestore.IgnoreExtraProperties
import kotlinx.android.parcel.Parcelize
import java.sql.Timestamp

@IgnoreExtraProperties
@Keep
@Parcelize
class ResultModel(
    var id: String? = null,
    var userid: String? = null,
    var tipeSoal:Int?=-1,
    var timestampTotal:Long?=-1,
    var jawabanModels: ArrayList<JawabanModel> = ArrayList()
) : Parcelable {


}