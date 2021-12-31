package pt.ua.weatherforecast.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.ua.weatherforecast.R
import pt.ua.weatherforecast.dataModel.Model
import pt.ua.weatherforecast.network.ApiDataCity

class CityFragment : Fragment(), RecyclerViewAdapter.ClickListener {
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_city, container, false)

        initRecyclerView(view)
        return view
    }

    private fun initRecyclerView(view: View){
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)


        Log.d("Status", "Fetching Data")
        var items1 = listOf<Model.City>()

        val clickListener = this

        ApiDataCity.apiData( object : ApiDataCity.Response{
            override fun data(data: Model.Cities, status: Boolean) {
                Log.d("Status", status.toString())
                if(status){
                    for(city in data.data){
                        items1 += city
                        Log.d("Cities", city.local)
                    }

                }

                Log.d("Items size: ", items1.size.toString())

                adapter = RecyclerViewAdapter(items1, clickListener) //adapter, bind data with ui
                recyclerView.adapter = adapter //adapter
            }
        })

    }


    companion object {

        @JvmStatic
        fun newInstance() =
            CityFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onItemClick(city: Model.City) {
        Log.d("Pressed: ", city.local)
        val fragment: Fragment = WeatherFragment.newInstance(city.local + " forecast ", city.globalIdLocal)
        val  transacion  = activity?.supportFragmentManager!!.beginTransaction()
        transacion.hide(activity?.supportFragmentManager!!.findFragmentByTag("weather_fragment")!!)
        transacion.add(R.id.frame_container, fragment)
        transacion.addToBackStack(null)
        transacion.commit()
    }
}