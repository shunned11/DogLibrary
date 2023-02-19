package com.example.dogimagelibrary

import com.example.dogimagelibrary.network.DogApi
import com.example.dogimagelibrary.network.DogProperty
import io.mockk.coEvery
import io.mockk.mockkObject
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assume.assumeTrue
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.JVM)
@RunWith(JUnit4::class)
class DogImagesTest {

    @Test
    fun `get image`()
    {
        val orignalIndex=DogImages.currentIndex
        mockkObject(DogApi)
        coEvery {
            DogApi.retrofitService.getDogs(any())
        } returns DogProperty(listOf("https://images.dog.ceo/breeds/chihuahua/n02085620_3681.jpg"),"success")

        val response = runBlocking {DogImages.getImage()}

        assertEquals(1,response.size)
        assertEquals(orignalIndex+1,DogImages.currentIndex)
    }

    @Test
    fun `get images`()
    {
        mockkObject(DogApi)
        val number=2
        val orignalIndex=DogImages.currentIndex
        coEvery {
            DogApi.retrofitService.getDogs(number)
        } returns DogProperty(listOf("https://images.dog.ceo/breeds/chihuahua/n02085620_3681.jpg","https://images.dog.ceo/breeds/chihuahua/n02085620_3681.jpg"),"success")

        val response = runBlocking {DogImages.getImages(number)}

        assertEquals(number,response.size)
        assertEquals(orignalIndex+number,DogImages.currentIndex)
    }

    @Test
    fun `get prev image`()
    {
        val orignalIndex=DogImages.currentIndex
        assumeTrue(orignalIndex>0)

        val response = runBlocking {DogImages.getPreviousImage()}
        assertEquals(1,response.size)
        assertEquals(orignalIndex-1,DogImages.currentIndex)
    }

    @Test
    fun `get next image when current index is last index`()
    {
        val orignalIndex=DogImages.currentIndex
        assumeTrue(orignalIndex==(DogImages.imageList.lastIndex))
        mockkObject(DogApi)
        coEvery {
            DogApi.retrofitService.getDogs(1)
        } returns DogProperty(listOf("https://images.dog.ceo/breeds/chihuahua/n02085620_3681.jpg"),"success")

        val response = runBlocking {DogImages.getNextImage()}

        assertEquals(1,response.size)
        assertEquals(orignalIndex+1,DogImages.currentIndex)
    }

    @Test
    fun `get next image when imagelist is empty`()
    {
        assumeTrue(DogImages.imageList.isEmpty())
        mockkObject(DogApi)
        coEvery {
            DogApi.retrofitService.getDogs(1)
        } returns DogProperty(listOf("https://images.dog.ceo/breeds/chihuahua/n02085620_3681.jpg"),"success")

        val response = runBlocking {DogImages.getNextImage()}

        assertEquals(1,response.size)
        assertEquals(0,DogImages.currentIndex)
    }

    @Test
    fun `get next image when imagelist is not empty and when current index is not last index`()
    {
        val orignalIndex=DogImages.currentIndex
        assumeTrue(DogImages.imageList.isNotEmpty() && orignalIndex!=DogImages.imageList.lastIndex)
        mockkObject(DogApi)
        coEvery {
            DogApi.retrofitService.getDogs(1)
        } returns DogProperty(listOf("https://images.dog.ceo/breeds/chihuahua/n02085620_3681.jpg"),"success")

        val response = runBlocking {DogImages.getNextImage()}

        assertEquals(1,response.size)
        assertEquals(orignalIndex+1,DogImages.currentIndex)
    }
}