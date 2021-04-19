package com.example.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [WordData::class],version = 1,exportSchema = false)
abstract class wordDatabase:RoomDatabase() {
        abstract val databaseDao : WordDataDao

        companion object{
            @Volatile
            private var INSTANCE:wordDatabase?=null

            fun getInstance(context: Context):wordDatabase{
                synchronized(this){
                    var instance = INSTANCE
                    if(instance==null){
                        instance= Room.databaseBuilder(context.applicationContext,wordDatabase::class.java,"word_database")
                            .fallbackToDestructiveMigration()
                            .build()
                        INSTANCE=instance
                    }
                    return instance
                }

            }

        }
}