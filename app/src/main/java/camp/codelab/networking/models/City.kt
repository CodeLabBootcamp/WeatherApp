package camp.codelab.networking.models

import com.google.gson.annotations.SerializedName

class City() {

    @SerializedName("woeid")
    var id: Int = 0
    var title: String = ""
    var timezone: String = ""
    var time: String = ""
    @SerializedName("consolidated_weather")
    var weather: List<WeatherUpdate> = listOf()

}