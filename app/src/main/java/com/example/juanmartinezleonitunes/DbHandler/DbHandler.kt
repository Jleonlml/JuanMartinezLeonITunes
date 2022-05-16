package com.example.juanmartinezleonitunes.DbHandler

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.juanmartinezleonitunes.Model.Song
import com.example.juanmartinezleonitunes.Model.SongResponse


class DBHandler  // creating a constructor for our database handler.
    (context: Context?) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    // below method is for creating a database by running a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TRACK_NAME + " TEXT,"
                + ARTIST_NAME + " TEXT,"
                + TRACK_PRICE + " REAL,"
                + ARTWORK160 + " TEXT"
                + PREVIEWURL + " TEXT)")

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query)
    }

    // this method is use to add new course to our sqlite database.
    fun addNewSong(
        artistName: String?,
        trackName: String?,
        trackPrice: String?,
        artworkUrl60: String?,
        previewUrl: String?
    ) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        val db = this.writableDatabase

        // on below line we are creating a
        // variable for content values.
        val values = ContentValues()

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(TRACK_NAME, trackName)
        values.put(ARTIST_NAME, artistName)
        values.put(TRACK_PRICE, trackPrice)
        values.put(ARTWORK160, artworkUrl60)
        values.put(PREVIEWURL, previewUrl)

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values)

        // at last we are closing our
        // database after adding database.
        db.close()
    }

//    fun getSongs(): SongResponse {
//        var songsList: List<Song> = List<Song>()
//        val db = this.readableDatabase
//        val c: Cursor = db.rawQuery("SELECT * FROM table ", null)
//        if (c.moveToFirst()) {
//            do {
//                // Passing values
//                val id: Int = c.getInt(0)
//                val trackName: String = c.getString(1)
//                val artistName: String = c.getString(2)
//                val trackPrice: String = c.getString(3)
//                val artworkUrl60: String = c.getString(4)
//                val previewUrl: String = c.getString(5)
//
//                var song = Song(trackName, artistName, trackPrice.toDouble(), artworkUrl60, previewUrl)
//                songsList += song
//            } while (c.moveToNext())
//
//        }
//        var songResponse: SongResponse = SongResponse(songsList)
//        c.close()
//        db.close()
//        return songResponse
//    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object {
        // creating a constant variables for our database.
        // below variable is for our database name.
        private const val DB_NAME = "itunesdb"

        // below int is our database version
        private const val DB_VERSION = 1

        // below variable is for our table name.
        private const val TABLE_NAME = "songs"

        // below variable is for our id column.
        private const val ID_COL = "id"

        // below variable is for our course name column
        private const val TRACK_NAME = "song name"

        // below variable id for our course duration column.
        private const val ARTIST_NAME = "artist name"

        // below variable for our course description column.
        private const val TRACK_PRICE = "0"

        // below variable is for our course tracks column.
        private const val ARTWORK160 = "art"

        private const val PREVIEWURL = "url"
    }
}