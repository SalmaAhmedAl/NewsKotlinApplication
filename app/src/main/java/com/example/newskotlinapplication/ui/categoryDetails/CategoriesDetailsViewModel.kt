package com.example.newskotlinapplication.ui.categoryDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newskotlinapplication.api.ApiConstants
import com.example.newskotlinapplication.api.ApiManager
import com.example.newskotlinapplication.api.model.sourceResponse.SourceItem
import com.example.newskotlinapplication.api.model.sourceResponse.SourcesResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesDetailsViewModel:ViewModel() {

    var sourcesLiveData = MutableLiveData<List<SourceItem?>?>()
    val showLoadingLayout = MutableLiveData<Boolean>()
    val showErrorLayout = MutableLiveData<String>()
     fun loadNewsSources(categoryId:String) {
        //showLoadingLayout()

        showLoadingLayout.value=true
        ApiManager.getApis().getSources(ApiConstants.apiKey, categoryId)
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    //  viewBinding.loddingIndector.isVisible=false
                    showLoadingLayout.value=false
                    if(response.isSuccessful) {
                       // bindingSourcesInTabLayout(response.body()?.sources)

                        sourcesLiveData.value=response.body()?.sources
                        //or
                       // sourcesLiveData.postValue(response.body()?.sources)  >>>when I dont work in the main thread
                    }else{
                        //we need to create object Gson because error body return string(JSON file) and I need it to return
                        //SourceItem object>>> SoOo I'll use Gson
                        val gson = Gson()
                        val errorResponse=  gson.fromJson(response.errorBody()?.string(), SourcesResponse::class.java)
                       // showErrorMessage(errorResponse.message)

                        showErrorLayout.value=errorResponse.message?: " "

                    }
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    //  viewBinding.loddingIndector.isVisible=false
                   // showErrorMessage(t.localizedMessage)

                    showLoadingLayout.value=false
                    showErrorLayout.value= t.localizedMessage?:" "
                }

            })
    }
}