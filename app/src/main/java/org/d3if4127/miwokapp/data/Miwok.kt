package org.d3if4127.miwokapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "miwok_table")
data class Miwok (
    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "background")
    val background: String,

    @PrimaryKey
    @ColumnInfo(name = "defaultWord")
    val defaultWord: String,

    @ColumnInfo(name = "miwokWord")
    val miwokWord: String,

    @ColumnInfo(name = "image")
    val image: String?
)