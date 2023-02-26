package com.example.newskotlinapplication.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newskotlinapplication.R
import com.example.newskotlinapplication.databinding.ActivityMainBinding
import com.example.newskotlinapplication.ui.categoryDetails.CategoryDetailsFragment

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
             supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,CategoryDetailsFragment())
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