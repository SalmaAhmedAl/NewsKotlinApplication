package com.example.newskotlinapplication.api

import com.example.newskotlinapplication.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("/v2/top-headlines/sources")
    fun getSources (@Query("apiKey") apiKey:String):Call<SourcesResponse>
}