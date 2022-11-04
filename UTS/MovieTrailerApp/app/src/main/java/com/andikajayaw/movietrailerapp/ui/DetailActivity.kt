package com.andikajayaw.movietrailerapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.andikajayaw.movietrailerapp.R
import com.andikajayaw.movietrailerapp.databinding.ActivityDetailBinding
import com.andikajayaw.movietrailerapp.model.Constant
import com.andikajayaw.movietrailerapp.model.detailmovie.DetailResponse
import com.andikajayaw.movietrailerapp.retrofit.ApiService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    companion object {
        private const val TAG: String = "DetailActivity"
        var TITLE_MOVIE: String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupListener()
    }

    override fun onStart() {
        super.onStart()
        getMovieDetail()
    }

    private fun setupView() {
        setSupportActionBar(findViewById(R.id.toolbar))


        supportActionBar!!.title = "" // set the top title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    private fun setupListener() {
        val fab_play: FloatingActionButton = findViewById(R.id.fabPlay)
        fab_play.setOnClickListener {
            startActivity(Intent(applicationContext, TrailerActivity::class.java))
        }
    }

    private fun getMovieDetail() {
        ApiService().endpoint.getMovieDetail(Constant.MOVIE_ID, Constant.API_KEY).enqueue(object :
            Callback<DetailResponse>{
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                if(response.isSuccessful) {
                    showMovie(response.body()!!)
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.d(TAG, t.toString())
            }

        })
    }

    fun showMovie(detail: DetailResponse) {
//        Log.d(TAG, "detailResponse: ${detail.vote_average.toString()}")

        TITLE_MOVIE = detail.title!!

        val backdropPath = Constant.BACKDROP_PATH + detail.backdrop_path
        Picasso.get().load(backdropPath)
            .placeholder(R.drawable.placeholder_landscape)
            .error(R.drawable.placeholder_landscape)
            .fit().centerCrop()
            .into(binding.imageBackdrop)

        val titleDetail: TextView = findViewById(R.id.textTitleDetail)
        titleDetail.text = detail.title
        val textVote: TextView = findViewById(R.id.textVoteDetail)
        textVote.text = detail.vote_average.toString()
        val textOverview: TextView = findViewById(R.id.textOverview)
        textOverview.text = detail.overview

        val textGenre: TextView = findViewById(R.id.textGenreDetail)

        for(genre in detail.genres) {
            textGenre.text = "${genre.name}"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}