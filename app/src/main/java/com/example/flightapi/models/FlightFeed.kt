package com.example.flightapi.models

class FlightFeed (
        val aircraft: Aircraft,
        val airline: Airline,
        val arrival: Arrival,
        val departure: Departure,
        val flight: Flight,
        val geography: Geography,
        val speed: Speed,
        val status: String,
        val system: System
)
class Aircraft(val iataCode: String,
               val icao24 : String,
               val icaoCode : String,
               val regNumber : String)
class Airline(val iataCode: String, val icaoCode :String)
class Arrival(val iataCode : String, val icaoCode : String)
class Departure(val iataCode : String, val icaoCode : String)
class Flight (val iataNumber : String, val icaoNumber : String, val number : String)
class Geography(val altitude: String, val direction: String, val latitude: String, val longitude: String)
class Speed(val horizontal : String, val isGround : String, val vspeed : String)
class System(val squawk : String?, val updated : String)