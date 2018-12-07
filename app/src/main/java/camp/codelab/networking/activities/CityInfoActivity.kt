package camp.codelab.networking.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import camp.codelab.networking.R
import camp.codelab.networking.interfaces.WeatherInterface
import camp.codelab.networking.models.City
import camp.codelab.networking.utils.Consts
import kotlinx.android.synthetic.main.activity_city_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CityInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_info)


        // initialize and build Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(Consts.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // initialize the interface using retrofit.create()
        val weatherInterface = retrofit.create(WeatherInterface::class.java)


        val id = intent.getIntExtra(Consts.CITY_ID, -1)

        if (id != -1) {
            weatherInterface.getCityInfo(id)
                .enqueue(object : Callback<City> {

                    override fun onFailure(call: Call<City>, t: Throwable) {
                        Toast.makeText(this@CityInfoActivity, t.message, Toast.LENGTH_LONG).show()
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
        } else {
            Toast.makeText(this, "Unknown Error", Toast.LENGTH_LONG).show()
            finish()
        }

    }
}
