package com.example.newskotlinapplication.api.model.sourceResponse

import com.google.gson.annotations.SerializedName

 class SourcesResponse(

     @field:SerializedName("sources")
	val sources: List<SourceItem?>? = null,

     @field:SerializedName("status")
	val status: String? = null,

    //error state
     @field:SerializedName("code")
     val code: String? = null,
     @field:SerializedName("message")
     val message: String? = null
)