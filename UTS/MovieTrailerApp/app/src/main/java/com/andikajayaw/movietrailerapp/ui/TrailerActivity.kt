package com.andikajayaw.movietrailerapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andikajayaw.movietrailerapp.R
import com.andikajayaw.movietrailerapp.adapter.TrailerAdapter
import com.andikajayaw.movietrailerapp.databinding.ActivityTrailerBinding
import com.andikajayaw.movietrailerapp.model.Constant
import com.andikajayaw.movietrailerapp.model.trailer.TrailerResponse
import com.andikajayaw.movietrailerapp.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class TrailerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrailerBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    lateinit var trailerAdapter: TrailerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrailerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        getMovieTrailer()
    }

    private fun setupView() {

    }

    private fun setupListener() {

    }

    private fun setupRecyclerView() {
        trailerAdapter = TrailerAdapter(arrayListOf(), object : TrailerAdapter.OnAdapterListener{
            override fun onLoad(key: String) {

            }
            override fun onPlay(key: String) {

            }
        })
        recyclerView = findViewById(R.id.list_trailer)
        recyclerView.setHasFixedSize(true)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = trailerAdapter
        }
    }

    private fun getMovieTrailer() {
        showLoading(true)
        ApiService().endpoint.getMovieTrailer(Constant.MOVIE_ID, Constant.API_KEY)
            .enqueue(object : Callback<TrailerResponse> {
                override fun onResponse(
                    call: Call<TrailerResponse>,
                    response: Response<TrailerResponse>
                ) {
                    showLoading(false)
                    if(response.isSuccessful) {
                        showTrailer(response.body()!!)
                    }

                }

                override fun onFailure(call: Call<TrailerResponse>, t: Throwable) {
                    showLoading(false)
                }

            })
    }

    private fun showLoading(loading: Boolean) {
        progressBar = findViewById(R.id.progress_list_trailer)
        when(loading) {
            true -> {
                progressBar.visibility = View.VISIBLE
            }
            else -> {
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun showTrailer(trailers: TrailerResponse) {
        trailerAdapter.setData(trailers.results)
    }

    companion object {
        private const val TAG: String = "TrailerActivity"
    }

}