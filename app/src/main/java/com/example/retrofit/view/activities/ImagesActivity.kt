package com.example.retrofit.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.model.Image
import com.example.retrofit.model.PlaceHolderServicesGenerator
import com.example.retrofit.model.api_interfaces.PlaceholderImageServices
import com.example.retrofit.view.adapter.ImageActivityRVAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImagesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var imageRecyclerAdapter:ImageActivityRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_images)

        recyclerView = findViewById(R.id.image_recycker_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
       imageRecyclerAdapter = ImageActivityRVAdapter(this@ImagesActivity)


        var placeHolderImageService = PlaceHolderServicesGenerator.createService(
            PlaceholderImageServices::class.java)
        var imageRequestCall:Call<List<Image>> = placeHolderImageService.getImages()
        imageRequestCall.enqueue(object :Callback<List<Image>>{
            override fun onResponse(call: Call<List<Image>>, response: Response<List<Image>>) {

                var images_list = response.body()
                imageRecyclerAdapter.setImageList(images_list!!)
                recyclerView.adapter =imageRecyclerAdapter

            }

            override fun onFailure(call: Call<List<Image>>, t: Throwable) {
                Toast.makeText(this@ImagesActivity,"Failed to load data ${t.cause}",Toast.LENGTH_LONG).show()
            }
        })
    }
}