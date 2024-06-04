package com.example.weatherapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.WeatherPlaceItemBinding

class AdapterWeatherList(val context: Context):RecyclerView.Adapter<AdapterWeatherList.WeatherViewHolder>() {
        class WeatherViewHolder(val binding: WeatherPlaceItemBinding) :
                RecyclerView.ViewHolder(binding.root)

        val diffUtil = object : DiffUtil.ItemCallback<>() {
                //running..

        }
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

                TODO("Not yet implemented")
        }

        override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {

                TODO("Not yet implemented")
        }

}




