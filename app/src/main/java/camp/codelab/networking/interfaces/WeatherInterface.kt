package camp.codelab.networking.interfaces

import camp.codelab.networking.models.City
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherInterface {

    @GET("location/{id}/")
    fun getCityInfo(@Path("id") cityId: Int): Call<City>

    @GET("location/search/")
    fun searchForCity(@Query("query") searchQuery: String): Call<List<City>>

}