package com.example.dogimagelibrary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.dogimagelibrary.network.DogApi
object DogImages {
    private var imageList= mutableListOf<String>()
    private var _currentIndex:Int = 0
    val currentIndex: Int get() = _currentIndex


    suspend fun getImage():List<String>{
        imageList += (DogApi.retrofitService.getDogs(1).imgSrcUrl)
        _currentIndex= imageList.lastIndex
        return listOf(imageList[currentIndex])
        val x=Math.abs(0)
     }

    suspend fun getImages(number: Int):List<String>{
        imageList+=DogApi.retrofitService.getDogs(number).imgSrcUrl
        _currentIndex= imageList.lastIndex
        return imageList.subList(imageList.lastIndex-number+1, currentIndex)
    }

    suspend fun getNextImage():List<String>{
        if(currentIndex==imageList.lastIndex || imageList.lastIndex==-1) {
            imageList += (DogApi.retrofitService.getDogs(1).imgSrcUrl)
            _currentIndex= imageList.lastIndex
        }
        else{
            _currentIndex++
        }
        return listOf(imageList[currentIndex])
    }

    fun getPreviousImage(): List<String> {
        if(currentIndex!=0)
            _currentIndex--
        return listOf(imageList[currentIndex])
    }
}