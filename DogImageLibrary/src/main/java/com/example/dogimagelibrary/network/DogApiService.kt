package com.example.dogimagelibrary.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()



private const val BASE_URL = "https://dog.ceo/"
private val retrofit=Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

internal interface DogApiService{
    @GET("api/breeds/image/random/{number}")
    suspend fun getDogs(@Path("number") number:Int): DogProperty
}

internal object DogApi{
    val retrofitService:DogApiService by lazy{
        retrofit.create(DogApiService::class.java)
    }
}