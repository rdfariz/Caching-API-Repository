package org.d3if4127.miwokapp.database

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.d3if4127.miwokapp.network.MiwokApi

class MiwokRepository (private val miwokDAO: MiwokDAO) {
    val miwok = miwokDAO.getAllMiwok()

    suspend fun refreshMiwok() {
        withContext(Dispatchers.IO) {
            val miwok = MiwokApi.retrofitService.getData()
            Log.i("dataMiwokRepo", miwok.toString())
            miwokDAO.insertAll(miwok)
        }
    }
}