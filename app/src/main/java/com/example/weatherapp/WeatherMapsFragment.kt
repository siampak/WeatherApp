package com.example.weatherapp

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.weatherapp.databinding.FragmentWeatherMapsBinding
import com.example.weatherapp.models.ListA

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class WeatherMapsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentWeatherMapsBinding
    private val args: WeatherMapsFragmentArgs by navArgs()
    private lateinit var map: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)


        val clickedLocationData=args.ListA
        // binding position onujai weather set korbo.
        binding.tvLocation.text = clickedLocationData.name
        binding.tvWeatherStatus.text = clickedLocationData.weather.toString()
        binding.tvHumidity.text = "Humidity: ${clickedLocationData.main.humidity}%".toString()
        binding.tvWindSpeed.text = "Wind Speed: ${clickedLocationData.wind} m/s".toString()
        binding.tvMaxTemp.text = "Max Temp: ${clickedLocationData.main.temp_max}°C"
        binding.tvMinTemp.text = "Min Temp: ${clickedLocationData.main.temp_min}°C"
        binding.tvTemp.text = "Temperature: ${clickedLocationData.main.temp}°C"
    }


    // map eee user location position onujai show korar jonno
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val location = LatLng(args.ListA.coord!!.lat, args.ListA.coord!!.lon)//exact location list current list theke call deoya drkr
        map.addMarker(MarkerOptions().position(location).title(args.ListA.name))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))
    }
}
