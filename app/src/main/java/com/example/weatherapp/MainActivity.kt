package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var rpo:Repo
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var WeatherViewModelFactory:WeatherViewModelFactory

    private lateinit var loader:ProgressBar
    private lateinit var condition_icon:ImageView
    private lateinit var cityName:EditText
    private lateinit var btn_search:ImageView
    private lateinit var condition:TextView
    private lateinit var place:TextView
    private lateinit var time:TextView
    private lateinit var temp:TextView
    private lateinit var wind:TextView
    private lateinit var humid:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        weatherViewModel.get_weather_detail("Dhanbad")

        btn_search.setOnClickListener {
            val ctyName=cityName.text.toString()
            weatherViewModel.get_weather_detail(ctyName)
        }
        weatherViewModel.weather_detail_live_data.observe(this){
//            Log.i("WeatherDetail",it.toString())
                val imageUrl="https:"+it.current.condition.icon
                Glide.with(this)
                    .load(imageUrl)
                    .into(condition_icon)

                condition.text=it.current.condition.text.toString()
                place.text=(it.location.name.toString())+", "+(it.location.region.toString())+", "+(it.location.country)
                time.text=it.location.localtime.toString()
                temp.text=it.current.temp_c.toString()+"°"
                wind.text=it.current.wind_kph.toString()
                humid.text=it.current.humidity.toString()+"%"
                weatherViewModel.isLoading.observe(this){
                    if(it){
                        loader.visibility= View.VISIBLE
                    }
                    else{
                        loader.visibility=View.GONE
                    }
                }
        }






    }

    private fun init(){
        rpo=Repo(RetrofitBuilder.getInstance())
        WeatherViewModelFactory= WeatherViewModelFactory(rpo)
        weatherViewModel=ViewModelProvider(this,WeatherViewModelFactory).get(WeatherViewModel::class.java)

        loader=findViewById(R.id.loader)
        condition_icon=findViewById(R.id.condition_icon)
        cityName=findViewById(R.id.city)
        btn_search=findViewById(R.id.search)
        condition=findViewById(R.id.condition)
        place=findViewById(R.id.place)
        time=findViewById(R.id.time)
        temp=findViewById(R.id.temp)
        wind=findViewById(R.id.wind)
        humid=findViewById(R.id.humid)
    }
}