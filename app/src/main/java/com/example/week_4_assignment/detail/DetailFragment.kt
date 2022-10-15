package com.example.week_4_assignment.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.week_4_assignment.data.api.ApiClient
import com.example.week_4_assignment.data.model.Daily
import com.example.week_4_assignment.data.model.Weather
import com.example.week_4_assignment.databinding.FragmentDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.List

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private lateinit var navController: NavController

    private var lat: Double = 34.0
    private var lon: Double = 43.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        getData()
        getWeather(lat, lon)
    }

    //gelen veri kontrol ediliyor
    private fun getData() {
        arguments?.let { it ->
            val getlat = it.getString("lat")
            val getlon = it.getString("lon")

            getlat?.let { safeLat ->
                lat = safeLat.toDouble()
            }
            getlon?.let { safeLob ->
                lon = safeLob.toDouble()
            }
        }
    }

    //kullanidan gelen veriyinin detaylarini almak icin yeni baglanti kuruluyor
    private fun getWeather(lat: Double, lon: Double) {
        ApiClient.getApiService().getEvertyhingWeather(lat, lon, "tr", "metric").enqueue(object :
            Callback<Weather> {
            @SuppressLint("SimpleDateFormat")
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                if (response.isSuccessful) {
                    val weather = response.body()
                    weather?.let { safeWeather ->
                        safeWeather.daily?.size?.let {
                            //weather nesnesi varsa ve icinde daily alt nesnesi varsa onlari bir listeye atiyoruz
                            var weatherList: MutableList<List<Daily>> = mutableListOf<List<Daily>>()

                            //kac gunun verisi varsa hepsini atmak icin for dongusu olusturuldu
                            for (i in 0.. safeWeather.daily.size) {
                                weatherList.add(safeWeather.daily)
                            }
                            //oluturulan liste adaptere yollandi
                            setupAdapter(weatherList)

                            binding.tvTimeZone.text = safeWeather.timezone+" \n 7 Günlük Hava Tahmini"
                        }

                    } ?: run {
                        Log.d("deneme1", "çalışmadı")
                    }
                }
            }
            override fun onFailure(call: Call<Weather>, t: Throwable) {
                Log.d("deneme1", t.toString())
            }
        }
        )
    }

    //adapter ayarlandi
    private fun setupAdapter(weatherList: MutableList<List<Daily>>) {
        binding.rvWeatherDetail.adapter = DetailAdapter(weatherList)
    }


}