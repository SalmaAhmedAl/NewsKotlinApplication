package com.example.newskotlinapplication.api

import com.example.newskotlinapplication.api.model.newsResponse.NewsResponse
import com.example.newskotlinapplication.api.model.sourceResponse.SourcesResponse
import com.example.newskotlinapplication.ui.categories.Category
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET("/v2/top-headlines/sources")
    fun getSources (@Query("apiKey") apiKey:String, @Query("category")category: String):Call<SourcesResponse>

    @GET ("/v2/everything")
    fun getNews(@Query("apiKey") apiKey:String, @Query("sources") source:String):Call<NewsResponse>
}