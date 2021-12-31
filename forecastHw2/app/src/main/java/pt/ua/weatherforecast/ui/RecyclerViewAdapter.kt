package pt.ua.weatherforecast.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import pt.ua.weatherforecast.R
import pt.ua.weatherforecast.dataModel.Model

class RecyclerViewAdapter(private val listData: List<Model.City>, val clickListener: ClickListener):RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.content_main, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        var cityName: TextView

        init {
            cityName = view.findViewById(R.id.city)
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cityName.text = listData.get(position).local
        holder.itemView.setOnClickListener{
            clickListener.onItemClick(listData.get(position))
        }
    }

    interface ClickListener{
        fun onItemClick(City:Model.City)
    }
}