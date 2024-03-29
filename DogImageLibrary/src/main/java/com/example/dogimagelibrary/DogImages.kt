package com.example.dogimagelibrary


import com.example.dogimagelibrary.network.DogApi
object DogImages {


    internal var imageList= mutableListOf<String>()
    var currentIndex:Int=-1
        private set


    suspend fun getImage():List<String>{
        imageList += (DogApi.retrofitService.getDogs(1).imgSrcUrl)
        currentIndex= imageList.lastIndex
        return listOf(imageList[currentIndex])
     }

    suspend fun getImages(number: Int):List<String>{
        val result=DogApi.retrofitService.getDogs(number).imgSrcUrl
        imageList+=result
        currentIndex= imageList.lastIndex
        return result
    }

    suspend fun getNextImage():List<String>{
        if(currentIndex==imageList.lastIndex || imageList.lastIndex==-1) {
            imageList += (DogApi.retrofitService.getDogs(1).imgSrcUrl)
            currentIndex= imageList.lastIndex
        }
        else{
            currentIndex++
        }
        return listOf(imageList[currentIndex])
    }

    fun getPreviousImage(): List<String> {
        if(currentIndex!=0)
            currentIndex--
        return listOf(imageList[currentIndex])
    }
}