package com.example.retrofit.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.retrofit.R

class DetailsActivity : AppCompatActivity() {
    private lateinit var id:TextView
    private lateinit var title:TextView
    private lateinit var body:TextView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        id = findViewById(R.id.id)
        title = findViewById(R.id.title)
        body = findViewById(R.id.body)
        id.text = intent.getIntExtra("id",-1).toString()
        title.text = intent.getStringExtra("title").toString()
        body.text = intent.getStringExtra("body").toString()

        button = findViewById(R.id.go_to_images_button)

        button.setOnClickListener{
            startActivity(Intent(this,ImagesActivity::class.java))
        }
    }
}