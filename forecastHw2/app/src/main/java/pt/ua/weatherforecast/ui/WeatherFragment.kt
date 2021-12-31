package pt.ua.weatherforecast.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import pt.ua.weatherforecast.R
import pt.ua.weatherforecast.dataModel.Model
import pt.ua.weatherforecast.network.ApiDataWeather


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WeatherFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherFragment : Fragment() {

    private var param1: String? = null
    private var param2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

        val cityName = view.findViewById<TextView>(R.id.cityName )
        var weather = view.findViewById<TextView>(R.id.weather )
        cityName.text = param1

        ApiDataWeather.apiData( object : ApiDataWeather.Response {
            override fun data(data: Model.WeatherGroup, status: Boolean) {
                Log.d("Status", status.toString())
                if (status) {
                    for (day in data.data) {
                        weather.append("Date: " + day.forecastDate + "\n")
                        weather.append("\t"+"tMax: " + day.tMax.toString() + "\n")
                        weather.append("\t"+"tMin: " + day.tMin.toString() + "\n")
                        weather.append("\n");
                    }

                }
            }
        }, param2)

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: Integer) =
            WeatherFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putInt(ARG_PARAM2, param2.toInt())
                }
            }
    }
}