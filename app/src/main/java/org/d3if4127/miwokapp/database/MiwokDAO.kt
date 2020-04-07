package org.d3if4127.miwokapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import org.d3if4127.miwokapp.data.Miwok

@Dao
interface MiwokDAO {
    @Query("SELECT * FROM miwok_table")
    fun getAllMiwok(): LiveData<List<Miwok>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(miwok: List<Miwok>)
}