package com.andikajayaw.movietrailerapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andikajayaw.movietrailerapp.R
import com.andikajayaw.movietrailerapp.model.Constant
import com.andikajayaw.movietrailerapp.model.trailer.TrailerModel

class TrailerAdapter(var trailers: ArrayList<TrailerModel>, var listener: OnAdapterListener): RecyclerView.Adapter<TrailerAdapter.ViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_trailer, parent, false)
    )

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = trailers.size

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val trailer = trailers[position]
        holder.bind(trailer)
        val textNameTrailer = holder.textNameTrailer
        textNameTrailer.setOnClickListener {
            listener.onPlay(trailer.key!!)
        }
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var textNameTrailer: TextView = view.findViewById(R.id.text_name_trailer)
        fun bind(trailers: TrailerModel) {
            textNameTrailer.text = trailers.name
        }
    }

    public fun setData(newTrailers: List<TrailerModel>) {
        trailers.clear()
        trailers.addAll(newTrailers)
        notifyDataSetChanged()
        listener.onLoad(newTrailers[0].key!!)
    }

    interface OnAdapterListener{
        fun onLoad(key: String)
        fun onPlay(key: String)
    }

}