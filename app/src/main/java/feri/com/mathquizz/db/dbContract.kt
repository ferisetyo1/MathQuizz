package feri.com.mathquizz.db

import android.provider.BaseColumns


object dbContract {
    // Table contents are grouped together in an anonymous object.
    object ResTable : BaseColumns {
        const val TABLE_NAME = "RESTABLES"
        const val COLUMN_NAME_ID = "id_soal"
        const val COLUMN_NAME_CREATEDAT_S = "created_s"
        const val COLUMN_NAME_CREATEDAT_N = "created_n"
        const val COLUMN_NAME_USERID = "user_id"
        const val COLUMN_NAME_TIPESOAL = "tipe_soal"
        const val COLUMN_NAME_TIMESTAMPTOTAL = "totaltimestamp"
    }

    object JawModel : BaseColumns {
        const val TABLE_NAME = "JAWTABLES"
        const val COLUMN_NAME_IDRESULT = "id_result"
        const val COLUMN_NAME_IDSOal = "id_soal"
        const val COLUMN_NAME_JAWABAN = "jawaban"
        const val COLUMN_NAME_TIMESTAMP = "timestamp"
    }
}
