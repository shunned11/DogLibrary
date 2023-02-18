package com.example.dogimagelibrary


import com.example.dogimagelibrary.network.DogApi
object DogImages {
    private var imageList= mutableListOf<String>()
    var currentIndex:Int=0
        private set


    suspend fun getImage():List<String>{
        imageList += (DogApi.retrofitService.getDogs(1).imgSrcUrl)
        currentIndex= imageList.lastIndex
        return listOf(imageList[currentIndex])
        val x=Math.abs(0)
     }

    suspend fun getImages(number: Int):List<String>{
        imageList+=DogApi.retrofitService.getDogs(number).imgSrcUrl
        currentIndex= imageList.lastIndex
        return imageList.subList(imageList.lastIndex-number+1, currentIndex+1)
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