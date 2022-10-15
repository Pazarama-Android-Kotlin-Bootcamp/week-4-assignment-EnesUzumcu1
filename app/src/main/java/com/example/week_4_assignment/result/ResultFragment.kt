package com.example.week_4_assignment.result

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.week_4_assignment.R
import com.example.week_4_assignment.data.api.ApiClient
import com.example.week_4_assignment.data.model.Weather
import com.example.week_4_assignment.databinding.FragmentResultBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResultFragment  : Fragment(){
    private lateinit var binding: FragmentResultBinding
    private lateinit var navController: NavController

    private var lat : Double = 34.0
    private var lon : Double = 43.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        getData()
        getWeather(lat,lon)
        //detayli bilgi alinmasi icin diger sayfaya veri gonderiliyor
        binding.tvTimeZone.setOnClickListener{
            navController.navigate(R.id.action_resultFragment_to_detailFragment, Bundle().apply{
                putString("lat", lat.toString())
                putString("lon", lon.toString())
            })
        }
    }

    //gelen veriler bu fonksiyonda alindi
    private fun getData(){
        arguments?.let { it ->
            val getlat = it.getString("lat")
            val getlon = it.getString("lon")

            getlat?.let {safeLat ->
                lat = safeLat.toDouble()
            }
            getlon?.let {safeLob ->
                lon = safeLob.toDouble()
            }

        }
    }

    //Api baglatisi ile kullanici istegi alindi
    private fun getWeather(lat: Double,lon: Double){
        ApiClient.getApiService().getEvertyhingWeather(lat,lon,"tr","metric").enqueue(object :
            Callback<Weather> {
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                if(response.isSuccessful){
                    val weather = response.body()
                    weather?.let {
                        binding.tvTimeZone.text = it.timezone
                        binding.tvTemp.text = "Anlık Hava Durumu \n"+it.current?.temp.toString()+" ℃"
                        binding.tvFeels.text = "Hissedilen Sıcaklık: "+it.current?.feels_like.toString()+" ℃"
                        binding.tvUvi.text = "Ultraviyole Oranı: "+it.current?.uvi.toString()
                        binding.tvClouds.text = "Bulut Oranı: "+it.current?.clouds.toString()
                        binding.tvMain.text = it.current?.weather?.get(0)?.main.toString() + "\n"+ it.current?.weather?.get(0)?.description.toString()
                        var icon :String = "10d"
                        it.current?.weather?.get(0)?.icon.toString().let {safeIcon->
                            icon = safeIcon
                        }
                        Glide.with(this@ResultFragment.requireActivity())
                            .load("https://openweathermap.org/img/wn/${icon}@2x.png")
                            .into(binding.ivWeatherIcon)
                    } ?: run{
                        Log.d("deneme1","çalışmadı")
                    }
                }

            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Log.d("deneme1",t.toString())
            }

        }
        )
    }
}