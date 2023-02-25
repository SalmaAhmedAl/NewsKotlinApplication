package com.example.newskotlinapplication.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.newskotlinapplication.api.ApiConstants
import com.example.newskotlinapplication.api.ApiManager
import com.example.newskotlinapplication.api.model.newsResponse.NewsItem
import com.example.newskotlinapplication.api.model.newsResponse.NewsResponse
import com.example.newskotlinapplication.api.model.sourceResponse.SourceItem
import com.example.newskotlinapplication.databinding.FragmentNewsBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment:Fragment() {
    companion object{
        fun getInstance(source: SourceItem):NewsFragment{
             val newFragment= NewsFragment()
            newFragment.source = source
            return newFragment
        }
    }
    lateinit var source : SourceItem
    lateinit var viewBinding : FragmentNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentNewsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerNews()
        getNew()
    }
    val newsAdapter =NewsAdapter(null)

    private fun initRecyclerNews() {
    viewBinding.recyclerNews.adapter=newsAdapter
    }

    private fun getNew() {
        showLoadingLayout()
        ApiManager.getApis()
            .getNews(ApiConstants.apiKey,source.id?:"")
            .enqueue(object :Callback<NewsResponse>{
                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                   if(response.isSuccessful){
                       //We have news to show
                       bindNewsList(response.body()?.articles)
                        return
                   }

                    val errorMessage = Gson().fromJson(response.errorBody()?.string(), NewsResponse::class.java)
                    showErrorMessage(errorMessage.message)
                }

                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                   showErrorMessage(t.localizedMessage)
                }

            })

    }

    private fun bindNewsList(articles: List<NewsItem?>?) {
     //show news in Recycler view
        with(viewBinding) {
            loddingIndector.isVisible=false
            errorLayout.isVisible=false
        }
        newsAdapter.changeData(articles)
    }

    private fun showErrorMessage(message: String?) {
        with(viewBinding) {
            loddingIndector.isVisible=false
            errorLayout.isVisible=true
            errorMessage.text = message
        }
    }

    private fun showLoadingLayout() {
        with(viewBinding) {
            loddingIndector.isVisible=true
            errorLayout.isVisible=false
        }
    }
}