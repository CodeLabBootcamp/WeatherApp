package camp.codelab.networking

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize and build Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(Consts.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // initialize the interface using retrofit.create()
        val weatherInterface = retrofit.create(WeatherInterface::class.java)

        weatherInterface.getCityInfo(2379574)
            .enqueue(object : Callback<City>{

                override fun onFailure(call: Call<City>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<City>, response: Response<City>) {


                    response.body()?.let {

                        titleTextView.text = it.title
                        timezoneTextView.text = it.timezone
                        timeTextView.text = it.time
                        weatherTextView.text = it.weather[0].theTemp.toString()

                    }


                }

            })

    }
}
