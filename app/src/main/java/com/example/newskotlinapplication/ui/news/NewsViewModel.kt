package com.example.newskotlinapplication.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newskotlinapplication.api.ApiConstants
import com.example.newskotlinapplication.api.ApiManager
import com.example.newskotlinapplication.api.model.newsResponse.NewsItem
import com.example.newskotlinapplication.api.model.newsResponse.NewsResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel:ViewModel() {
    val showLoading =MutableLiveData<Boolean>()
    val showError =MutableLiveData<String>()
    val newsList = MutableLiveData<List<NewsItem?>?>()
     fun getNew(sourceId:String, pageSize:Int, page:Int) {
        //showLoadingLayout()
         showLoading.value=true
        ApiManager.getApis()
            .getNews(ApiConstants.apiKey,sourceId,pageSize=pageSize,page=page)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if(response.isSuccessful){
                        //We have news to show
                       // bindNewsList(response.body()?.articles)

                        newsList.value=response.body()?.articles
                        return
                    }

                    val errorMessage = Gson().fromJson(response.errorBody()?.string(), NewsResponse::class.java)
                    showError.value= errorMessage.message?:""
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    showError.value=t.localizedMessage
                }

            })

    }
}