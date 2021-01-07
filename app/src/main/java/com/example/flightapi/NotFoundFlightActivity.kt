package com.example.flightapi

import android.content.Intent
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_search_page.*
import kotlinx.android.synthetic.main.activity_not_found_flight.*

class NotFoundFlightActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_found_flight)

        //image rounded
        val bitmap = (resources.getDrawable(R.drawable.skyguide) as BitmapDrawable).bitmap
        val imageRounded = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config)
        val canvas = Canvas(imageRounded)
        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        canvas.drawRoundRect(
            RectF(10F, 10F, bitmap.width.toFloat(), bitmap.height.toFloat()),
            200F, 200F, paint) // Round Image Corner 100 100 100 100
        imageViewNotFound.setImageBitmap(imageRounded)
        
    }


    fun tryAgain(view: View) {
        val intent = Intent(this@NotFoundFlightActivity, SearchPageActivity::class.java)
        startActivity(intent)
    }
}