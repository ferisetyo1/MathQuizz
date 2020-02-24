package feri.com.mathquizz.db

import android.content.ContentValues
import android.content.Context
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.google.firebase.Timestamp
import feri.com.mathquizz.db.dbHelper
import feri.com.mathquizz.model.ResultModel


class dbResult(context: Context) {

    private lateinit var db: SQLiteDatabase
    private var dbhelper: dbHelper

    init {
        dbhelper = dbHelper(context)
    }

    @Throws(SQLException::class)
    fun open(): dbResult? {
        db = dbhelper.writableDatabase
        return this
    }

    fun close() {
        db.close()
    }

    fun getData(): ArrayList<ResultModel> {
        val list = ArrayList<ResultModel>()
        val cursor = db.query(
            dbContract.ResTable.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
        cursor.moveToFirst()
        with(cursor){
            while (moveToNext()){
                list.add(
                    ResultModel(
                        getString(getColumnIndex(dbContract.ResTable.COLUMN_NAME_ID)),
                        Timestamp(
                            getLong(getColumnIndex(dbContract.ResTable.COLUMN_NAME_CREATEDAT_S)),
                            getInt(getColumnIndex(dbContract.ResTable.COLUMN_NAME_CREATEDAT_N))
                        ),
                        getString(getColumnIndex(dbContract.ResTable.COLUMN_NAME_USERID)),
                        getInt(getColumnIndex(dbContract.ResTable.COLUMN_NAME_TIPESOAL)),
                        getLong(getColumnIndex(dbContract.ResTable.COLUMN_NAME_TIMESTAMPTOTAL))
                    )
                )
            }
        }
        cursor.close()
        return list
    }

    fun addData(model: ResultModel): Long {
        val contentValues=ContentValues()
        contentValues.put(dbContract.ResTable.COLUMN_NAME_ID,model.id)
        contentValues.put(dbContract.ResTable.COLUMN_NAME_TIMESTAMPTOTAL,model.timestampTotal)
        contentValues.put(dbContract.ResTable.COLUMN_NAME_CREATEDAT_N,model.created_at.nanoseconds)
        contentValues.put(dbContract.ResTable.COLUMN_NAME_USERID,model.userid)
        contentValues.put(dbContract.ResTable.COLUMN_NAME_TIPESOAL,model.tipeSoal)
        contentValues.put(dbContract.ResTable.COLUMN_NAME_CREATEDAT_S,model.created_at.seconds)
        return db.insert(dbContract.ResTable.TABLE_NAME,null,contentValues)
    }

    fun truncate(){
        db.execSQL("DELETE FROM ${dbContract.ResTable.TABLE_NAME}")
    }
}