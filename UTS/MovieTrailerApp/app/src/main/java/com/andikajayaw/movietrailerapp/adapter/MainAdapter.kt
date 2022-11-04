package com.andikajayaw.movietrailerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andikajayaw.movietrailerapp.R
import com.andikajayaw.movietrailerapp.model.Constant
import com.andikajayaw.movietrailerapp.model.movie.MovieModel
import com.squareup.picasso.Picasso

class MainAdapter(var movies: ArrayList<MovieModel>, var listener: OnAdapterListener): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_main, parent, false)
    )

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = movies.size

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        val imagePoster = holder.imagePoster
        val posterPath = Constant.POSTER_PATH + movie.poster_path
        Picasso.get().load(posterPath).placeholder(R.drawable.placeholder_portrait).error(R.drawable.placeholder_portrait).into(imagePoster);
        imagePoster.setOnClickListener {
            listener.onClick(movie)
        }
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var textTitle: TextView = view.findViewById(R.id.textTitleSplash)
        var imagePoster: ImageView = view.findViewById(R.id.image_poster)
        fun bind(movies: MovieModel) {

            textTitle.text = movies.title
        }
    }

    public fun setData(newMovies: List<MovieModel>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(movie: MovieModel) {

        }
    }

}