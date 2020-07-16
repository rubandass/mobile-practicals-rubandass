package com.jhonr1.location.helpers

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DATABASE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insert(playlistName: String, playlistCategory: String, playlistDescription: String): Long {
        val db: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, playlistName)
        values.put(COLUMN_CATEGORY, playlistCategory)
        values.put(COLUMN_DESCRIPTION, playlistDescription)
        values.put(COLUMN_DATE_TIME, DateTime.currentDateTime())
        val id: Long = db.insert(TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun selectAll(): ArrayList<Playlist>{
        val playlists = ArrayList<Playlist>()
        val selectQuery = "SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_DATE_TIME ASC"
        val db: SQLiteDatabase = this.writableDatabase
        val cursor: Cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()){
            do{
                val playlist = Playlist()
                playlist.id = cursor.getInt((cursor.getColumnIndex(COLUMN_ID)))
                playlist.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                playlist.category = cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY))
                playlist.description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))
                playlist.dateTime = cursor.getString(cursor.getColumnIndex(COLUMN_DATE_TIME))
                playlists.add(playlist)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return playlists
    }

    fun update(id: Long, playlistName: String, playlistCategory: String, playlistDescription: String): Int {
        val db:SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, playlistName)
        values.put(COLUMN_CATEGORY, playlistCategory)
        values.put(COLUMN_DESCRIPTION, playlistDescription)
        return db.update(
            TABLE_NAME, values, "$COLUMN_ID = ?",
            arrayOf(id.toString())
        )
    }

    fun delete(id: Long){
        val db: SQLiteDatabase = this.writableDatabase
        db.delete(
            TABLE_NAME, "$COLUMN_ID = ?",
            arrayOf(id.toString())
        )
        db.close()
    }

    companion object{
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "db_playlist"
        const val TABLE_NAME = "playlists"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_CATEGORY = "category"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_DATE_TIME = "date_time"
        const val DATABASE_CREATE: String =
            "CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NAME TEXT, " +
                    "$COLUMN_CATEGORY TEXT, $COLUMN_DESCRIPTION TEXT, $COLUMN_DATE_TIME TEXT)"
    }

    object DateTime{
        fun currentDateTime(): String {
            val date: Date = Calendar.getInstance().time
            val outputPattern = "dd/MM/yyyy kk:mm:ss"
            val outputFormat = SimpleDateFormat(outputPattern, Locale.ENGLISH)
            return  outputFormat.format(date)
        }

        fun formatDateTime(dateTime: String): String {
            val inputPattern = "dd/MM/yyyy kk:mm:ss"
            val outputPattern = "dd MMM yyyy hh:mm:ss a"

            val inputFormat = SimpleDateFormat(inputPattern, Locale.ENGLISH)
            val outputFormat = SimpleDateFormat(outputPattern, Locale.ENGLISH)

            lateinit var date: Date
            lateinit var str: String
            try {
                date = inputFormat.parse(dateTime)
                str = outputFormat.format(date)
            } catch (e: ParseException){
                e.printStackTrace()
            }
            return str
        }
    }
}
