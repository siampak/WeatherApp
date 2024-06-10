package com.example.weatherapp

import android.content.pm.PackageManager
import android.Manifest
import android.os.Bundle
import android.location.Location
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.FragmentWeatherPlaceBinding
import com.example.weatherapp.models.CurrentLocation
import com.example.weatherapp.models.ListA
import com.example.weatherapp.network.RetrofitInstance
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherPlaceFragment : Fragment() {
    private lateinit var binding:FragmentWeatherPlaceBinding
    private lateinit var weatherAdapter: AdapterWeatherList
    private lateinit var fusedLocationClient: FusedLocationProviderClient

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
        weatherAdapter = AdapterWeatherList(emptyList()){ item ->
            adapterOnClick(item)
        }
        binding.rvWeatherPlaceList.adapter = weatherAdapter

      //For Location Detect;  fetchWeatherData(like "Dhaka")
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        //permission message show(check location)(1)
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request location permissions
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
            return
        }

        getUserLocationAndFetchWeather()
    }

    private fun getUserLocationAndFetchWeather() {
        Log.d("WeatherPlaceFragment", "Location: getUserLocationAndFetchWeather ")

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                Log.d("WeatherPlaceFragment", "Location: ${location.toString()} ")

                if (location != null) {
                    Log.d("WeatherPlaceFragment", "Location: ${location.latitude}, ${location.longitude}")


                    fetchWeatherData(location.latitude, location.longitude)
                }
            }
            .addOnFailureListener { e ->

                Log.d("WeatherPlaceFragment", "Location: getUserLocationAndFetchWeather : addOnFailureListener $e")

            }
    }

    private fun fetchWeatherData(latitude: Double, longitude: Double) {
        val apiKey = "e384f9ac095b2109c751d95296f8ea76" // Replace with your actual API key
        RetrofitInstance.apiService.getCurrentWeather(latitude,longitude, cnt = 50, apiKey)
            .enqueue(object : Callback<CurrentLocation> {
                override fun onResponse(
                    call: Call<CurrentLocation>,
                    response: Response<CurrentLocation>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { currentLocation ->
//                            val weatherList = currentLocation.list ?: emptyList()
                            weatherAdapter.updateData(currentLocation.list)
                            binding.rvWeatherPlaceList.adapter = weatherAdapter
                        }
                    }
                }

                override fun onFailure(call: Call<CurrentLocation>, t: Throwable) {

                }
            })
    }

    fun adapterOnClick(item:ListA){
        val action = WeatherPlaceFragmentDirections.actionWeatherPlaceFragmentToWeatherMapsFragment(item)
        findNavController().navigate(action)
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

//    private fun navigatingToWeatherMapFragment() {
//
//        binding.toolbarTitle.setOnClickListener{
//            findNavController().navigate(R.id.action_weatherPlaceFragment_to_weatherMapsFragment)
//        }
//    }
}

//    private fun fetchWeatherData(location: String) {
//        val apiKey = "e384f9ac095b2109c751d95296f8ea76" // Replace with your actual API key
//        RetrofitInstance.apiService.getCurrentWeather(location, apiKey)
//            .enqueue(object : Callback<CurrentLocation> {
//                override fun onResponse(
//                    call: Call<CurrentLocation>,
//                    response: Response<CurrentLocation>
//                ) {
//                    if (response.isSuccessful) {
//                        response.body()?.let { currentLocation ->
//                            Log.d("WeatherPlaceFragment", "Weather data retrieved: ${currentLocation.list}")
//                            weatherAdapter.updateData(currentLocation.list)
//
//                            binding.rvWeatherPlaceList.adapter = weatherAdapter
//                        }
//                    }else {
//                        Log.e("WeatherPlaceFragment", "Failed to retrieve weather data: ${response.errorBody()}")
//                    }
//                }
//
//                override fun onFailure(call: Call<CurrentLocation>, t: Throwable) {
//
//                    Log.e("WeatherPlaceFragment", "Error fetching weather data", t)
//                }
//            })
//    }
