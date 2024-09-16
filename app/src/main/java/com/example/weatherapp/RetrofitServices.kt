package com.example.weatherapp

import com.example.weatherapp.datamodel.WeatherDataClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {

    @GET("/v1/current.json?key=9660706a546e4591892200151241309")
    suspend fun get_weather_detail(
        @Query("q") city:String
    ):Response<WeatherDataClass>
}