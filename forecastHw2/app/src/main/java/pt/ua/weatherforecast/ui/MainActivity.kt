package pt.ua.weatherforecast.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pt.ua.weatherforecast.R
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment: Fragment = CityFragment.newInstance( )

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_container, fragment, "weather_fragment")
        transaction.commit()
    }


}