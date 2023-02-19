package com.example.dogimagelibrary

import com.example.dogimagelibrary.network.DogApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class DogApiServiceTest {
    private var mockWebServer = MockWebServer()
    private lateinit var apiService: DogApiService
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


    @Before
    fun setUp() {

        mockWebServer.start()

        apiService = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(mockWebServer.url("/")) // note the URL is different from production one
            .build()
            .create(DogApiService::class.java)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testDogApiService(){
        runBlocking {
            mockWebServer.enqueue((MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody("""{"message":["https:\/\/images.dog.ceo\/breeds\/chihuahua\/n02085620_3681.jpg","https:\/\/images.dog.ceo\/breeds\/spaniel-brittany\/n02101388_2888.jpg"],"status":"success"}""")))

            val response = apiService.getDogs(2)

            assertNotNull(response)
            assertEquals(2, response.imgSrcUrl.size)
            assertEquals("success", response.status)
            for(i in 0 until response.imgSrcUrl.size) {
                assertTrue(response.imgSrcUrl[i].startsWith("https://images.dog.ceo/breeds"))
                assertTrue(response.imgSrcUrl[i].endsWith(".jpg"))
            }
        }
    }
}
