package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.database.WordData
import com.example.myapplication.database.wordDatabase
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.viewModel.WordViewModel
import com.example.myapplication.viewModel.WordViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        val application= requireNotNull(this).application

        val datasource=wordDatabase.getInstance(application).databaseDao

        val viewModelFactory=WordViewModelFactory(datasource,application)

        val wordViewmodel=ViewModelProvider(this,viewModelFactory).get(WordViewModel::class.java)


      //  wordViewmodel.words.forEach() {  newString.add(it.word) }
       // val newString1 = newString.joinToString("\n")
/*
        val newString :MutableList<String> = mutableListOf("hello")
        wordViewmodel.words.observe(this, Observer {
            it.forEach(){ it ->
                newString.add(it.word)
            }
            val newString1=newString.joinToString { "/n" }
            binding.textView.text= newString.toString()
        })


*/
        binding.insert.setOnClickListener{
            val newWord = WordData(enterword.text.toString())
            wordViewmodel.onInsert(newWord)
            enterword.setText("")
        }


        wordViewmodel.words.observe(this, Observer {
                words ->
            val newString :MutableList<String> = mutableListOf("")
            words.forEach() {  newString.add(it.word) }
            val newString1 = newString.joinToString("\n")
            textView.text=newString1
        })

        binding.setLifecycleOwner(this)

        binding.wordViewModel=wordViewmodel




    }
}