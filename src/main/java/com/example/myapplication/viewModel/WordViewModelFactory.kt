package com.example.myapplication.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.database.WordDataDao

class WordViewModelFactory(private val dataSource :WordDataDao, private val application: Application):ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(WordViewModel::class.java))  {
            return WordViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("unknown view model")
    }

}