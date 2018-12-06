package camp.codelab.networking

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        viewHolder.setCity(cityList[position])
    }

    class CityViewHolder : RecyclerView.ViewHolder {

        val view: View

        constructor(view: View) : super(view) {
            this.view = view
        }

        fun setCity(city: City) {
            view.cityTitleTextView.text = city.title
        }

    }

}