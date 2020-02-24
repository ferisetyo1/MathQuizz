package feri.com.mathquizz.db

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.google.firebase.Timestamp
import feri.com.mathquizz.db.dbHelper
import feri.com.mathquizz.model.JawabanModel
import feri.com.mathquizz.model.ResultModel


class dbJawaban(context: Context) {

    private lateinit var db: SQLiteDatabase
    private var dbhelper: dbHelper

    init {
        dbhelper = dbHelper(context)
    }

    @Throws(SQLException::class)
    fun open(): dbJawaban? {
        db = dbhelper.writableDatabase
        return this
    }

    fun close() {
        db.close()
    }

    fun getData(s:String): ArrayList<JawabanModel> {
        val list = ArrayList<JawabanModel>()
        val selection = "${dbContract.JawModel.COLUMN_NAME_IDRESULT} = ?"
        val selectionArgs = arrayOf(s)
        val cursor = db.query(
            dbContract.JawModel.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        cursor.moveToFirst()
        with(cursor){
            while (moveToNext()){
                list.add(
                    JawabanModel(
                        getString(getColumnIndex(dbContract.JawModel.COLUMN_NAME_IDSOal)),
                        getLong(getColumnIndex(dbContract.JawModel.COLUMN_NAME_TIMESTAMP)),
                        getInt(getColumnIndex(dbContract.JawModel.COLUMN_NAME_JAWABAN))
                    )
                )
            }
        }
        cursor.close()
        return list
    }

    fun addData(idResult:String,model: JawabanModel): Long {
        val contentValues=ContentValues()
        contentValues.put(dbContract.JawModel.COLUMN_NAME_IDRESULT,idResult)
        contentValues.put(dbContract.JawModel.COLUMN_NAME_IDSOal,model.idSoal)
        contentValues.put(dbContract.JawModel.COLUMN_NAME_JAWABAN,model.jawaban)
        contentValues.put(dbContract.JawModel.COLUMN_NAME_TIMESTAMP,model.timestamp)
        return db.insert(dbContract.JawModel.TABLE_NAME,null,contentValues)
    }

    fun truncate(){
        db.execSQL("DELETE FROM ${dbContract.JawModel.TABLE_NAME}")
    }
}