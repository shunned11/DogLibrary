package com.example.dogimagelibrary.network

import com.squareup.moshi.Json

data class DogProperty(
    @Json(name="message")
    val imgSrcUrl:List<String>,
    val status:String
)
