package feri.com.mathquizz.shared

import android.content.Context
import android.content.SharedPreferences

class SPManager(context: Context) {
    val KEY_NAMA_USER = "nama_user"
    val KEY_ID_USER = "id_user"
    val KEY_NAMA_APP = "nama_app"
    val KEY_LULUS_PRASYARAT = "lulus_prasyarat"
    val sp: SharedPreferences

    init {
        sp = context.getSharedPreferences(KEY_NAMA_APP, Context.MODE_PRIVATE)
    }

    fun saveSPString(keySP: String, value: String) {
        val spEditor = sp.edit()
        spEditor.putString(keySP, value)
        spEditor.apply()
    }

    fun saveSPBoolean(keySP: String, value: Boolean) {
        val spEditor = sp.edit()
        spEditor.putBoolean(keySP, value)
        spEditor.apply()
    }

    fun getNamaUser(): String = sp.getString(KEY_NAMA_USER, "").toString()
    fun getIDUser(): String = sp.getString(KEY_ID_USER, "").toString()
    fun isLulusPrasyarat(): Boolean = sp.getBoolean(KEY_LULUS_PRASYARAT, false)
}