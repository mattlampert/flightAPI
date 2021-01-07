package com.example.flightapi.recyclerViewFlightDetail

import com.example.flightapi.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.flight_detail_single_item_row.view.*


class FlightDetailSingleItem (private val flightDetailSingle : FlightDetailSingle) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

        viewHolder.itemView.apply {
            key.text = flightDetailSingle.key
            value?.text = flightDetailSingle.value
        }
    }

    override fun getLayout() = R.layout.flight_detail_single_item_row

}

fun List<FlightDetailSingle>.toFlightDetailSingleItem(): List<FlightDetailSingleItem>{
    return this.map {
        FlightDetailSingleItem(it)
    }
}

