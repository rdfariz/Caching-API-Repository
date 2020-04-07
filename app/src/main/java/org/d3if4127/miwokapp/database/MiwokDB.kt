package org.d3if4127.miwokapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.d3if4127.miwokapp.data.Miwok

@Database(entities = [Miwok::class], version = 1, exportSchema = false)
abstract class MiwokDB : RoomDatabase() {

    abstract val miwokDAO: MiwokDAO

    companion object {
        @Volatile
        private var INSTANCE: MiwokDB? = null

        fun getInstance(context: Context): MiwokDB {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MiwokDB::class.java,
                        "miwok_table"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}