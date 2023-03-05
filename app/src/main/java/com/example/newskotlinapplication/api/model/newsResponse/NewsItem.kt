package com.example.newskotlinapplication.api.model.newsResponse

import com.example.newskotlinapplication.api.model.sourceResponse.SourceItem
import com.google.gson.annotations.SerializedName

data class NewsItem(

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("urlToImage")
	val urlToImage: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("source")
	val source: SourceItem? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("content")
	val content: String? = null
):java.io.Serializable