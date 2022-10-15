package com.example.week_4_assignment.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week_4_assignment.data.model.Daily
import com.example.week_4_assignment.databinding.WeatherListItemBinding
import java.text.SimpleDateFormat
import java.util.*


class DetailAdapter(
    private val dailyList: MutableList<List<Daily>>,
) :
    RecyclerView.Adapter<DetailAdapter.DailyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val binding =
            WeatherListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyViewHolder(binding)
    }


    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        //positionu 0 olan item haric diger itemler ekrana yazilmak uzere bind edildi
        if (position != 0) holder.bind(dailyList[position], position)
        else holder.itemView.visibility = View.GONE

    }


    class DailyViewHolder(private val binding: WeatherListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(daily: List<Daily>, position: Int) {
            //daily listesinin alt elemanlarina ulasmak icin degiken atandi ve sirayla ekrana yazildi
            var i = 0
            if (position > 0) i = position - 1
            binding.tvMin.text = daily[i].temp?.min?.toInt().toString()
            binding.tvmax.text = daily[i].temp?.max?.toInt().toString()
            binding.tvDayTemp.text = daily[i].temp?.day?.toInt().toString()

            //gelen veriyi gun olarak yazilmasi icin cevirme islemi yapildi
            var date = daily[i].dt?.toLong()?.times(1000)?.let {
                Date(it)
            }
            binding.tvDayName.text = SimpleDateFormat("EEEE", Locale("tr")).format(date).toString()

            //hava durumu ikonu bastırmak icin glide kullanildi
            var icon :String = "10d"
            daily[i].weather?.get(0)?.icon.toString().let { safeIcon->
                icon = safeIcon
            }
            Glide.with(itemView.context)
                .load("https://openweathermap.org/img/wn/${icon}@2x.png")
                .into(binding.ivIcon)
        }
    }

    override fun getItemCount(): Int {
        return dailyList.size
    }
}
