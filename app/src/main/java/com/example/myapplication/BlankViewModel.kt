package com.example.myapplication

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BlankViewModel(application: Application) : AndroidViewModel(application) {
    private val _stringList = MutableLiveData<ArrayList<String>>()

    val stringList: LiveData<ArrayList<String>> = _stringList

    init {
        _stringList.value = arrayListOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
    }

    fun viewed(position: Int) {
        val toastMessage = "Item at position $position is observed!!"
        Log.w("D", toastMessage)
    }

}