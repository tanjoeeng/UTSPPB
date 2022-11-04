package com.andikajayaw.movietrailerapp.retrofit

import com.andikajayaw.movietrailerapp.model.detailmovie.DetailResponse
import com.andikajayaw.movietrailerapp.model.movie.MovieResponse
import com.andikajayaw.movietrailerapp.model.trailer.TrailerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndPoint {
    @GET("movie/now_playing")
    fun getMovieNowPlaying(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Call<MovieResponse>

    @GET("movie/popular")
    fun getMoviePopular(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): Call<MovieResponse>

    @GET("movie/{movieId}")
    fun getMovieDetail(
        @Path("movieId") movieId: Int,
        @Query("api_key") api_key: String
    ): Call<DetailResponse>

    @GET("movie/{movieId}/videos")
    fun getMovieTrailer(
        @Path("movieId") movieId: Int,
        @Query("api_key") api_key: String
    ): Call<TrailerResponse>
}