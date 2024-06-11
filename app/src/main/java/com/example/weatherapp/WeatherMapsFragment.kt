package com.example.weatherapp

import android.os.Build
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
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
        onBackButtonClicked()
        setStatusBarColor()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)


        val clickedLocationData=args.ListA
        // binding position onujai weather set korbo.
        binding.tvLocation.text = clickedLocationData.name
        binding.tvWeatherStatus.text = clickedLocationData.weather[0].description
        binding.tvHumidity.text = "Humidity: ${clickedLocationData.main.humidity}%".toString()
        binding.tvWindSpeed.text = "Wind Speed: ${clickedLocationData.wind?.speed} m/s".toString()
        binding.tvMaxTemp.text = "Max Temp: ${Math.round(clickedLocationData.main.temp_max!!.minus(273.15))}°C"
        binding.tvMinTemp.text = "Min Temp: ${Math.round(clickedLocationData.main.temp_min!!.minus(273.15))}°C"
        binding.tvTemp.text = "${Math.round(clickedLocationData.main.temp.minus(273.15))}°C"

    }


    // map eee user location position onujai show korar jonno
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val location = LatLng(args.ListA.coord!!.lat, args.ListA.coord!!.lon)//exact location list current list theke call deoya drkr
        map.addMarker(MarkerOptions().position(location).title(args.ListA.name))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))
    }


    //back button for fragment to Fragment(NavigationToolbar)
    private fun onBackButtonClicked() {
        binding.tbWeatherFragment.setNavigationOnClickListener {
            findNavController().navigate(R.id.action_weatherMapsFragment_to_weatherPlaceFragment)
        }
    }


    //status bar color
    private fun setStatusBarColor() {
        activity?.window?.apply {
            val statusBarColors= ContextCompat.getColor(requireContext(), R.color.green)
            statusBarColor=statusBarColors
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M ){
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
    }
}
