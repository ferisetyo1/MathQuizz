package feri.com.mathquizz.model

import androidx.annotation.Keep
import com.google.firebase.Timestamp
import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
@Keep
class ResultModel(
    var id: String? = null,
    var created_at: Timestamp,
    var userid: String? = null,
    var tipeSoal: Int? = -1,
    var timestampTotal: Long? = -1,
    var jawabanModels: ArrayList<JawabanModel> = ArrayList()
){
    override fun toString(): String {
        return "ResultModel(id=$id, created_at=$created_at, userid=$userid, tipeSoal=$tipeSoal, timestampTotal=$timestampTotal, jawabanModels=$jawabanModels)"
    }
}