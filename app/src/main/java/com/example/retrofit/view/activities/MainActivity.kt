package com.example.retrofit.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.R
import com.example.retrofit.model.Contributor
import com.example.retrofit.model.api_interfaces.GitHubClient
import com.example.retrofit.model.PlaceHolderServicesGenerator
import com.example.retrofit.view.adapter.MainActivityRVAdapter
import retrofit2.*

class MainActivity : AppCompatActivity() {
    companion object{
        val BASE_URL ="https://jsonplaceholder.typicode.com/"
    }
    private lateinit var recyclerView: RecyclerView
    private lateinit var mainRVAdapter:MainActivityRVAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        mainRVAdapter = MainActivityRVAdapter(this@MainActivity)




        val github_client_get_request = PlaceHolderServicesGenerator.createService(GitHubClient::class.java)
        val contributors_list:Call<List<Contributor>> = github_client_get_request.contributors()
        contributors_list.enqueue(object :Callback<List<Contributor>>{
            override fun onResponse(
                call: Call<List<Contributor>>,
                response: Response<List<Contributor>>
            ) {
                val contributorList = response.body()
                //Toast.makeText(this@MainActivity,"Operation successful. Length = ${contributorList?.size}",Toast.LENGTH_LONG).show()

                mainRVAdapter.setContributors(contributorList!!)
                recyclerView.adapter = mainRVAdapter
            }

            override fun onFailure(call: Call<List<Contributor>>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Failed to load the data",Toast.LENGTH_LONG).show()
            }
        })

        mainRVAdapter.setOnClickListener(object : MainActivityRVAdapter.OnClickListener{
            override fun onClick(position: Int, model: Contributor,sharedElement:View) {
                val intent = Intent(this@MainActivity,DetailsActivity::class.java)

                intent.putExtra("id",model.id)
                intent.putExtra("title",model.title)
                intent.putExtra("body",model.body)
                var options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MainActivity,sharedElement,"transition_title")
                startActivity(intent,options.toBundle())
            }
        })

    }
}