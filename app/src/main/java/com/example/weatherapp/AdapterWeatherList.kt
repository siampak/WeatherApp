package com.example.weatherapp


import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.WeatherPlaceItemBinding

import com.example.weatherapp.models.ListA

class AdapterWeatherList(private val weatherList: List<ListA>):RecyclerView.Adapter<AdapterWeatherList.WeatherViewHolder>() {
        class WeatherViewHolder(val binding: WeatherPlaceItemBinding) :
                RecyclerView.ViewHolder(binding.root)


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
                return WeatherViewHolder(
                        WeatherPlaceItemBinding.inflate(
                                LayoutInflater.from(parent.context),
                                parent,
                                false
                        )


                )

        }

        override fun getItemCount(): Int {

                return weatherList.size
        }

        override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {

                val weatherItem = weatherList[position]
                holder.binding.apply {
                        tvWeatherLocation.text = weatherItem.name
                        tvWeatherStatus.text = weatherItem.weather[0].description
                        tvProductCount.text ="${weatherItem.main?.temp}°C"
                }

        }

}




