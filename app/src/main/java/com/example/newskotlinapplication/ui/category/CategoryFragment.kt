package com.example.newskotlinapplication.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.newskotlinapplication.api.ApiConstants
import com.example.newskotlinapplication.api.ApiManager
import com.example.newskotlinapplication.api.model.SourcesResponse
import com.example.newskotlinapplication.databinding.FragmentCategoryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryFragment :Fragment() {
    lateinit var viewBinding : FragmentCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentCategoryBinding.inflate(inflater, container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadNewsSources()
    }

    private fun loadNewsSources() {
        ApiManager.getApis().getSources(ApiConstants.apiKey)
            .enqueue(object :Callback<SourcesResponse>{
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    viewBinding.loddingIndector.isVisible=false
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    viewBinding.loddingIndector.isVisible=false
                }

            })
    }
}