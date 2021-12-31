package pt.ua.weatherforecast.network

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import pt.ua.weatherforecast.dataModel.Model

class ApiDataWeather {
    companion object {
        val api by lazy { Connect.callWeather() }
        var disposable: Disposable? = null
        fun apiData(callback:Response, localId:Int){
            disposable = api.getApi(localId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({
                        result ->
                    callback.data(result,true)
                }, { error ->
                    error.printStackTrace()
                })

        }

    }
    interface Response {
        fun data(data: Model.WeatherGroup, status:Boolean)
    }
}