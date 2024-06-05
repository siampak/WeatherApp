package com.example.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.AdapterWeatherList
import com.example.weatherapp.databinding.FragmentWeatherPlaceBinding
import com.example.weatherapp.models.CurrentLocation
import com.example.weatherapp.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherPlaceFragment : Fragment() {
    private lateinit var binding:FragmentWeatherPlaceBinding
    private lateinit var weatherAdapter: AdapterWeatherList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherPlaceBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvWeatherPlaceList.layoutManager = LinearLayoutManager(requireContext())
        weatherAdapter = AdapterWeatherList(emptyList())
        binding.rvWeatherPlaceList.adapter = weatherAdapter

        fetchWeatherData("Dhaka")
    }

    private fun fetchWeatherData(location: String) {
        val apiKey = "e384f9ac095b2109c751d95296f8ea76" // Replace with your actual API key
        RetrofitInstance.apiService.getCurrentWeather(location, apiKey)
            .enqueue(object : Callback<CurrentLocation> {
                override fun onResponse(
                    call: Call<CurrentLocation>,
                    response: Response<CurrentLocation>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { currentLocation ->
                            weatherAdapter = AdapterWeatherList(currentLocation.list)
                            binding.rvWeatherPlaceList.adapter = weatherAdapter
                        }
                    }
                }

                override fun onFailure(call: Call<CurrentLocation>, t: Throwable) {
                    // Handle the error
                }
            })
    }


}