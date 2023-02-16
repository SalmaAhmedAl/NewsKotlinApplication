package com.example.newskotlinapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.newskotlinapplication.api.ApiManager
import com.example.newskotlinapplication.api.model.SourcesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val apiKey ="bb67de293274417ca19e18a19e64478c"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ApiManager.getApis().getSources(apiKey)
            .enqueue(object :Callback<SourcesResponse>{
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                    Log.e("response", response.body().toString())
                    Log.e("response", response.code().toString())
                    Log.e("response", response.isSuccessful().toString())
                    Log.e("response", response.errorBody()?.string()?:"")
                }

                override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                    Log.e("error", t.localizedMessage?:"")
                }

            })
    }
}