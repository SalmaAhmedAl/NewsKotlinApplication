package com.example.newskotlinapplication.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiManager {
    companion object{
       private var retrofit:Retrofit ?=null
       @Synchronized
       private fun getInstance():Retrofit{
            if(retrofit==null){
//                val loggingInterceptor= HttpLoggingInterceptor(
//                    HttpLoggingInterceptor.Logger {
//                        Log.e("apiTestForLearning", it)
//                    }
//                )
//                loggingInterceptor.level= HttpLoggingInterceptor.Level.BODY
//
//                val okHttpClient =OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
                retrofit=Retrofit.Builder().baseUrl("https://newsapi.org")
                   // .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
        //ال api هو اللي بيملى ال interface>>> و getInstance هو اللي بيبني اوبجيكت من Retrofit
        fun getApis():WebServices{
            return getInstance().create(WebServices::class.java)
        }
    }
}