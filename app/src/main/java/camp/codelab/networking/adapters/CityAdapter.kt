package camp.codelab.networking.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import camp.codelab.networking.R
import camp.codelab.networking.activities.CityInfoActivity
import camp.codelab.networking.models.City
import camp.codelab.networking.utils.Consts
import kotlinx.android.synthetic.main.item_city.view.*

class CityAdapter : RecyclerView.Adapter<CityAdapter.CityViewHolder> {

    val cityList: List<City>

    constructor(cityList: List<City>) {
        this.cityList = cityList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_city, parent, false)
        return CityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

    override fun onBindViewHolder(viewHolder: CityViewHolder, position: Int) {
        val city = cityList[position]
        viewHolder.setCity(city)
    }

    inner class CityViewHolder : RecyclerView.ViewHolder {

        val view: View

        constructor(view: View) : super(view) {
            this.view = view
            view.card.setOnClickListener {

                val clickedCity = cityList[layoutPosition]
                val cityId = clickedCity.id

                val intent = Intent(view.context, CityInfoActivity::class.java)
                intent.putExtra(Consts.CITY_ID, cityId)
                view.context.startActivity(intent)

            }
        }

        fun setCity(city: City) {
            view.cityTitleTextView.text = city.title
        }

    }

}