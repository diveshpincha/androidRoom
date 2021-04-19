package com.example.myapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
data class WordData(
    @PrimaryKey @ColumnInfo(name="word")
    val word:String=""

)