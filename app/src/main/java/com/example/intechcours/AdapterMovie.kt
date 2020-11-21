package com.example.intechcours

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class AdapterMovie(private val dataSet: List<Movie>,clickListener: ClickMovieListener) :
    RecyclerView.Adapter<AdapterMovie.ViewHolder>() {
    val listener : ClickMovieListener = clickListener

    class ViewHolder(view: View, clickListener: ClickMovieListener) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val textViewMovieTitle: TextView;
        val textViewMovieOverview: TextView;
        val imageView: ImageView;
        val listener : ClickMovieListener = clickListener

        init {
            textViewMovieTitle = view.findViewById(R.id.movieTitle)
            textViewMovieOverview = view.findViewById(R.id.resumeMovie)
            imageView = view.findViewById(R.id.imageView)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onMovieClick(adapterPosition)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_movie_items, viewGroup, false)
        return ViewHolder(view,listener);
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        try {
            val url = "https://image.tmdb.org/t/p/w200" + dataSet[position].poster_path;
            Picasso.get().load(url).into(viewHolder.imageView)
        } catch (e: Exception) {
        }
        viewHolder.textViewMovieTitle.text = dataSet[position].title
        viewHolder.textViewMovieOverview.text = dataSet[position].overview
    }

    override fun getItemCount() = dataSet.size

    public interface ClickMovieListener{
        fun onMovieClick(position: Int);
    }

}