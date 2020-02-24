package feri.com.mathquizz.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class dbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Companion.SQL_CREATE_JAWABAN)
        db.execSQL(Companion.SQL_CREATE_RES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(Companion.SQL_DELETE_JAWABAN)
        db.execSQL(Companion.SQL_DELETE_RES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 5
        const val DATABASE_NAME = "MathQuizz"
        private const val SQL_CREATE_RES =
            "CREATE TABLE IF NOT EXISTS ${dbContract.ResTable.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${dbContract.ResTable.COLUMN_NAME_ID} TEXT," +
                    "${dbContract.ResTable.COLUMN_NAME_CREATEDAT_S} INTEGER," +
                    "${dbContract.ResTable.COLUMN_NAME_CREATEDAT_N} INTEGER," +
                    "${dbContract.ResTable.COLUMN_NAME_TIPESOAL} INTEGER," +
                    "${dbContract.ResTable.COLUMN_NAME_USERID} TEXT," +
                    "${dbContract.ResTable.COLUMN_NAME_TIMESTAMPTOTAL} INTEGER)"
        private const val SQL_DELETE_RES = "DROP TABLE IF EXISTS ${dbContract.ResTable.TABLE_NAME}"
        private const val SQL_CREATE_JAWABAN =
            "CREATE TABLE IF NOT EXISTS ${dbContract.JawModel.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${dbContract.JawModel.COLUMN_NAME_IDRESULT} TEXT," +
                    "${dbContract.JawModel.COLUMN_NAME_IDSOal} TEXT," +
                    "${dbContract.JawModel.COLUMN_NAME_JAWABAN} INTEGER," +
                    "${dbContract.JawModel.COLUMN_NAME_TIMESTAMP} INTEGER)"
        private const val SQL_DELETE_JAWABAN = "DROP TABLE IF EXISTS ${dbContract.JawModel.TABLE_NAME}"
    }
}