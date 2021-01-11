package com.example.flightapi

import android.content.Intent
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flightapi.models.FlightFeed
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_search_page.*
import okhttp3.*
import java.io.IOException


class SearchPageActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_page)

        //image rounded - source code: https://www.tutorialspoint.com/how-to-make-an-imageview-with-rounded-corners-on-android-app-using-kotlin
        //*********************************************************
        val bitmap = (resources.getDrawable(R.drawable.flightimage) as BitmapDrawable).bitmap
        val imageRounded = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
        val canvas = Canvas(imageRounded)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        canvas.drawRoundRect(RectF(10F, 10F, bitmap.width.toFloat(), bitmap.height.toFloat()),
            200F, 200F, paint) // Round Image Corner 100 100 100 100
        imageView.setImageBitmap(imageRounded)
        //*********************************************************

    }

    fun get(view: View){
        run(editText_iataCode.text.toString())
    }

    // on va interroger l'API
    fun run(iataCode: String){

            val request = Request.Builder()
                    .url("http://aviation-edge.com/v2/public/flights?key=7c1f07-caf747&flightIata=$iataCode")
                    .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        if (!response.isSuccessful) throw IOException("Unexpected code $response")

                        for ((name, value) in response.headers) {
                            println("le name + value : $name: $value")
                        }

                        val jsonBody = response.body!!.string()

                        println("la réponse est:$jsonBody")

                        //conversion json->class avec gson
                        val gson = GsonBuilder().create()
                        val sType = object : TypeToken<List<FlightFeed>>() { }.type

                        //on essaie de convertir la réponse avec la class FlightFeed afin de voir
                        // si un vol existe bel est bien
                        try {
                            val flightFeed = gson.fromJson<List<FlightFeed>>(jsonBody, sType)
                        } catch (e: JsonSyntaxException){
                            // IllegalStateException
                            val intent = Intent(this@SearchPageActivity, NotFoundFlightActivity::class.java)
                            startActivity(intent)
                            return
                        }

                        val intent = Intent(this@SearchPageActivity, FlightMenuActivity::class.java)
                        intent.putExtra("flightObject",jsonBody)
                        startActivity(intent)
                    }
                }
            })
        }
}