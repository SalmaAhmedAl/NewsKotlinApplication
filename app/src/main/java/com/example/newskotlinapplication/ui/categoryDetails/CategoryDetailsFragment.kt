package com.example.newskotlinapplication.ui.categoryDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.newskotlinapplication.R
import com.example.newskotlinapplication.api.ApiConstants
import com.example.newskotlinapplication.api.ApiManager
import com.example.newskotlinapplication.api.model.sourceResponse.SourceItem
import com.example.newskotlinapplication.api.model.sourceResponse.SourcesResponse
import com.example.newskotlinapplication.databinding.FragmentDetailsCategoryBinding
import com.example.newskotlinapplication.ui.news.NewsFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryDetailsFragment :Fragment() {
    lateinit var viewBinding : FragmentDetailsCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentDetailsCategoryBinding.inflate(inflater, container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadNewsSources()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, NewsFragment())

        viewBinding.tryAgain.setOnClickListener{
            loadNewsSources()
        }
    }
    private fun changeNewsFragment(source: SourceItem){
        loadNewsSources()
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_n, NewsFragment.getInstance(source))
            .commit()
    }
    private fun loadNewsSources() {
        showLoadingLayout()
        ApiManager.getApis().getSources(ApiConstants.apiKey)
            .enqueue(object :Callback<SourcesResponse>{
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                  //  viewBinding.loddingIndector.isVisible=false
                    if(response.isSuccessful) {
                        bindingSourcesInTabLayout(response.body()?.sources)
                    }else{
                        //we need to create object Gson because error body return string(JSON file) and I need it to return
                        //SourceItem object>>> SoOo I'll use Gson
                        val gson = Gson()
                        val errorResponse=  gson.fromJson(response.errorBody()?.string(), SourcesResponse::class.java)
                        showErrorMessage(errorResponse.message)

                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                  //  viewBinding.loddingIndector.isVisible=false
                    showErrorMessage(t.localizedMessage)
                }

            })
    }

    private fun showErrorMessage(message: String?) {
        with(viewBinding) {
           // loddingIndector.isVisible=false
            errorLayout.isVisible=true
            errorMessage.text = message
        }
    }

    private fun showLoadingLayout() {
        with(viewBinding) {
            //loddingIndector.isVisible=true
            errorLayout.isVisible=false
        }
    }

    fun bindingSourcesInTabLayout(sourcesList: List<SourceItem?>?){
        sourcesList?.forEach { source->
               val tab= viewBinding.tabLayout.newTab()
            tab.text= source?.name
            tab.tag= source
            viewBinding.tabLayout.addTab(tab)

        }
        viewBinding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source=  tab?.tag as SourceItem
                changeNewsFragment(source)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source=  tab?.tag as SourceItem
                changeNewsFragment(source)            }

        })

        //viewBinding.tabLayout.getTabAt(0)?.select()
    }
}

