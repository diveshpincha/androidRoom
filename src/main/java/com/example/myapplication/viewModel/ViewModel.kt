package com.example.myapplication.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myapplication.database.WordData
import com.example.myapplication.database.WordDataDao
import kotlinx.coroutines.*


class WordViewModel(val database: WordDataDao , application: Application) : AndroidViewModel(Application()) {
    var words = database.getAllWords()
    private var viewModeljob = Job()

   // var wordstring:MutableList<String> = mutableListOf("hi")


    override fun onCleared() {
        super.onCleared()
        viewModeljob.cancel()
    }

    private val uiscope = CoroutineScope(Dispatchers.Main+viewModeljob)

    /*init{
        initializeNewWord()
    }*/

    fun onInsert( NewWord:WordData) {
        uiscope.launch {
            insertWord(NewWord)
            //updateWordList(words)
        }

    }

 /*   private fun updateWordList(words: LiveData<List<WordData>>) {
        for(words in words.value!!){
            wordstring.add(words.word)
        }

    }*/


     suspend fun insertWord(word : WordData) {
        return withContext(Dispatchers.IO){
            database.insert(word)
        }
    }

    fun onClear(){
        uiscope.launch {
            clearAll()
        }
    }

    suspend fun clearAll() {
        withContext(Dispatchers.IO){
            database.clear()
        }
    }

}