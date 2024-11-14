package com.example.myweatherappdemo


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {
    private var weatherData: List<ForecastData> = emptyList()

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewDate: TextView = itemView.findViewById(R.id.textViewDate)
        val textViewTemperature: TextView = itemView.findViewById(R.id.textViewTemperature)
        val imageViewIcon: ImageView = itemView.findViewById(R.id.imageViewIcon)
        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forecast, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val forecast = weatherData[position]
        holder.textViewDate.text = forecast.dtTxt
        holder.textViewTemperature.text = "${forecast.main.temp}°C"
        holder.textViewDescription.text = forecast.weather[0].description
        val iconUrl = "https://openweathermap.org/img/w/${forecast.weather[0].icon}.png"
        Picasso.get().load(iconUrl).into(holder.imageViewIcon)
    }

    override fun getItemCount() = weatherData.size

    fun setWeatherData(data: List<ForecastData>) {
        weatherData = data
        notifyDataSetChanged()
    }
}