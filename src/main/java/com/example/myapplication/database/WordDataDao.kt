package com.example.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDataDao{
    @Insert
    fun insert(word:WordData)

    @Query("select * from word_table order by word desc")
    fun getAllWords(): LiveData<List<WordData>>


    @Query("delete from word_table")
    fun clear()


}