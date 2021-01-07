package com.example.flightapi

import android.content.Intent
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.flightapi.models.FlightFeed
import com.example.flightapi.recyclerViewFlightDetail.FlightDetailRecyclerView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_flight_menu.*
import kotlinx.android.synthetic.main.activity_search_page.*

class FlightMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flight_menu)

        val jsonBody = intent.getStringExtra("flightObject")

        val gson = GsonBuilder().create()
        val sType = object : TypeToken<List<FlightFeed>>() { }.type
        val flightFeed = gson.fromJson<List<FlightFeed>>(jsonBody, sType)
        val flight = flightFeed[0]

        textView_flightInfo.text = "Flight "+flight.flight.iataNumber
        textView_status.text = "Le status: "+flight.status

        //image rounded - source code: https://www.tutorialspoint.com/how-to-make-an-imageview-with-rounded-corners-on-android-app-using-kotlin
        //*********************************************************
        val bitmap = (resources.getDrawable(R.drawable.flightimage2) as BitmapDrawable).bitmap
        val imageRounded = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
        val canvas = Canvas(imageRounded)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        canvas.drawRoundRect(
            RectF(10F, 10F, bitmap.width.toFloat(), bitmap.height.toFloat()),
            200F, 200F, paint) // Round Image Corner 100 100 100 100
        imageView_flightInfo.setImageBitmap(imageRounded)
        //*********************************************************

    }

    fun seePositionAircraft(view: View){
        val jsonBody = intent.getStringExtra("flightObject")
        val intent = Intent(this@FlightMenuActivity, MapsActivity::class.java)
        intent.putExtra("flightObject",jsonBody)
        startActivity(intent)
    }

    fun seeFlightDetails(view: View){
        val jsonBody = intent.getStringExtra("flightObject")
        val intent = Intent(this@FlightMenuActivity, FlightDetailRecyclerView::class.java)
        intent.putExtra("flightObject", jsonBody)
        startActivity(intent)

    }
}