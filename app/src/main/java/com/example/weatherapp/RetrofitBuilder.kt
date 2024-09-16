package com.example.weatherapp

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    companion object{
        var retrofitServices:RetrofitServices?=null
        fun getInstance():RetrofitServices{
            if(retrofitServices==null){
                retrofitServices= Retrofit.Builder()
                    .baseUrl("https://api.weatherapi.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
                    .create(RetrofitServices::class.java)
            }
            return retrofitServices!!
        }
    }
}