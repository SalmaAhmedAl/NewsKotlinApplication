package com.example.newskotlinapplication.api.model.newsResponse

import com.google.gson.annotations.SerializedName

data class NewsResponse(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<NewsItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null,

	//error state
	@field:SerializedName("code")
	val code: String? = null,
	@field:SerializedName("message")
	val message: String? = null


)