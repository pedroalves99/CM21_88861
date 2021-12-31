package pt.ua.weatherforecast.network
//https://stackoverflow.com/questions/45219379/how-to-make-an-api-request-in-kotlin/45219917
import io.reactivex.Observable
import pt.ua.weatherforecast.dataModel.Model
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


class Connect {
    companion object {
        private const val BASE_URL = "https://api.ipma.pt/"
        private fun getRetrofit(Url:String) : Retrofit {
            return Retrofit.Builder()
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Url)
                .build()
        }
        fun getApiData():Retrofit{
            val retrofitApi = getRetrofit(BASE_URL)
            return retrofitApi
        }
        fun callCities():Cities{
            val retrofitCall = getApiData()
            return retrofitCall.create(Cities::class.java)
        }
        fun callWeather():Weather{
            val retrofitCall = getApiData()
            return retrofitCall.create(Weather::class.java)
        }
    }
}


interface Cities {
    @GET("open-data/distrits-islands.json")
    fun getApi(@Query("limit") limit: Int): Observable<Model.Cities>
}
interface Weather {
    @GET("open-data/forecast/meteorology/cities/daily/{localId}.json")
    fun getApi(@Path("localId") localId:Int): Observable<Model.WeatherGroup>
}

