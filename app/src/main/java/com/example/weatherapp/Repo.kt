package com.example.weatherapp

class Repo(
    private val retrofitServices: RetrofitServices
) {
    suspend fun getWeatherDetail(city:String)=retrofitServices.get_weather_detail(city)
}