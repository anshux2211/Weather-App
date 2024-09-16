package com.example.weatherapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.datamodel.WeatherDataClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(private val rpo:Repo):ViewModel() {

    val weather_detail_live_data=MutableLiveData<WeatherDataClass>()
    val isLoading=MutableLiveData<Boolean>(false)

    fun get_weather_detail(city:String){
        viewModelScope.launch(Dispatchers.IO){
            isLoading.postValue(true)
            val response=rpo.getWeatherDetail(city)
            if(response.isSuccessful){
                weather_detail_live_data.postValue(response.body())
                isLoading.postValue(false)
            }
        }
    }
}