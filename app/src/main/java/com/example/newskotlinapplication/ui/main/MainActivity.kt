package com.example.newskotlinapplication.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.newskotlinapplication.R
import com.example.newskotlinapplication.api.ApiManager
import com.example.newskotlinapplication.api.model.SourcesResponse
import com.example.newskotlinapplication.databinding.ActivityMainBinding
import com.example.newskotlinapplication.databinding.FragmentCategoryBinding
import com.example.newskotlinapplication.ui.category.CategoryFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val apiKey ="bb67de293274417ca19e18a19e64478c"
    lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
             supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,CategoryFragment())
            .commit()
//        ApiManager.getApis().getSources(apiKey)
//            .enqueue(object :Callback<SourcesResponse>{
//                override fun onResponse(
//                    call: Call<SourcesResponse>,
//                    response: Response<SourcesResponse>
//                ) {
//                    Log.e("response", response.body().toString())
//                    Log.e("response", response.code().toString())
//                    Log.e("response", response.isSuccessful().toString())
//                    Log.e("response", response.errorBody()?.string()?:"")
//                }
//
//                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
//                    Log.e("error", t.localizedMessage?:"")
//                }
//
//            })
    }
}