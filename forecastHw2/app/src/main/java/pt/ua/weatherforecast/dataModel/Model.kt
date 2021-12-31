package pt.ua.weatherforecast.dataModel

import pt.ua.weatherforecast.network.Weather

object Model {

    data class City(val local : String, val globalIdLocal : Integer, val latitude:Double, val longitude:Double)
    data class Cities(val data: List<City>)

    data class Weather(val tMax: Double, val tMin: Double, val forecastDate: String)
    data class WeatherGroup(val data: List<Weather>)
}