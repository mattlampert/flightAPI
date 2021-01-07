package com.example.flightapi.recyclerViewFlightDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flightapi.R
import com.example.flightapi.models.FlightFeed
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_flight_detail_recycler_view.*

class FlightDetailRecyclerView : AppCompatActivity() {

    lateinit var listFlightDetail : List<FlightDetailSingle>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_detail_recycler_view)



        val jsonBody = intent.getStringExtra("flightObject")
        val gson = GsonBuilder().create()
        val sType = object : TypeToken<List<FlightFeed>>() { }.type
        val flightFeed = gson.fromJson<List<FlightFeed>>(jsonBody, sType)
        val flight = flightFeed[0]


        val item1 = FlightDetailSingle("Aircraft iatacode", flight.aircraft.iataCode)
        val item2 = FlightDetailSingle("Aircraft icao24", flight.aircraft.icao24)
        val item3 = FlightDetailSingle("Aircraft icaoCode", flight.aircraft.icaoCode)
        val item4 = FlightDetailSingle("Aircraft regNumber", flight.aircraft.regNumber)
        val item5 = FlightDetailSingle("Airline iataCode", flight.airline.iataCode)
        val item6 = FlightDetailSingle("Airline icaoCode", flight.airline.icaoCode)
        val item7 = FlightDetailSingle("Arrival iataCode", flight.arrival.iataCode)
        val item8 = FlightDetailSingle("Arrival icaoCode", flight.arrival.icaoCode)
        val item9 = FlightDetailSingle("Departure iataCode", flight.departure.iataCode)
        val item10 = FlightDetailSingle("Departure icaoCode", flight.departure.icaoCode)
        val item11 = FlightDetailSingle("Flight iataNumber", flight.flight.iataNumber)
        val item12 = FlightDetailSingle("Flight icaoNumber", flight.flight.icaoNumber)
        val item13 = FlightDetailSingle("Flight number", flight.flight.number)
        val item14 = FlightDetailSingle("Geography altitude", flight.geography.altitude)
        val item15 = FlightDetailSingle("Geography direction", flight.geography.direction)
        val item16 = FlightDetailSingle("Geography latitude", flight.geography.latitude)
        val item17 = FlightDetailSingle("Geography longitude", flight.geography.longitude)
        val item18 = FlightDetailSingle("Speed horizontal", flight.speed.horizontal)
        val item19 = FlightDetailSingle("Speed isGround", flight.speed.isGround)
        val item20 = FlightDetailSingle("Speed vspeed", flight.speed.vspeed)
        val item21 = FlightDetailSingle("Status", flight.status)
        val item22 = FlightDetailSingle("System updated",flight.system.updated)

        val squawk = flight.system.squawk

        val item23 : FlightDetailSingle

        if(squawk.isNullOrEmpty()){
            item23= FlightDetailSingle("System squawk", "Null")
        } else{
            item23 = FlightDetailSingle("System squawk", squawk)
        }


        listFlightDetail = listOf(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11, item12, item13, item14, item15, item16, item17, item18, item19, item20, item21, item22, item23)


        //création du GroupeAdapter & adding items
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(listFlightDetail.toFlightDetailSingleItem())
        }

        recycler_view_flight_detail.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = groupAdapter
        }
    }
}