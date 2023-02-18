package com.example.dogimages.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogimagelibrary.DogImages
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _property = MutableLiveData<List<String>>()
    val property: LiveData<List<String>> get() = _property

    init {
        getDogProperty()

    }

    fun getNext(){
        _property.value=listOf("Loading")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _property.postValue(DogImages.getNextImage())

            } catch (e: Exception) {
                _property.postValue(listOf("Error $e"))
                Log.i("AnushX",e.toString())
            }
        }
    }
    fun getPrev(){
        _property.value=DogImages.getPreviousImage()
    }
    private fun getDogProperties() {
        _property.value=listOf("Loading")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _property.postValue(DogImages.getImages(5))
            } catch (e: Exception) {
                _property.postValue(listOf("Error"))
            }
        }
    }
    private fun getDogProperty() {
        _property.value=listOf("Loading")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _property.postValue(DogImages.getImage())
            } catch (e: Exception) {
                _property.postValue(listOf("Error"))
            }
        }
    }
}

