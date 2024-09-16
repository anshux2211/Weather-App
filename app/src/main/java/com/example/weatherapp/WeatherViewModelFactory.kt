package com.example.weatherapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WeatherViewModelFactory(
    private val rpo:Repo
) :ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherViewModel(rpo) as T
    }
}