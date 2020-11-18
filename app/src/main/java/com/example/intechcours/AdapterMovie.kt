package com.example.intechcours

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdapterMovie(private val dataSet: List<Movie>) :
    RecyclerView.Adapter<AdapterMovie.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView

        init {
            textView = view.findViewById(R.id.movieTitle)
            imageView = view.findViewById(R.id.imageView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_movie_items, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        try {
            val url = "https://image.tmdb.org/t/p/w200" + dataSet[position].poster_path;
            Picasso.get().load(url).into(viewHolder.imageView)
        } catch (e: Exception) {
        }
        viewHolder.textView.text = dataSet[position].title
    }

    override fun getItemCount() = dataSet.size
}