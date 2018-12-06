package camp.codelab.networking

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_search.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        searchQueryEditText.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                searchCities(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })

    }


    fun searchCities(searchQuery: String) {

        val retrofit = Retrofit.Builder()
                .baseUrl(Consts.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val weatherInterface = retrofit.create(WeatherInterface::class.java)

        weatherInterface.searchForCity(searchQuery)
                .enqueue(object : Callback<List<City>> {
                    override fun onFailure(call: Call<List<City>>, t: Throwable) {
                        Toast.makeText(this@SearchActivity, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<List<City>>, response: Response<List<City>>) {
                        response.body()?.let { cityList ->
                            prepareRecyclerView(cityList)
                        }
                    }

                })
    }

    fun prepareRecyclerView(cityList: List<City>) {

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = CityAdapter(cityList)

    }
}
